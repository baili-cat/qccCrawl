package com.xjm.webmagic.qcc;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baili
 * @date 2023年01月29日16:36
 */
public class QccPipeline implements Pipeline {


    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取抓取过程中保存的数据
        List<QccInfo> qccInfos = resultItems.get("resultList");
        System.out.println(qccInfos);
    }
}
