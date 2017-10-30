package co.kr.umbbalook.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by whydda on 2017-08-03.
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/login/**", "/pub/**", "/static/**", "/webjars/**", "/h2console/**").permitAll()
                .anyRequest().authenticated()
                //.anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login/form")
                .loginProcessingUrl("/login/pros")
                .usernameParameter("user_id")
                .passwordParameter("passwd")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .logoutUrl("/logout").permitAll()
                .and()
                .csrf().disable()
                .httpBasic()
        ;

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customProvider());
    }

    @Bean
    public CustomAuthenticationProvider customProvider() {
        return new CustomAuthenticationProvider();
    }

    @Bean
    public ShaPasswordEncoder sharPasswordEncoder(){
        ShaPasswordEncoder passwdEndoder = new ShaPasswordEncoder(256);
        return passwdEndoder;
    }
}
