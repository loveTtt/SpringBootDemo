package com.dream.demo.service.user.impl;

import com.dream.demo.constant.UserTypeEnum;
import com.dream.demo.service.user.UserTypeService;
import org.springframework.stereotype.Service;

/**
 * 超级vip用户
 * @author hys
 * @date 2022/8/9 10:41
 */
@Service
public class SuperVipUserServiceImpl implements UserTypeService {

	private static final double DISCOUNT = 0.7;
	@Override
	public UserTypeEnum getUserType() {
		return UserTypeEnum.SUPER_VIP;
	}

	@Override
	public Double getDiscountAmount(Double amount) {
		return amount*DISCOUNT;
	}
}
