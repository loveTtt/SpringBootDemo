package com.dream.demo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("investigation_situation")
public class InvestigationSituation {

    private Integer id;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("性别")
    private String gender;
    @ExcelProperty("年龄")
    private Integer age;
    //职位
    @ExcelProperty("职位")
    private String position;
    //招聘流程
    @ExcelProperty("招聘流程")
    private String process;
    //合理性
    @ExcelProperty("招聘合理性")
    private String rationality;
    //不合理原因
    @ExcelProperty("不合理原因")
    private String unreasonableReasons;
    //明确性
    @ExcelProperty("招聘明确性")
    private String clarity;
    //招聘渠道
    @ExcelProperty("招聘渠道")
    private String channel;
    @ExcelProperty("其他原因")
    private String otherReasons;
    //评估工作
    @ExcelProperty("评估工作")
    private String assess;
    //注重方面
    private String keyNotes;
    //评估标准
    private String evaluationCriteria;
    //是否计算成本
    private String hasCalculateCosts;
    //设计人
    private String designer;
    //招聘计划
    private String recruitmentPlan;
    //建议
    private String proposal;
}
