package org.zimttech.fleetmanager.configs.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder passwordEncryptionService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncryptionService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"));
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/assets/**", "/login/**", "/media/**", "/plugins/**", "/js/**", "/css/**",
                        "/utility/**","/forgot-password/**", "/api/**", "/user/registration",
                        "/actuator/**", "/swagger-ui/**", "/v3/api-docs/**")
                .permitAll()
                .antMatchers("/driver/**").hasRole("DRIVER")
                .antMatchers("/supervisor/**").hasAnyRole("SUPERVISOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/login/error")
                .defaultSuccessUrl("/dashboard")
                .and()
                .logout()
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login");


    }
}
