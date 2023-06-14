package com.dream.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author huangys2
 * @date 2022/9/23 16:57
 */
@TableName(value = "company")
@Data
public class Company {


	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;

	/**
	 * 公司名称
	 */
	private String name;

	private long stock;
	/**
	 * 公司地址
	 */
	private String address;
	/**
	 * 社会统一信用代码
	 */
	@TableField(value = "credit_code")
	private String creditCode;

	@TableField(value = "creat_date")
	private LocalDateTime createDate;
}
