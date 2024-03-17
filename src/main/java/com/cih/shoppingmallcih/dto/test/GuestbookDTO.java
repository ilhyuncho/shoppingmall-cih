package com.cih.shoppingmallcih.dto.test;


import com.cih.shoppingmallcih.dto.typeCommand.GuestGrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuestbookDTO {

    private Long gno;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    private String writer;

   // @NotEmpty
//    @JsonProperty("guestType")
    @Enumerated(EnumType.ORDINAL)
    private GuestGrade guestType;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
