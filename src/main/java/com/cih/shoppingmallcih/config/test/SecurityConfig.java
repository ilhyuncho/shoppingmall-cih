package com.cih.shoppingmallcih.config.test;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;


@Log4j2
@Configuration
public class SecurityConfig {
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var userDetailsService = new InMemoryUserDetailsManager();
//
//        UserDetails user = User.withUsername("cih")
//                .password("abcd1234")
//                .authorities("read")
//                .build();
//
//        userDetailsService.createUser(user);
//
//        return userDetailsService;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        log.info("configure.......");
//
////                http.cors().and().csrf().disable()
////                .and().authorizeRequests()
////                .antMatchers("/home").permitAll()
////                .antMatchers("/mypage").authenticated()
////                .anyRequest().authenticated();
//
//
//        // 모든 요청에 인증을 요구하도록 지정
//        //http.httpBasic();
//        //http.authorizeHttpRequests().anyRequest().authenticated();
//
//        http.csrf().disable();                  // post요청에서 403 forbidden 에러 발생 때문에

        http.formLogin(); // 로그인 화면에서 로그인을 진행한다는 설정


        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.error("------web configure--------------------");
        // 단순한 css파일이나 js파일 등을 불러올떄 추가되는 필터들을 제외
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }
}
