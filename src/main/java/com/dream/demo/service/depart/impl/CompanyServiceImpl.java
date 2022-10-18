package com.dream.demo.service.depart.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.demo.entity.Company;
import com.dream.demo.repostory.CompanyDao;
import com.dream.demo.service.depart.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyDao, Company>  implements CompanyService {
}
