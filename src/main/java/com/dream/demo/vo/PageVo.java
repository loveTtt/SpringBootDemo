package com.dream.demo.vo;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class PageVo {
    private List<Map<String,Object>> orders;

    private Long pages;

    private Long total;

    private Long size;
}
