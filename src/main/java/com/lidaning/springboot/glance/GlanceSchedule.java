package com.lidaning.springboot.glance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class GlanceSchedule {

    Logger log= LoggerFactory.getLogger(getClass());

    @Autowired
    ScheduleRule scheduleRule;

    @Autowired
    WordService wordService;

    @Autowired
    ImpStatService impStatService;

    @Autowired
    MemoryInfoService memoryInfoService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Scheduled(cron = "0 0 1 * * ?")
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


    /**
     * 无效单词生成重复数据
     *  1，新导入单词+1天
     *  2，已经重复过的单词，如果重复日期过期，查询当前单词重复次数，上次重复日期+下次间隔天数
     *  3，已完成重复单词   TODO
     */
    @Scheduled(cron = "0 10 1 * * ?")
    public void genRepeatInfo() throws ParseException {
        log.info("execute scheduleMethod genRepeatInfo...");
        //1
        List<Word> words = jdbcTemplate.query(" select * from word w " +
                "  where not EXISTS (select 1 from memoryinfo m where m.wordId = w.id) ", new RowMapper<Word>() {
            @Override
            public Word mapRow(ResultSet resultSet, int i) throws SQLException {
                Word word = new Word();
                word.setId(resultSet.getLong("id"));
                word.setWord(resultSet.getString("word"));
                word.setPronunciation(resultSet.getString("pronunciation"));
                word.setDescription(resultSet.getString("description"));
                word.setImpDate(resultSet.getDate("impDate"));
                return word;
            }
        });

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for(Word w: words){
            MemoryInfo m = new MemoryInfo();
            m.setWordId(new Long(w.getId()).intValue());
            m.setRepeatTimes(1);
            m.setRepeatDate(sdf.parse(sdf.format(w.getImpDate())));
            memoryInfoService.insert(m);
        }

        //2

        List<MemoryInfo> memoryInfos = jdbcTemplate.query(" select wordid as wordId, rtimes as repeatTimes, rdate as repeatDate from ( " +
                " select wordid, max(repeatTimes) as rtimes, max(repeatDate) as rdate from memoryinfo " +
                " group by wordid  ) t" +
                " where date_format(t.rdate,'%Y-%m-%d') < "+sdf.format(new Date())+" ", new RowMapper<MemoryInfo>() {
            @Override
            public MemoryInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                MemoryInfo memoryInfo = new MemoryInfo();
                memoryInfo.setWordId(resultSet.getInt("wordId"));
                memoryInfo.setRepeatTimes(resultSet.getInt("repeatTimes"));
                memoryInfo.setRepeatDate(resultSet.getDate("repeatDate"));
                return memoryInfo;
            }
        });

        Calendar calendar=Calendar.getInstance();
        for(MemoryInfo m:memoryInfos){
            MemoryInfo temp=new MemoryInfo();

            int interval_days= 0;
            switch(m.getRepeatTimes()+1){
                case 2:
                    interval_days=Integer.valueOf(scheduleRule.getSecond());
                    break;
                case 3:
                    interval_days=Integer.valueOf(scheduleRule.getThird());
                    break;
                case 4:
                    interval_days=Integer.valueOf(scheduleRule.getFourth());
                    break;
                case 5:
                    interval_days=Integer.valueOf(scheduleRule.getFifth());
                    break;
                case 6:
                    interval_days=Integer.valueOf(scheduleRule.getSixth());
                    break;
                case 7:
                    interval_days=Integer.valueOf(scheduleRule.getSeventh());
                    break;
                case 8:
                    interval_days=Integer.valueOf(scheduleRule.getEighth());
                    break;


            }

            calendar.setTime(sdf.parse(sdf.format(m.getRepeatDate())));
            calendar.add(5, interval_days);
            temp.setWordId(m.getWordId());
            temp.setRepeatTimes(m.getRepeatTimes()+1);
            temp.setRepeatDate(calendar.getTime());
            memoryInfoService.insert(temp);

        }

    }


}
