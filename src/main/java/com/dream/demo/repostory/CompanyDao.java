package com.dream.demo.repostory;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dream.demo.entity.Company;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huangys2
 * @date 2022/9/24 07:57
 */
@Mapper
public interface CompanyDao extends BaseMapper<Company> {

}
