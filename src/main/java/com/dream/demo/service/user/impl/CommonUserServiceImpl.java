package com.dream.demo.service.user.impl;

import com.dream.demo.constant.UserTypeEnum;
import com.dream.demo.service.user.UserTypeService;
import org.springframework.stereotype.Service;

/**
 * 普通用户
 * @author hys
 * @date 2022/8/9 10:52
 */
@Service
public class CommonUserServiceImpl implements UserTypeService {
	private static final double DISCOUNT = 0.9;
	@Override
	public UserTypeEnum getUserType() {
		return UserTypeEnum.COMMON;
	}

	@Override
	public Double getDiscountAmount(Double amount) {
		return amount*DISCOUNT;
	}
}
