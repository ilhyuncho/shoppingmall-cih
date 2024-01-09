package com.cih.shoppingmallcih.domain.test.guestbook;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GuestbookVO {
    private Long gno;

    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
