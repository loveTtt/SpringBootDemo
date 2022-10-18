package com.dream.demo.properties;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.demo.SpringBootDemoApplication;
import com.dream.demo.entity.Company;
import com.dream.demo.entity.FieldTable;
import com.dream.demo.repostory.CompanyDao;
import com.dream.demo.repostory.FieldTableDao;
import com.dream.demo.vo.PageVo;
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
import java.util.stream.Collectors;


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
	public PageVo test(){
		System.out.println("根据id查询");
		Company company =  companyDao.selectById(22);
		System.out.println(company.toString());
		System.out.println("列表查询");
		List<Company> companyList = companyDao.selectList(Wrappers.<Company>lambdaQuery().eq(Company::getName,"Aaron"));
		companyList.forEach(company1 -> System.out.println(company1.toString()));

//		System.out.println("聚合函数 ");
//		QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
//		queryWrapper.select("name,avg(salary)").between("age",10,30).groupBy("name").having("name = {0}","Mary");
//		List<Map<String, Object>> listSumMap =  companyDao.selectMaps(queryWrapper);
//		listSumMap.stream().findFirst().orElse(new HashMap<>()).forEach((name,salary)->{
//				System.out.println(name);
//				System.out.println(salary.toString());
//		});


		Page<Company> companyPage = new Page<>(1,10);
		System.out.println("分页查询");
		IPage<Company> companyIPage = companyDao.selectPage(companyPage,Wrappers.<Company>lambdaQuery().lt(Company::getId,200));
		System.out.println(companyIPage.getPages());
		System.out.println(companyIPage.getTotal());
		System.out.println(companyIPage.getSize());
		List<Company> companies = companyIPage.getRecords();
		List<Object> objects = companies.stream().map(company1 -> (Object) company1).collect(Collectors.toList());

		List<FieldTable> fieldTables = fieldTableDao.selectList(Wrappers.<FieldTable>lambdaQuery().eq(FieldTable::getShow,"1"));
		List<String> fieldNames = fieldTables.stream().map(FieldTable::getName).collect(Collectors.toList());

		List<Map<String,Object>> dateMapList = dataList(objects,fieldNames);
		PageVo pageVo = new PageVo();
		pageVo.setPages(companyIPage.getPages());
		pageVo.setSize(companyIPage.getSize());
		pageVo.setTotal(companyIPage.getTotal());
		pageVo.setOrders(dateMapList);
		return pageVo;
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
