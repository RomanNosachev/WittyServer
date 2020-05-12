package com.wittyhome.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
public class SecurityConfiguration 
extends WebSecurityConfigurerAdapter
{
	private UserDetailsService userDetailsService;
	
	@Autowired
	public SecurityConfiguration(UserDetailsService userDetailsService) 
	{
		this.userDetailsService = userDetailsService;
	}

	@Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
    {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
	
	@Bean
    public PasswordEncoder bCryptPasswordEncoder() 
	{
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() 
	{
        return new CustomAuthenticationSuccessHandler();
    }
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception 
	{
        httpSecurity
                .csrf()
                    .disable()
                .authorizeRequests()
                    .antMatchers("/registration").not().fullyAuthenticated()
                    
                    .antMatchers("/scenario/**").hasRole("USER")
                    .antMatchers("/group/**").hasRole("USER")
                    
                    .antMatchers("/enableScenario/**").hasRole("USER")
                    .antMatchers("/disableScenario/**").hasRole("USER")
                    
                    .antMatchers("/enableGroup/**").hasRole("USER")
                    .antMatchers("/disableGroup/**").hasRole("USER")
                    
                    .antMatchers("/addScenario/**").hasRole("ADMIN")
                    .antMatchers("/saveScenario/**").hasRole("ADMIN")
                    .antMatchers("/editScenario/**").hasRole("ADMIN")
                    .antMatchers("/deleteScenario/**").hasRole("ADMIN")
                    
                    .antMatchers("/deleteGroup/**").hasRole("ADMIN")
                    .antMatchers("/leaveGroup/**").hasRole("ADMIN")
                    
                    .antMatchers("/", "index", "/resources/**").permitAll()
                    .antMatchers("/css/**", "/js/**", "/img/**").permitAll()
                    
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successHandler(customAuthenticationSuccessHandler())
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/");
    }
}
