package com.fit.health_insurance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageableResponseDto<T> {
    private Integer page;
    private Integer total;
    private Integer size;
    private List<T> content;

    public PageableResponseDto(Page<T> page) {
        this.page = page.getNumber();
        this.total = page.getTotalPages();
        this.size = page.getSize();
        this.content = page.getContent();
    }
}
