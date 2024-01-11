package com.cih.shoppingmallcih.dto.test;

import lombok.*;

import java.util.List;


@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    // 시작 페이지 번호
    private int start;
    // 끝 페이지 번호
    private int end;

    // 이번 페이지의 존재 여부
    private boolean prev;
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    //  builder 대신 다름으로 대체. PageResponseDTO.<ReplyDTO>withAll()
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){

        if(total <= 0){
            return;
        }

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;


        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;     // 화면에서의 마지막 번호
        this.start = this.end - 9;  // 화면에서의 시작 번호

        int last = (int)(Math.ceil((total/(double)size)));      // 데이터의 개수를 계산한 마지막 페이지 번호

        this.end = Math.min(end, last);
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }


}
