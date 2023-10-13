package com.cih.shoppingmallcih.domain.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class jpaTest {

    @Test
    @DisplayName("NoArgConstructor 옵션 테스트")
    public void test1(){
        Memo memo = new Memo();

        System.out.println(memo.getMno());

    }

}
