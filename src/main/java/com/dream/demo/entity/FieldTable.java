package com.dream.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
@TableName("field_table")
public class FieldTable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "table_name")
    private String tableName;

    @TableField(value = "field_name")
    private String fieldName;

    private String show;
}
