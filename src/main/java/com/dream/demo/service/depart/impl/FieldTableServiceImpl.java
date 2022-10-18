package com.dream.demo.service.depart.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dream.demo.entity.FieldTable;
import com.dream.demo.repostory.FieldTableDao;
import com.dream.demo.service.depart.FieldTableService;
import org.springframework.stereotype.Service;

@Service
public class FieldTableServiceImpl extends ServiceImpl<FieldTableDao, FieldTable> implements FieldTableService {
}
