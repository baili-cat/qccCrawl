package com.xjm.webmagic.qcc;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QccInfo extends BaseRowModel {
    @ExcelProperty(value = "企业名称" ,index = 0)
    private String title;
    @ExcelProperty(value = "法定代表人" ,index = 3)
    private String realName;
    @ExcelProperty(value = "注册资本（万元人民币)" ,index = 1)
    private String registeredCapital;
    @ExcelProperty(value = "成立日期" ,index =4)
    private String registerDate;
    @ExcelProperty(value = "电话" ,index = 2)
    private String mobile;

}
