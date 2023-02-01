package com.xjm.webmagic.qcc;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.xjm.webmagic.qcc.ExeclUtils.readExcel;

/**
 * The type Qcc page processor.
 *
 * @author xiangjunming
 * @date 2019/07/30 16:58:16
 */
@Log4j
public class QccPageProcessor implements PageProcessor {

    QccAddressSearchConfig qccAddressSearchConfig = new QccAddressSearchConfig();
    /**
     * 其他页标识
     */
    public static final String SEARCH = ".*?/search_index.*?";

    public static List<QccInfo> qccInfosAll = new ArrayList<>();
    public static List<QccInfo> qccInfosFail = new ArrayList<>();
    //
    /**
     * Instantiates a new Qcc page processor.
     *
     * @param keys the key
     */
    public QccPageProcessor(List<String> keys) {
        this.keys = keys;
    }

    public  List<QccInfo> getQccInfosAll(){
        return qccInfosAll;
    }
    public  List<QccInfo> getQccInfosFail(){
        return qccInfosFail;
    }
    /**
     * 搜索关键字
     */
    private List<String> keys;
    /**
     * 查询到的数据
     */


    @Override
    public void process(Page page) {
        //if (!page.getUrl().regex(SEARCH).match()) {
            //第一页添加其他页
            //从原有excel中读取所有企业
            //for(String  key : keys){
            //    try {
            //        key = URLEncoder.encode(key, "UTF-8");
            //        page.addTargetRequest("https://www.qcc.com/web/search?key=" + key);
            //
            //        //System.out.println(key);
            //    } catch (UnsupportedEncodingException e) {
            //        e.printStackTrace();
            //    }
            //}
        try {
            //获取匹配到的第一个公司的xpath信息
            Selectable xpathMessage = page.getHtml().xpath("//div[@class='search-cell']/table//tr[@class='frtrt " +
                    "tsd0']/td[3]/div[@class='maininfo']");
            //获取公司名称
            List<String> titles =
                    xpathMessage.xpath("//span[1]/span[1]/a[@class='title copy-value']/span/em").all();
            //System.out.println("原始网站公司名称格式：" + titles);
            //处理获取到的公司名包含em标签的问题
            String title = String.join("",titles).replace("<em>", "").replace("</em>", "").trim();
            System.out.println("公司名称：" + title);
            //获取法定代表人
            String realNames = xpathMessage.xpath("//div[@class='relate-info']/div[1]/span[1]/span[@class='val']/span" +
                    "/a/text()").get();
            //System.out.println("公司法定代表人：" + realNames);
            //获取注册资本
            //去除单位，便于排序
            String registeredCapital =
                    xpathMessage.xpath("//div[@class='relate-info']/div[1]/span[2]/span[@class" +
                            "='val']/text()").get().replace("万元人民币","");
            //System.out.println("公司注册资本：" + registeredCapital);
            //成立日期
            String registerDate =
                    xpathMessage.xpath("//div[@class='relate-info']/div[1]/span[3]/span[@class" +
                            "='val']/text()").get();
            //System.out.println("公司成立日期：" + registerDate);
            //获取公司电话
            String mobiles = xpathMessage.xpath("//div[@class='relate-info']/div[2]/span[1]/span[@class='val']/span[2" +
                    "]/text()").get();
            Thread.sleep(500);
            //System.out.println("公司电话：" + mobiles);
            if(title.isEmpty() || registeredCapital.isEmpty()){
                log.error(page.getUrl()+"未获取到信息");
                System.out.println((page.getUrl()+"未获取到信息!!!!!!!!!!!!!"));
                qccInfosFail.add(new QccInfo(title,realNames,registeredCapital,registerDate,mobiles));
            }else {
                qccInfosAll.add(new QccInfo(title,realNames,registeredCapital,registerDate,mobiles));
            }
            //打印

            //System.out.printf(qccInfos.toString()+"#######################");
            //page.putField("resultList", qccInfos);
        } catch (Exception e) {
            log.error(e);
        } finally {
//            cdl.countDown();
        }

        //}
        //ExeclUtils.writeExcel(qccInfosAll);
    }
    @Override
    public Site getSite() {
        return qccAddressSearchConfig.getSite();
    }
}
