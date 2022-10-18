package com.dream.demo.properties;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.dream.demo.entity.Company;
import org.assertj.core.util.Lists;
import org.springframework.boot.test.util.ApplicationContextTestUtils;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {


    @org.junit.Test
    public void test(){
        List<Map<Object,Object>> data = Lists.newArrayList();
        for(int i = 0;i<10;i++){
            HashMap<Object, Object> colorMap = MapUtil.of(new String[][] {
                    {"RED", "#FF0000"},
                    {"GREEN", "#00FF00"},
                    {"BLUE", "#0000FF"}
            });
            data.add(colorMap);
        }
        data.forEach(item->item.forEach((key,value)->{
            if("RED".equals(key)){
                System.out.println(value);
            }
        }));
        System.out.println("-------------------分割线------------------");
        data.forEach(item-> System.out.println(item.get("RED")));

    }

}
