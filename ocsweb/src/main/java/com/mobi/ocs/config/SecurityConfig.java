
package com.mobi.ocs.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder())
				.usersByUsernameQuery("select username,password, enabled from users where users.username=?")
				.authoritiesByUsernameQuery("select username, authority from authorities where authorities.username=?");

		// add our users for in memory Authentication

		/*
		 * auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
		 * .withUser("Shafi").password(passwordEncoder.encode("1234")).roles("SALES").
		 * and() .withUser("vignesh").password(passwordEncoder.encode("123456")).roles(
		 * "SALES").and()
		 * .withUser("Marcus").password(passwordEncoder.encode("1234")).roles(
		 * "SALES_HEAD").and()
		 * .withUser("Melvin").password(passwordEncoder.encode("1234")).roles("SALES")
		 * .and() .withUser("Nandhini").password(passwordEncoder.encode("1234")).roles(
		 * "FINANCE").and()
		 * .withUser("Asther").password(passwordEncoder.encode("1234")).roles(
		 * "PROCESSING").and()
		 * .withUser("Ethan").password(passwordEncoder.encode("1234")).roles("RISK").
		 * and() .withUser("admin")
		 * .password(passwordEncoder.encode("123456")).roles("USER", "ADMIN");
		 */

	}

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.authorizeRequests() .anyRequest().authenticated() .and() .formLogin()
	 * .loginPage("/LoginPage") .loginProcessingUrl("/authenticateUser")
	 * .permitAll();
	 * 
	 * 
	 * 
	 * http.authorizeRequests().antMatchers("/login").permitAll().antMatchers(
	 * "/admin/**").hasRole("ADMIN") .antMatchers("/**").hasAnyRole("ADMIN",
	 * "USER").and().formLogin().and().logout()
	 * .logoutSuccessUrl("/login").permitAll().and().csrf().disable();
	 * 
	 * }
	 */

	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http.authorizeRequests().antMatchers("/").hasRole("SALES").antMatchers(
	 * "/leaders/**").hasRole("SALES")
	 * .antMatchers("/systems/**").hasRole("SALES").and().formLogin().loginPage(
	 * "/showMyLoginPage")
	 * .loginProcessingUrl("/authenticateTheUser").permitAll().and().logout().
	 * permitAll().and() .exceptionHandling().accessDeniedPage("/access-denied");
	 * 
	 * }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**", "/sms/send/otp", "/sms/validate/otp", "/signup", "/ForgotPassword",
						"/resetPassword/request/otp", "/resetPassword/update", "/user/otp/validate", "/about",
						"/CallBack/saveCallBackRequest", "/internal/signup")
				.permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/authenticateTheUser").successForwardUrl("/Home").permitAll().and().logout()
				.logoutSuccessUrl("/login");

	}

	@Bean
	public JdbcUserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager();
		manager.setDataSource(dataSource);
		manager.setUsersByUsernameQuery("select username,password,enabled from users where users.username=?");
		manager.setAuthoritiesByUsernameQuery("select username, role from user_roles where user_roles.username=?");
		manager.setRolePrefix("ROLE_");
		return manager;
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsManager());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

}
