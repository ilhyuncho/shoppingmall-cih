package com.cih.shoppingmallcih.domain.member;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @EntityGraph(attributePaths = "roleSet")    // 한번에 조인 로딩
    @Query("select m from Member m where m.mid = :mid and m.social = false")
    Optional<Member> getWithRoles(@Param("mid") String mid);

    // email을 이용해서 회원 정보를 찾을수 있도록 추가
    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);
}
