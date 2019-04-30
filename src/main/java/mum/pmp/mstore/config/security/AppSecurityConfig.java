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
		http.authorizeRequests()
			.antMatchers("/webjars/**", "/css/**", "/images/**" , 
					"/signup", "/vendor/signup", "/admin/signup", "/customer/signup", "/password/forgotpassword",
					"/forgotpassword", "/sendemailforgotpassword", "/resetpassword")
			.permitAll();
		
		http.csrf().disable()		//disable cross-side scripting
			.authorizeRequests()
			.antMatchers("/login").permitAll()
			.antMatchers("/index").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/vendor/**").hasRole("VENDOR")
			.antMatchers("/customer/**").hasRole("CUSTOMER")
			.antMatchers("/products" , "/products/**").hasRole("VENDOR")
			.antMatchers("/category" , "/category/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-success")
			.permitAll();
			
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().usersByUsernameQuery(authQuery)
								.authoritiesByUsernameQuery(authorQuery)
								.dataSource(dataSource);
	}
	
}
