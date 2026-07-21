package com.lreebom.springbootlearn.common;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {
    private Long total;
    private Long current;
    private Long pages;
    private List<T> records;

    public PageResult(Long total, Long current, Long pages, List<T> records) {
        this.total = total;
        this.current = current;
        this.pages = pages;
        this.records = records;
    }
}
