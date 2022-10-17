package com.dream.demo.user;

import com.dream.demo.SpringBootDemoApplication;
import com.dream.demo.constant.UserTypeEnum;
import com.dream.demo.service.CenterUserHandleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 用户策略模式测试
 * @author hys
 * @date 2022/8/9 10:54
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class UserStrategyModeTest {

	@Autowired
	private CenterUserHandleService centerUserHandleService;


	@Test
	public void testUserStrategy(){
		double amount = 100.00;
		//获取普通用户应支付金额
		System.out.println(centerUserHandleService.getDiscountAmount(UserTypeEnum.COMMON,amount));
		//获取vip用户应支付金额
		System.out.println(centerUserHandleService.getDiscountAmount(UserTypeEnum.VIP,amount));
		//获取超级vip用户应支付金额
		System.out.println(centerUserHandleService.getDiscountAmount(UserTypeEnum.SUPER_VIP,amount));
	}
}
