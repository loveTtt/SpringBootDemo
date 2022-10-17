package com.dream.demo.properties;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dream.demo.SpringBootDemoApplication;
import com.dream.demo.entity.Company;
import com.dream.demo.repostory.CompanyDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

	@Test
	public void test(){
		System.out.println("根据id查询");
		Company company =  companyDao.selectById(22);
		System.out.println(company.toString());
		System.out.println("列表查询");
		List<Company> companyList = companyDao.selectList(Wrappers.<Company>lambdaQuery().eq(Company::getName,"Aaron"));
		companyList.forEach(company1 -> System.out.println(company1.toString()));
		Page<Company> companyPage = new Page<>(1,10);
		System.out.println("分页查询");
		IPage<Company> companyIPage = companyDao.selectPage(companyPage,Wrappers.<Company>lambdaQuery().lt(Company::getAge,10));
		System.out.println(companyIPage.getPages());
		System.out.println(companyIPage.getTotal());
		List<Company> companies = companyIPage.getRecords();
		companies.forEach(company1 -> System.out.println(company1.toString()));

		System.out.println("聚合函数 ");
		QueryWrapper<Company> queryWrapper = new QueryWrapper<>();
		queryWrapper.select("name,avg(salary)").between("age",10,30).groupBy("name").having("name = {0}","Mary");
		List<Map<String, Object>> listSumMap =  companyDao.selectMaps(queryWrapper);
		listSumMap.stream().findFirst().orElse(new HashMap<>()).forEach((name,salary)->{
				System.out.println(name);
				System.out.println(salary.toString());
		});
	}
}
