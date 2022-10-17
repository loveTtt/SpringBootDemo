package com.dream.demo.service.user;

import com.dream.demo.constant.UserTypeEnum;

/**
 * 用户类型
 * @author hys
 * @date 2022/8/9 10:34
 */
public interface UserTypeService {
	/**
	 * 获取用户类型
	 * @return 用户类型枚举
	 */
	UserTypeEnum getUserType();

	/**
	 * 获取折扣后金额
	 * @param amount 总金额
	 * @return 折扣后金额
	 */
	Double getDiscountAmount(Double amount);
}
