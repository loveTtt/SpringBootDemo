package com.dream.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author huangys2
 * @date 2022/9/23 16:57
 */
@Data
@TableName(value = "company")
public class Company {

	@TableId(value = "id",type = IdType.AUTO)
	private long id;

	private String name;

	private int age;

	private String address;

	private double salary;
}
