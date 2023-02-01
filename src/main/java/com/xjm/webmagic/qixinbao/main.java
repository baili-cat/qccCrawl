package com.xjm.webmagic.qixinbao;

import java.io.UnsupportedEncodingException;

import static com.xjm.webmagic.qixinbao.ExeclUtils.readExcel;
import static com.xjm.webmagic.qixinbao.QxbAddressSearchConfig.ClimbOutInformation;

/**
 * @author baili
 * @date 2023年01月29日16:15
 */
public class main {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public  static void main(String[] args) {
        //打印
        //qccInfos.addAll(ret);
        //writeExcel(qccInfos);
        String filePath = "./企业名单-八批全.xlsx";
        //System.out.println(readExcel(filePath));
        ClimbOutInformation(readExcel(filePath));
    }
}
