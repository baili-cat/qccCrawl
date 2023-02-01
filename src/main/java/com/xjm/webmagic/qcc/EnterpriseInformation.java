package com.xjm.webmagic.qcc;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author baili
 * @date 2023年01月29日15:02
 */
@Data
public class EnterpriseInformation {
    @ExcelProperty(value = "企业名称" ,index = 1)
    private String title;
    @ExcelProperty(value = "序号" ,index = 0)
    private String number;
    @Override
    public String toString() {
        return title;
    }
}
