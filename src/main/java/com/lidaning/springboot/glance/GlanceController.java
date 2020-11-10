package com.lidaning.springboot.glance;

import com.lidaning.springboot.glance.util.MultipartFileToFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/glance")
public class GlanceController {

    Logger log = LoggerFactory.getLogger(getClass());

    /*@Autowired
    WordDao wordDao;

    @Autowired
    JdbcTemplate jdbcTemplate;*/

    @Autowired
    WordService wordService;

    /**
     * glance首页
     */
    @RequestMapping("/index")
    public String index(){
        return "/glance/index";
    }

    /**
     * 导入单词
     */
    @RequestMapping("/impWord")
    public String impWord(){
        return "glance/impWord";
    }

    /**
     * 解析文件
     */
    @RequestMapping("/uploadWord")
    public String uploadWord(@RequestParam("file") MultipartFile file) throws Exception {

        File source= MultipartFileToFile.multipartFileToFile(file);
        InputStreamReader read = new InputStreamReader(new FileInputStream(source),"UTF-8");//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String lineTxt = null;
        int word_index=0;
        Word word=new Word();
        Word temp=new Word();
        while((lineTxt = bufferedReader.readLine()) != null){
            log.info("lineTxt = " + lineTxt);

            if(!lineTxt.trim().equals("")){
                lineTxt=lineTxt.trim();
                word_index++;

                switch (word_index){
                    case 1:
                        word.setWord(lineTxt);
                        break;
                    case 2:
                        word.setPronunciation(lineTxt);
                        break;
                    default:
                        word.setDescription(word.getDescription()==null?lineTxt:word.getDescription()+lineTxt);
                }
            }else{
                if(word != null && word.getWord()!=null && !word.getWord().trim().equals("")){
                    word.setImpDate(new Date());
                    temp.setId(null);
                    temp.setWord(word.getWord());
                    /*Example<Word> example = Example.of(temp);*/

//                    List list=jdbcTemplate.queryForList(" select * from word where word = '"+word.getWord().trim()+"' ");

                    List list=wordService.select(temp);
                    log.info(list==null?"0":String.valueOf(list.size()));

                    if(list==null||list.size()==0){
//                    if(wordDao.count(example)==0){

                        /*jdbcTemplate.update("INSERT INTO word (word, pronunciation, description ) VALUES (  '"+word.getWord().trim()+
                                "', '"+word.getPronunciation().replaceAll("'", "&#180;")+"', '"+word.getDescription()+"' ) ");*/

                        wordService.insert(word);

//                        wordDao.save(word);
                    }
                    word_index=0;
                    word=new Word();
                }

            }
        }

        if(word != null && word.getWord()!=null && !word.getWord().trim().equals("")){
            word.setImpDate(new Date());
                    temp.setId(null);
                    temp.setWord(word.getWord());
                    /*Example<Word> example = Example.of(temp);*/

//            List list=jdbcTemplate.queryForList(" select * from word where word = '"+word.getWord().trim()+"' ");

            List list=wordService.select(temp);
            log.info(list==null?"0":String.valueOf(list.size()));

            if(list==null||list.size()==0){
//                    if(wordDao.count(example)==0){

                /*jdbcTemplate.update("INSERT INTO word (word, pronunciation, description ) VALUES (  '"+word.getWord().trim()+
                        "', '"+word.getPronunciation().replaceAll("'", "&#180;")+"', '"+word.getDescription()+"' ) ");*/

                wordService.insert(word);

//                        wordDao.save(word);
            }
        }

        read.close();

        return "redirect:impList";
    }

    /**
     * 今天需要复习的单词
     */
    @RequestMapping("todayWord")
    public String todayWord(){

        return "/glance/todayWord";
    }

    /**
     * 导入记录列表
     */
    @RequestMapping("/impList")
    public String impList(Model model){
//        model.addAttribute("list", wordDao.findAll());

        /*List<Word> words = jdbcTemplate.query(" select * from word ", new RowMapper<Word>() {
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
        });*/

        List words=wordService.select(null);
        model.addAttribute("list", words);

        return "/glance/impList";
    }

    /**
     * 删除单词
     */
    @RequestMapping("/del")
    public String del(Word word){

//        jdbcTemplate.execute(" delete from word where id = '"+word.getId()+"' ");

//        wordDao.delete(word);

        wordService.delete(new Long(word.getId()).intValue());

        return "redirect:impList";
    }


    /**
     * 统计信息
     */
    public String statistic(){

        return null;
    }
}
