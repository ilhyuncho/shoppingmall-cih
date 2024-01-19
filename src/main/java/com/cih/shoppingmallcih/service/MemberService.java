package com.cih.shoppingmallcih.service;

import com.cih.shoppingmallcih.dto.member.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception{
        // 만약 같은 아이디면 예외 발생
    }
    void join(MemberJoinDTO memberJoinDTO)throws MidExistException;

}
