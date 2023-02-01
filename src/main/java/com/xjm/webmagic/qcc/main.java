package com.xjm.webmagic.qcc;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static com.xjm.webmagic.qcc.ExeclUtils.readExcel;
import static com.xjm.webmagic.qcc.QccAddressSearchConfig.ClimbOutInformation;

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
        //泸州，南充，乐山，内江，自贡，广安
        //new QccPageProcessor("广安市金桂建材有限公司").start();
        //List<QccInfo> ret = new ArrayList<>();
        //ret.add(new QccInfo("广安市金桂建材有限公司","张宗英","2600万元人民币","2011-08-09","0826-5693989"));
        //ret.add(new QccInfo("广安市金桂建材有限公司12","张宗英12","2600万元人民币12","2011-08-0912","0826-56939891"));

        //打印
        //qccInfos.addAll(ret);
        //writeExcel(qccInfos);
        String filePath = "./企业名单.xlsx";
        //System.out.println(readExcel(filePath));
        ClimbOutInformation(readExcel(filePath));
    }
}
