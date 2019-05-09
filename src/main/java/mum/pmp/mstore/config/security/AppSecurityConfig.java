/*
 * Author: Yee Mon Zaw
 * Date: 24-Apr-2019
 * Class Name: AppSecurityConfig
 * Package: mum.pmp.mstore.config.security
 * Description: AppSecurityConfig is a convenience class that allows customization to both WebSecurity and HttpSecurity. 
 * The @EnableWebSecurity annotation and WebSecurityConfigurerAdapter work together to provide web based security.
 * 1) Require the user to be authenticated prior to accessing any URL within our application.
 * 2) Enables HTTP Basic and Form based authentication
 * 
 * 
 */

package mum.pmp.mstore.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private MyAuthSuccessHandler successHandler;
	
	@Value("${auth.query}")
	private String authQuery;
	@Value("${author.query}")
	private String authorQuery;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// Allow anyone (including unauthenticated users) to access to the below URLs , but the menu is controlled based on the user role.
		http.authorizeRequests()
			.antMatchers("/webjars/**", "/static/**", "/css/**", "/images/**" , "/", 
					"/home", "/catalogs" , 
					"/forgotpassword", "/resetpassword", "/user/**",
					"/signup", "/vendor/signup", "/admin/signup", "/customer/signup", 
					"/search", "/adsearch", 
					"/password/forgotpassword", "/forgotpassword", "/sendemailforgotpassword", "/resetpassword"
					,"/products/**", "/category/**" , "/category" , 
					"/shoppingCart/**", "/reports/**" , 
					"/placeOrder",
					"/payment/**", "/paymentgw/**", "/settlement/**")
			.permitAll();
		
		
		//Any URL that starts with “/approval/” must be an administrative user. For example, that would be the user “ADMIN” approval.
		http.csrf().disable()		//disable cross-side scripting
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/index").permitAll()
			.antMatchers("/profile/**").hasAnyRole("ADMIN","CUSTOMER","VENDOR")
			//.antMatchers("/catalogs").hasAnyRole("CUSTOMER")
			.antMatchers("/approval/*", "/approval/admins/*").hasRole("SUPER_ADMIN")
			.antMatchers("/approval/*", "/approval/vendors/*", "/admin/update/**").hasRole("ADMIN")
			.antMatchers("/vendor/**", "/products/allproducts" ).hasRole("VENDOR")
			.antMatchers("/customer/**" ).hasRole("CUSTOMER")
			.antMatchers("/products" , "/products/*").hasRole("VENDOR")
			.antMatchers("/category" , "/category/*").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")		//Explicitly state the login page
			.permitAll()
			.successHandler(successHandler)		//If Login successful, handler will handle for respective user home page.
			.and()
			.logout().invalidateHttpSession(true)		//Invalidate user session upon logout.
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-success")
			.permitAll();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// retrieve the different users and roles - the queries are defined in application.properties.
		auth.jdbcAuthentication().usersByUsernameQuery(authQuery)
								.authoritiesByUsernameQuery(authorQuery)
								.dataSource(dataSource);
	}
	
}
