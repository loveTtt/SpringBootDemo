package com.dream.demo.excel;

import cn.hutool.json.JSON;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.dream.demo.vo.OrderImportVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ReadExcelTest {

    @Test
    public void read(){

        String fileName = "F:\\品牌订单综合报表.xlsx";

        EasyExcel.read(fileName, OrderImportVo.class, new PageReadListener<OrderImportVo>(dataList -> {
            for (OrderImportVo demoData : dataList) {
                log.info("读取到一条数据{}", demoData.toString());
            }
        })).sheet().doRead();

    }
}
