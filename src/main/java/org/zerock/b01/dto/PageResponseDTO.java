package org.zerock.b01.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {

        if(total <= 0) return;

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = ((this.page - 1) / 10 + 1) * 10;

        this.start = this.end - 9;

        int last = (this.total - 1) / 10 + 1;

        this.end = this.end > last ? last : this.end;

        this.prev = this.page > 1;

        this.next = this.page < last;
    }

}
