package com.dream.demo.properties;


import cn.hutool.core.date.DateUtil;
import com.dream.demo.SpringBootDemoApplication;
import com.dream.demo.entity.Company;
import com.dream.demo.service.depart.CompanyService;
import com.dream.demo.util.RedisUtil;
import org.apache.ibatis.transaction.Transaction;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;


@SpringBootTest
@RunWith(SpringRunner.class)
public class Test {

    @Autowired
    CompanyService companyService;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    RedisUtil redisUtil;


    /**
     * 可重入锁reentrantLock实现
     */
    @org.junit.Test
    public void reentrantLockTest() {
        ReentrantLock lock = new ReentrantLock(true);
        IntStream.range(1,10).parallel().forEach(i -> {
            lock.lock();
            try{
                Company company = companyService.getById(1);
                long stock = company.getStock();
                System.out.println(stock);
                if (stock > 91) {
                    company.setStock(stock - 1);
                }
                transactionTemplate.execute((status) -> companyService.updateById(company));
            }finally {
                lock.unlock();
            }
        });
        Company company = companyService.getById(1);
        System.out.println(company.getStock());
    }

    /**
     * redis位图实现签到
     */
    @org.junit.Test
    public void redisBitMapTest(){
        String key = "sign:1234567:"+ DateUtil.format(new Date(),"yyMM");
        int dayOfMonth = DateUtil.dayOfMonth(new Date());
        redisUtil.setBit(key,dayOfMonth-1,true);

        List<Long> result = redisUtil.bitFields(key, BitFieldSubCommands.create()
                        .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth)).valueAt(0));
        long num = result.stream().findFirst().orElse(0L);
        int count = 0;
        while (true){
            if((num & 1) == 0){
                break;
            }else{
                count++;
            }
            num >>>= 1;
        }
        System.out.println(count);

    }
}
