package com.cih.shoppingmallcih.service;

import com.cih.shoppingmallcih.domain.member.Member;
import com.cih.shoppingmallcih.domain.member.MemberRepository;
import com.cih.shoppingmallcih.domain.member.MemberRole;
import com.cih.shoppingmallcih.dto.member.MemberJoinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {

        boolean exist = memberRepository.existsById(memberJoinDTO.getMid());
        if(exist){
            throw new MidExistException();
        }

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        memberRepository.save(member);
    }
}
