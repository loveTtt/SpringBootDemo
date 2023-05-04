package com.dream.demo.converter;

import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateConverter implements Converter<Date> {
    @Override
    public Class supportJavaTypeKey() {
        return Date.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Date convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration){
        if (cellData.getType().equals(CellDataTypeEnum.NUMBER)) {
            LocalDate localDate = LocalDate.of(1900, 1, 1);
            //excel 有些奇怪的bug, 导致日期数差2
            localDate = localDate.plusDays(cellData.getNumberValue().longValue() - 2);
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else if (cellData.getType().equals(CellDataTypeEnum.STRING)) {
            String dateString = cellData.getStringValue();
            return DateUtil.parse(dateString,"yyyy/MM/dd");
        } else {
            return null;
        }
    }



}
