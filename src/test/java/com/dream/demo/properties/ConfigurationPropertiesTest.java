package com.dream.demo.properties;

import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.demo.SpringBootDemoApplication;
import com.dream.demo.entity.Company;
import com.dream.demo.entity.FieldTable;
import com.dream.demo.repostory.CompanyDao;
import com.dream.demo.repostory.FieldTableDao;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author huangys2
 * @date 2022/9/15 10:10
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = SpringBootDemoApplication.class)
public class ConfigurationPropertiesTest {

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private FieldTableDao fieldTableDao;

	@Test
	public void test() {
		System.out.println("根据id查询");
		Company company =  companyDao.selectById(1);
		System.out.println(company.toString());
		System.out.println("列表查询");
		List<Company> companyList = companyDao.selectList(Wrappers.<Company>lambdaQuery().eq(Company::getName,"Aaron"));
		companyList.forEach(company1 -> System.out.println(company1.toString()));


	}
	/**
	 * 设置表格信息
	 * @param dataList  查询出的数据
	 * @param fieldList  需要显示的字段
	 * @return
	 */
	private static List<Map<String,Object>> dataList(List<Object> dataList, List<String> fieldList) {
		List<Map<String,Object>> dataMapList = Lists.newArrayList();
		for (Object object : dataList) {
			Map<String,Object> dataMap = new HashMap<>(fieldList.size()*2);
			for (String fieldName : fieldList) {
				//通过反射根据需要显示的字段，获取对应的属性值
				dataMap.put(fieldName,getFieldValue(fieldName,object));
			}
			dataMapList.add(dataMap);
		}
		return dataMapList;
	}

	/**
	 * 根据传入的字段获取对应的get方法，如name,对应的getName方法，需命名规范
	 * @param fieldName  字段名
	 * @param tableVo    对象
	 * @return
	 */
	private static Object getFieldValue(String fieldName, Object tableVo) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = tableVo.getClass().getMethod(getter);
			return method.invoke(tableVo);
		} catch (Exception e) {
			return null;
		}

	}
}
