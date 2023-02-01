package com.xjm.webmagic.qixinbao;

import com.xjm.webmagic.qixinbao.QxbAddressSearchConfig;
import com.xjm.webmagic.qixinbao.QxbInfo;
import lombok.extern.log4j.Log4j;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Qcc page processor.
 *
 * @author xiangjunming
 * @date 2019/07/30 16:58:16
 */
@Log4j
public class QxbPageProcessor implements PageProcessor {

    QxbAddressSearchConfig qccAddressSearchConfig = new QxbAddressSearchConfig();
    /**
     * 其他页标识
     */
    public static final String SEARCH = ".*?/search_index.*?";

    public static List<QxbInfo> qccInfosAll = new ArrayList<>();
    public static List<QxbInfo> qccInfosFail = new ArrayList<>();
    //
    /**
     * Instantiates a new Qcc page processor.
     *
     * @param keys the key
     */
    public QxbPageProcessor(List<String> keys) {
        this.keys = keys;
    }

    public  List<QxbInfo> getQccInfosAll(){
        return qccInfosAll;
    }
    public  List<QxbInfo> getQccInfosFail(){
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
            Selectable xpathMessage = page.getHtml().xpath("//div[@class='col-xs-24 p-25 margin-1-1x company-item']");

            //获取公司名称
            List<String> titles =
                    xpathMessage.xpath("//div[@class='company-title font-18 font-f6']/a/em").all();

            System.out.println("原始网站公司名称格式：" + titles);
            //处理获取到的公司名包含em标签的问题
            String title = String.join("",titles).replace("<em>", "").replace("</em>", "").trim();
            System.out.println("公司名称：" + title);
            //获取法定代表人
            String realNames = xpathMessage.xpath("//div[@class='legal-person m-t-12']/span[1]/a/text()").get();
            //System.out.println("公司法定代表人：" + realNames);

            //获取注册资本
            //去除单位，便于排序
            String registeredCapital =
                    xpathMessage.xpath("//div[@class='legal-person m-t-12']/span[2]/text()").get().replace(
                            "万人民币","");
            //System.out.println("公司注册资本：" + registeredCapital);
            //成立日期
            String registerDate =
                    xpathMessage.xpath("//div[@class='legal-person m-t-12']/span[3]/text()").get();
            //System.out.println("公司成立日期：" + registerDate);
            //获取公司电话
            String mobiles = xpathMessage.xpath("//div[@class='col-2-1']/div[4]/span[2]/text()").get();

            Thread.sleep(500);
            System.out.println("公司电话：" + mobiles);
            if( title.isEmpty() || registeredCapital.isEmpty()){
                log.error(page.getUrl()+"未获取到信息");
                System.out.println((page.getUrl()+"未获取到信息!!!!!!!!!!!!!"));
                qccInfosFail.add(new QxbInfo(title,realNames,registeredCapital,registerDate,mobiles));
            }else {
                qccInfosAll.add(new QxbInfo(title,realNames,registeredCapital,registerDate,mobiles));
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
    }
    @Override
    public Site getSite() {
        return qccAddressSearchConfig.getSite();
    }
}
