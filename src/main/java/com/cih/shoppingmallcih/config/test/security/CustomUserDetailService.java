package com.cih.shoppingmallcih.config.test.security;

import com.cih.shoppingmallcih.domain.member.Member;
import com.cih.shoppingmallcih.domain.member.MemberRepository;
import com.cih.shoppingmallcih.dto.member.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        UserDetails userDetails = User.builder().username("user1")
//                .password(passwordEncoder.encode("1111"))
//                .authorities("ROLE_USER")
//                .build();
//
//        return userDetails;

        log.error("loadUserByUsername: " + username);

        Optional<Member> result = memberRepository.getWithRoles(username);
        if(result.isEmpty()){
            throw new UsernameNotFoundException("username not foudn.........");
        }

        Member member = result.get();

        MemberSecurityDTO memberSecurityDTO= new MemberSecurityDTO(member.getMid(),
            member.getMpw(),
            member.getEmail(),
            member.isDel(),
            false,
            member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name()))
                    .collect(Collectors.toList()));
            log.error("memberSecurityDTO: " + memberSecurityDTO );
            return memberSecurityDTO;
    }
}
