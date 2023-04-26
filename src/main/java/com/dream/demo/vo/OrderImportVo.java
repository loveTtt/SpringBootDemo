package com.dream.demo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

@Data
public class OrderImportVo {
    @ExcelProperty("销售凭证")
    private String orderNo;
    @ExcelProperty("产品组")
    private String productGroup;
    @ExcelProperty("工厂")
    private String factory;
    @ExcelProperty("工厂名称")
    private String factoryName;
    @ExcelProperty("销售凭证类型")
    private String salesVoucherType;
    @ExcelProperty("交货日期")
    @DateTimeFormat("MM月dd日")
    private String deliveryDate;
    @ExcelProperty("订单类型描述")
    private String orderType;
    @ExcelProperty("出货月份(GAC Month)")
    private String gacMonth;
    @ExcelProperty(value = "凭证日期")
    @DateTimeFormat("MM月dd日")
    private String documentDate;
    @ExcelProperty("包装ship-to代码")
    private String packShipToCode;
    @ExcelProperty("包装ship-to名称1")
    private String ackShipToName1;
    @ExcelProperty("尺码总数量")
    private String sizeTotal;
}
