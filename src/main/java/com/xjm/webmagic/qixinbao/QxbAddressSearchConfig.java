package com.xjm.webmagic.qixinbao;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;

import java.util.List;

/**
 * @author baili
 * @date 2023年01月29日16:09
 */
@Data
public class QxbAddressSearchConfig {
    /**
     * 设置爬虫Cookie参数
     */
    //
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
            .addCookie("aliyungf_tc", "")
            .addCookie("","1674896431")
            .addCookie("flogger_fpid", "")
            .addCookie("fid","")
            .addCookie("acw_tc","")
            .addCookie("pdid","")
            .addCookie("ssxmod_itna","")
            .addCookie("ssxmod_itna2","")
            .addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/109.0.0.0 Safari/537.36");
    public static void ClimbOutInformation(List<String> keys){
        QxbPageProcessor qccPageProcessor = new QxbPageProcessor(keys);

        String urls = "https://www.qixin.com/search?key=" + StringUtils.join(keys,",https://www.qixin.com/search?key=");
        String[] urlsList = urls.split(",");
        System.out.println(urls);
        Spider.create(qccPageProcessor).addUrl(urlsList).thread(5).run();
        String filePath = "./启信宝企业名单-第二次全部爬取结果.xlsx";
        ExeclUtils.writeExcel(filePath,qccPageProcessor.getQccInfosAll());
        String filePathError = "./启信宝企业名单爬取版_error.xlsx";
        ExeclUtils.writeExcel(filePathError,qccPageProcessor.getQccInfosFail());


    }
}
