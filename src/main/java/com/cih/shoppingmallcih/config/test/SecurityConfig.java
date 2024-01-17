package com.cih.shoppingmallcih.config.test;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Log4j2
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService(){
        var userDetailsService = new InMemoryUserDetailsManager();

        UserDetails user = User.withUsername("cih")
                .password("abcd1234")
                .authorities("read")
                .build();

        userDetailsService.createUser(user);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        log.info("configure.......");

//                http.cors().and().csrf().disable()
//                .and().authorizeRequests()
//                .antMatchers("/home").permitAll()
//                .antMatchers("/mypage").authenticated()
//                .anyRequest().authenticated();


        // 모든 요청에 인증을 요구하도록 지정
        //http.httpBasic();
        http.authorizeHttpRequests()
                .antMatchers("/member/*").permitAll()
                .anyRequest().authenticated();

        http.csrf().disable();                  // post요청에서 403 forbidden 에러 발생 때문에

        http.formLogin().loginPage("/member/login");
        return http.build();
    }

}
