package com.cih.shoppingmallcih.repository;


import com.cih.shoppingmallcih.domain.test.guestbook.Guestbook;
import com.cih.shoppingmallcih.domain.test.guestbook.GuestbookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {
    @Autowired
    private GuestbookRepository guestbookRepository;

    @Test
    public void insertDummies(){
        IntStream.rangeClosed(1,300).forEach(i -> {

            Guestbook guestbook = Guestbook.builder()
                    .title("Title..." + i)
                    .content("Content...." + i)
                    .writer("user" + (i % 10))
                    .build();

            System.out.println(guestbookRepository.save(guestbook));

        });
    }

    @Test
    public void updateTest(){
        Optional<Guestbook> result = guestbookRepository.findById(300L);
        if(result.isPresent()){
            Guestbook guestbook = result.get();

            guestbook.setTitle("Changed title..");
            guestbook.setContent("Changed Content..");

            guestbookRepository.save(guestbook);
        }
    }


}
