package com.dream.demo.properties;


import cn.hutool.core.lang.UUID;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
