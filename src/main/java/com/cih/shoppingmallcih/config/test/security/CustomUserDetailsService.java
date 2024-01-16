package com.cih.shoppingmallcih.config.test.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 실제 인증을 처리 할때 호출되는 부분

        log.error("loadUserByUsername: " + username);

        // 시큐리티는 내부적으로 UserDetails 타입의 객체를 이용해서 패스워드르루 검사하고, 
        // 사용자 권한을 확인하는 방식으로 동작 함.
        
        // 정리하면. 개발 단계에서 UserDetails 라는 인터페이스 타입에 맞는 객체가 필요하고
        // 이를 CustomUserDetailsService 에서 반환하는 일이 필요함
        UserDetails userDetails = User.withUsername("cih")
        .password("abcd1234")
        .authorities("ROLE_USER")
        .build();

        return userDetails;
    }


}
