package com.dream.demo.properties;

import cn.hutool.core.util.IdUtil;

import java.util.HashMap;

public class Test {


    @org.junit.Test
    public void test() {
        HashMap<String,Integer> map = new HashMap<>(3);
      for (int i = 0;i<10;i++){
        map.put(String.valueOf(i),i);
      }
        System.out.println(map.get("1"));
    }

}
