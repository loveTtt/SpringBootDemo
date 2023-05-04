package com.dream.demo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.dream.demo.converter.DateConverter;
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
    @ExcelProperty(value = "交货日期",converter = DateConverter.class)
    private Date deliveryDate;
    @ExcelProperty("订单类型描述")
    private String orderType;
    @ExcelProperty("出货月份(GAC Month)")
    private String gacMonth;
    @ExcelProperty(value = "凭证日期",converter = DateConverter.class)
    private Date documentDate;
    @ExcelProperty("包装ship-to代码")
    private String packShipToCode;
    @ExcelProperty("包装ship-to名称1")
    private String packShipToName1;
    @ExcelProperty("包装ship-to城市")
    private String packShipToCity;
    @ExcelProperty("包装ship-to国家代码")
    private String packShipToCountryCode;
    @ExcelProperty("包装ship-to国家名")
    private String packShipToCountryEn;
    @ExcelProperty("包装ship-to国家名(越文)")
    private String packShipToCountryZW;
    @ExcelProperty("包装Ship-to国家名(中文)")
    private String packShipToCountryCN;
    @ExcelProperty("Customer PO#")
    private String customerPo;
    @ExcelProperty("客户参考(Main PO)")
    private String mainPo;
    @ExcelProperty(value = "PO CREATED DATE/定价日期",converter = DateConverter.class)
    private Date pricingDate;
    @ExcelProperty(value = "客户参考日期/PO Cr.D",converter = DateConverter.class)
    private Date referenceDate;
    @ExcelProperty("付款条款")
    private String paymentProvision;
    @ExcelProperty("销售组织")
    private String salesOrganization;
    @ExcelProperty("客户物料")
    private String customerMaterial;
    @ExcelProperty("物料")
    private String material;
    @ExcelProperty("SAP物件描述")
    private String sapDescribe;
    @ExcelProperty("尺码总数量")
    private Integer sizeTotal;
    @ExcelProperty("客户组 3/销售地区")
    private String salesRegion;
    @ExcelProperty("电话(Nike的客户)")
    private String phone;
    @ExcelProperty("运输方式")
    private String shipType;
    @ExcelProperty("运输方式说明")
    private String shipTypeExplain;
    @ExcelProperty("客户组")
    private String customerGroup;
    @ExcelProperty(value = "创建日期",converter = DateConverter.class)
    private Date createDate;
    @ExcelProperty(value = "请求交货日期(RGAC)",converter = DateConverter.class)
    private Date requestDeliveryDate;
    @ExcelProperty(value = "原始确认交期(OGAC)",converter = DateConverter.class)
    private Date originalDate;
    @ExcelProperty(value = "RVS",converter = DateConverter.class)
    private Date rvs;
    @ExcelProperty("使用/包装方式")
    private String packMethod;
    @ExcelProperty("您的参考/Trace PO")
    private String tracePo;
    @ExcelProperty("Buyer代码")
    private String buyerCode;
    @ExcelProperty(index = 46)
    private String buyerName;
    @ExcelProperty("Season")
    private String season;
    @ExcelProperty("Size区间")
    private String sizeSection;
    @ExcelProperty("Material Trans. L/T")
    private String materialTrans;
    @ExcelProperty("Shoes production LT")
    private String shoesProduct;
    @ExcelProperty("Good Insurance")
    private String goodInsurance;
    @ExcelProperty("MLT")
    private String mlt;
    @ExcelProperty("PO collaboration (PO create to accept)")
    private String poCollaboration;
    @ExcelProperty("Material Prod. L/T")
    private String materialProd;
    @ExcelProperty(value = "物料可用日期/交期/离厂日",converter = DateConverter.class)
    private Date materialHandoverDate;
    @ExcelProperty("材料编号/BOM展开编号")
    private String bomCode;
    @ExcelProperty("国贸条件")
    private String tradeCondition;
    @ExcelProperty("备注")
    private String remark;
    @ExcelProperty("包装方式(多个)")
    private String packMethodMany;
    @ExcelProperty("分销渠道")
    private String distributionChannel;
    @ExcelProperty("送達方 Consignee")
    private String consignee;
    @ExcelProperty("需求细分")
    private String demand;
    @ExcelProperty("Gender")
    private String gender;
    @ExcelProperty("鞋型类别(大类)")
    private String shoeType;
    @ExcelProperty("大底编号")
    private String bottomNumber;
    @ExcelProperty("楦头编号")
    private String lastNumber;
    @ExcelProperty("型体新旧状况")
    private String styleStatus;
    @ExcelProperty("围条结构")
    private String fenceStructure;
    @ExcelProperty("鞋型(style name)(型体)")
    private String styleName;
    @ExcelProperty("英文配色")
    private String englishColour;
    @ExcelProperty("中文配色")
    private String cnColour;
    @ExcelProperty("Category(中类)")
    private String category;
    @ExcelProperty("Sample size")
    private String sampleSize;
    @ExcelProperty("SKU#")
    private String sku;
    @ExcelProperty("开发季节")
    private String developSeason;
    @ExcelProperty("中底编号")
    private String midsoleNumber;
    @ExcelProperty("脚床编号")
    private String footBedNumber;

    @ExcelProperty("1#")
    private String _1Yard;
    @ExcelProperty("1.5#")
    private String _1_5Yard;
    @ExcelProperty("2#")
    private String _2Yard;
    @ExcelProperty("2.5#")
    private String _2_5Yard;
    @ExcelProperty("3#")
    private String _3Yard;
    @ExcelProperty("3.5#")
    private String _3_5Yard;
    @ExcelProperty("4#")
    private String _4Yard;
    @ExcelProperty("4.5#")
    private String _4_5Yard;
    @ExcelProperty("5#")
    private String _5Yard;
    @ExcelProperty("5.5#")
    private String _5_5Yard;
    @ExcelProperty("6#")
    private String _6Yard;
    @ExcelProperty("6.5#")
    private String _6_5Yard;
    @ExcelProperty("7#")
    private String _7Yard;
    @ExcelProperty("7.5#")
    private String _7_5Yard;
    @ExcelProperty("8#")
    private String _8Yard;
    @ExcelProperty("8.5#")
    private String _8_5Yard;
    @ExcelProperty("9#")
    private String _9Yard;
    @ExcelProperty("9.5#")
    private String _9_5Yard;
    @ExcelProperty("10#")
    private String _10Yard;
    @ExcelProperty("10.5#")
    private String _10_5Yard;
    @ExcelProperty("11#")
    private String _11Yard;
    @ExcelProperty("11.5#")
    private String _11_5Yard;
    @ExcelProperty("12#")
    private String _12Yard;
    @ExcelProperty("12.5#")
    private String _12_5Yard;
    @ExcelProperty("13#")
    private String _13Yard;
    @ExcelProperty("13.5#")
    private String _13_5Yard;
    @ExcelProperty("14#")
    private String _14Yard;
    @ExcelProperty("14.5#")
    private String _14_5Yard;
}
