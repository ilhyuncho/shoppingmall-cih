package com.cih.shoppingmallcih.config.test.security;

import com.cih.shoppingmallcih.domain.member.Member;
import com.cih.shoppingmallcih.domain.member.MemberRepository;
import com.cih.shoppingmallcih.domain.member.MemberRole;
import com.cih.shoppingmallcih.dto.member.MemberSecurityDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    // OAuth2UserService 인터페이스를 구현해야 하는데 하위 클래스인 DefaultOAuth2UserService
    // 를 상속해서 구현하는 방식이 가장 간단

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("userRequest.............");
        log.info(userRequest);

        // loadUser()에서는 카카오 서비스와 연동된 결과를 OAuth2UserRequest로 처리한다.
        ClientRegistration clientRegistration = userRequest.getClientRegistration();
        String clientName = clientRegistration.getClientName();
        log.error("clientName : " + clientName);

        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> paramMap = oAuth2User.getAttributes();

        String email = null;

        paramMap.forEach((k,v)-> {
            log.info("-----------------");
            log.info(k + ":" + v);
        });

        switch(clientName){
            case "kakao":
                //email = getKakaoEmail(paramMap);
                email = "cihg1@naver.com";
                break;
        }

        log.info("kakao email: " + email);

        //return oAuth2User;
        return generateDTO(email, paramMap);
    }

    private MemberSecurityDTO generateDTO(String email, Map<String, Object> params){
        Optional<Member> result = memberRepository.findByEmail(email);

        // db에 이메일을 사용하는 유저가 없다면
        if (result.isEmpty()) {
            // 회원 추가 - mid는 이메일주소
            Member member = Member.builder()
                    .mid(email)
                    .mpw(passwordEncoder.encode("1111"))
                    .email(email)
                    .social(true)
                    .build();
            member.addRole(MemberRole.USER);

            memberRepository.save(member);

            // MemberSecurityDTO 구성및 반환
            MemberSecurityDTO memberSecurityDTO =
                    new MemberSecurityDTO(email, "1111", email,false, true, Arrays.asList(
                            new SimpleGrantedAuthority("ROLE_USER"))
                    );
            memberSecurityDTO.setProps(params);

            return memberSecurityDTO;
         }else{
            Member member = result.get();
            MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(member.getMid(),
                    member.getMpw(),
                    member.getEmail(),
                    member.isDel(),
                    member.isSocial(),
                    member.getRoleSet().stream().
                            map(memberRole -> new SimpleGrantedAuthority("ROLE_"+memberRole.name()))
                            .collect(Collectors.toList()));

            return memberSecurityDTO;
        }

    }
    private String getKakaoEmail(Map<String, Object> paramMap){
        log.info("---------kakao------------");

        Object kakaoAccount = paramMap.get("Kakao_account");

        log.info(kakaoAccount);

        LinkedHashMap accountMap = (LinkedHashMap) kakaoAccount;

        String email = (String)accountMap.get("email");
        log.info("email : " + email);

        return email;
    }
}
