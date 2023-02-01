package com.xjm.webmagic.qixinbao;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.log4j.Log4j;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Log4j
/**
 * @author baili
 * @date 2023年01月29日16:05
 */
public class ExeclUtils {

    /**
     * 把所有企业信息写入excel
     */
    public  static void writeExcel(String filePath,List<QxbInfo> qccInfos) {
        OutputStream out = null;
        try {
            //String filePath = "./企查查企业名单爬取版.xlsx";
            String filePathTemp = "./企查查企业名单爬取版_temp.xlsx";

            File outFile = new File(filePath);
            File outFIleTemp = new File(filePathTemp);
            out = new FileOutputStream(filePath);
            ExcelWriter writer = null;
            writer = EasyExcel.write(outFile, QxbInfo.class).build();

            WriteSheet sheet1 = new WriteSheet();
            sheet1.setSheetName("企业信息");
            writer.write(qccInfos,sheet1);
            //writer.fill(qccInfos,sheet1);
            //System.out.println(qccInfos);
            writer.finish();
            //if (outFIleTemp.exists()){
            //    outFile.delete();
            //    outFIleTemp.renameTo(outFile);
            //}

            log.info("导出excel完成!");
        } catch (Exception e) {
            log.error(e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                log.error(e);
            }
        }

    }
    /**
     * 把需要查询的企业从excel读取
     */
    public static List<String> readExcel(String filePath) {
        // 被读取的文件绝对路径
        String fileName = filePath;

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<EnterpriseInformation> tmpList = EasyExcel.read(inputStream)
                // 设置与Excel表映射的类
                .head(EnterpriseInformation.class)
                // 设置sheet,默认读取第一个
                .sheet()
                // 设置标题所在行数
                .headRowNumber(1)
                // 异步读取
                .doReadSync();

        return tmpList.stream().map(EnterpriseInformation::getTitle).collect(Collectors.toList());
    }
}
