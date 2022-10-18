package com.dream.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("field_table")
public class FieldTable {

    private Long id;

    private String name;

    private String show;
}
