package com.snow.probmgmt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationEntryPoint authenticationEntryPoint;
    private AuthenticationSuccessHandler successHandler;
    private LogoutSuccessHandler logoutSuccessHandler;

    protected WebSecurityConfig(AuthenticationEntryPoint authenticationEntryPoint,
            AuthenticationSuccessHandler successHandler,
            LogoutSuccessHandler logoutSuccessHandler) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.successHandler = successHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors().disable()
            .authorizeRequests()
                .antMatchers("/api/app/**").authenticated()
                .antMatchers("/api/admin/**").permitAll()
                .anyRequest().permitAll()
                .and()
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
            .formLogin()
                .loginProcessingUrl("/api/admin/login").permitAll()
                .successHandler(successHandler)
                .and()
            .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessHandler(logoutSuccessHandler);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user1);
    }
}
