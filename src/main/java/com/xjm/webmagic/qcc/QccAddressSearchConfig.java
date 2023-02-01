package com.xjm.webmagic.qcc;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author baili
 * @date 2023年01月29日16:09
 */
@Data
public class QccAddressSearchConfig {
    /**
     * 设置爬虫Cookie参数
     * 微信
     */
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
            .addCookie("QCCSESSID", "")
            .addCookie("UM_distinctid", "")
            .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
    public static void ClimbOutInformation(List<String> keys){
        QccPageProcessor qccPageProcessor = new QccPageProcessor(keys);

        String urls = "https://www.qcc.com/search?key=" + StringUtils.join(keys,",https://www.qcc" +
                ".com/search?key=");
        String[] urlsList = urls.split(",");
        System.out.println(urls);
        Spider.create(qccPageProcessor).addUrl(urlsList).thread(1).run();
        String filePath = "./企查查企业名单爬取版.xlsx";
        ExeclUtils.writeExcel(filePath,qccPageProcessor.getQccInfosAll());
        String filePathError = "./企查查企业名单爬取版_error.xlsx";
        ExeclUtils.writeExcel(filePathError,qccPageProcessor.getQccInfosFail());


    }
}
