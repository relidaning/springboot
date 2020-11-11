package com.lidaning.springboot.glance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class GlanceSchedule {

    Logger log= LoggerFactory.getLogger(getClass());

    @Autowired
    WordService wordService;

    @Autowired
    ImpStatService impStatService;

    @Scheduled(cron = "0 36 22 * * ?")
    public void impStat() throws ParseException {

        //查询昨天导入了多少单词
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,-24);
        String yesterdayDate=dateFormat.format(calendar.getTime());
        Word word=new Word();
        word.setImpDate(dateFormat.parse(yesterdayDate));
        List list=wordService.select(word);
        log.info("list.size="+(list==null?0:list.size()));
        if(list!=null&&list.size()>0){
            ImpStat impStat=new ImpStat();
            impStat.setImpDate(calendar.getTime());
            impStat.setImpNum(list.size());
            impStatService.insert(impStat);
        }

    }
}
