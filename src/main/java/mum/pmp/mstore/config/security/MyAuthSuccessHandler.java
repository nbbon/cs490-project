/*
 * Author: Yee Mon Zaw
 * Date: 05-May-2019
 * Class Name: MyAccessDeniedHandler
 * Package: mum.pmp.mstore.config.security
 * Description: This class extends AuthenticationSuccessHandler and this will be called when a user has been successfully authenticated.
 * Based on the user role, different landing page will be displayed.
 */


package mum.pmp.mstore.config.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyAuthSuccessHandler
  implements AuthenticationSuccessHandler {
  
    protected Log logger = LogFactory.getLog(this.getClass());
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    private Authentication auth;
 
    public Authentication getAuth() {
		return auth;
	}

	public void setAuth(Authentication auth) {
		this.auth = auth;
	}

	/*
	 * request - the request which caused the successful authentication 
	 * response - the response authentication - 
	 * the Authentication object which was created during the authentication process.
	*/
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication)
      throws IOException {
  
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication)
      throws IOException {
  
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            logger.debug(
              "Response has already been committed. Unable to redirect to "
              + targetUrl);
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    protected String determineTargetUrl(Authentication authentication) {
        boolean isVendor = false;
        boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities
         = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_VENDOR")) {
                isVendor = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }
        
        setAuth(authentication);
        
        System.out.println("is Vendor ? " + isVendor + ".. is Admin?" + isAdmin);
        // redirect to products details page.
        if (isVendor) {
        	System.out.println("Is Vendor?" + isVendor);
            return "/products/";
        } else if (isAdmin) {		// redirect to category page.
        	System.out.println("Is Admin?" + isAdmin);
            return "/category/";
        } else {
        	return "/home";
        }
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}