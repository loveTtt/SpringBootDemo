package com.dream.demo.service.user.impl;

import com.dream.demo.constant.UserTypeEnum;
import com.dream.demo.service.user.UserTypeService;
import org.springframework.stereotype.Service;

/**
 * vip用户
 * @author hys
 * @date 2022/8/9 10:51
 */
@Service
public class VipUserServiceImpl implements UserTypeService {
	private static final double DISCOUNT = 0.8;
	@Override
	public UserTypeEnum getUserType() {
		return UserTypeEnum.VIP;
	}

	@Override
	public Double getDiscountAmount(Double amount) {
		return amount*DISCOUNT;
	}
}
