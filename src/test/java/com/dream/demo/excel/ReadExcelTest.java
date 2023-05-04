package com.dream.demo.excel;

import cn.hutool.json.JSON;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.event.Order;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.read.listener.PageReadListener;
import com.dream.demo.converter.DateConverter;
import com.dream.demo.vo.OrderImportVo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.gen5.commons.util.StringUtils;
import org.mockito.internal.matchers.Or;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ReadExcelTest {

    private static final List<String> sizeNoList = Lists.newArrayList("1#","1.5#","2#","2.5#","3#","3.5#","4#",
            "4.5#","5#","5.5#","6#","6.5#","7#","7.5#","8#","8.5#","9#","9.5#","10#","10.5#","11#","11.5#","12#","12.5#",
            "13#","13.5#","14#","14.5#","15#","15.5#","16#","16.5#","17#","17.5#","18#");

    @Test
    public void read(){

        String fileName = "F:\\品牌订单综合报表.xlsx";

        Field[] fields = OrderImportVo.class.getDeclaredFields();
        List<Field> fieldList = Arrays.stream(fields).filter(field -> sizeNoList.contains(field.getAnnotation(ExcelProperty.class).value()[0])).collect(Collectors.toList());

        EasyExcel.read(fileName, OrderImportVo.class, new PageReadListener<OrderImportVo>(dataList -> {
            for (OrderImportVo orderVo : dataList) {
                fieldList.forEach(field -> {
                    try {
                        field.setAccessible(true);
                        String columnValue = (String) field.get(orderVo);
                        if(StringUtils.isNotBlank(columnValue)){
                            System.out.println(orderVo.getOrderNo()+":"+field.getAnnotation(ExcelProperty.class).value()[0]+":"+columnValue);
                        }
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        })).sheet().doRead();


    }

}
