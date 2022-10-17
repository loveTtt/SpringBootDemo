package com.dream.demo.service;

import com.dream.demo.constant.UserTypeEnum;
import com.dream.demo.service.user.UserTypeService;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * 用户策略处理
 * @author hys
 * @date 2022/8/9 10:33
 */
@Service
public class CenterUserHandleService {
	/**
	 * 	也可使用hashmap代替
	 */
	private final EnumMap<UserTypeEnum,UserTypeService> userTypeServiceEnumMap = new EnumMap<>(UserTypeEnum.class);
	/**
	 * 注册用户策略、构造方法会把{@link UserTypeService}所有的实现类注册进去
	 *
	 * @param userTypeServices {@link UserTypeService}所有的实现类
	 */
	public CenterUserHandleService(List<UserTypeService> userTypeServices) {
		userTypeServices.forEach(userTypeService -> userTypeServiceEnumMap.put(userTypeService.getUserType(), userTypeService));
	}


	/**
	 * 获取折扣后金额
	 * @param userTypeEnum 用户类型枚举
	 * @param amount 原价
	 * @return 折扣后金额
	 */
	public Double getDiscountAmount(UserTypeEnum userTypeEnum,Double amount){
		return userTypeServiceEnumMap.get(userTypeEnum).getDiscountAmount(amount);
	}



}
