package com.cih.shoppingmallcih.domain.test.guestbook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>,
        QuerydslPredicateExecutor<Guestbook>    // Querydsl
{

}
