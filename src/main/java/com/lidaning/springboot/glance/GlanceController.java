package com.lidaning.springboot.glance;

import com.lidaning.springboot.glance.util.MultipartFileToFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.expression.Maps;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/glance")
public class GlanceController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    WordDao wordDao;

    /**
     * glance首页
     */
    @RequestMapping("/")
    public String index(){
        log.info("execute glance.index");
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

            if(StringUtils.isEmpty(lineTxt)){
                if(!StringUtils.isEmpty(word.getWord())){
                    word.setImpDate(new Date());
                    temp.setId(null);
                    temp.setWord(word.getWord());
                    Example<Word> example = Example.of(temp);
                    if(wordDao.count(example)==0){
                        wordDao.save(word);
                    }
                    word_index=0;
                    word=new Word();
                }

                continue;
            }else{
                word_index++;

                switch (word_index){
                    case 1:
                        word.setWord(lineTxt);
                        break;
                    case 2:
                        word.setPronunciation(lineTxt);
                        break;
                    default:
                        word.setExplain(word.getExplain()==null?"":word.getExplain()+lineTxt);
                }
            }
        }

        if(!StringUtils.isEmpty(word.getWord())){
            word.setImpDate(new Date());
            wordDao.save(word);
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
        model.addAttribute("list", wordDao.findAll());
        return "/glance/impList";
    }

    /**
     * 删除单词
     */
    @RequestMapping("/del")
    public String del(Word word){
        wordDao.delete(word);
        return "redirect:impList";
    }


    /**
     * 统计信息
     */
    public String statistic(){

        return null;
    }
}
