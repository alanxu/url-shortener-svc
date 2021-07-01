package me.alanx.urlshortener;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors()
        .and()
            .httpBasic()
        .and()
            .authorizeRequests()
                .antMatchers("/shorten", "/retrieve").authenticated()
                .anyRequest().permitAll()
        .and()
            .csrf().disable();
    }
}
