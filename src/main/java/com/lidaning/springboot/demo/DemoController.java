package com.lidaning.springboot.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/demo")
public class DemoController {

    Logger log= LoggerFactory.getLogger(getClass());

    @Autowired
    private DemoDao demoDao;


    @RequestMapping("/list")
    public String list(Model model){
        model.addAttribute("list", demoDao.findAll());
        return "/demo/list";
    }

    @RequestMapping("/save")
    public String save(HttpServletRequest request, Demo demo, @RequestParam("file") MultipartFile file) throws IOException {
        demoDao.save(demo);
//        String upload_path=request.getServletContext().getRealPath("classpath:/static/upload");
        String upload_path= ResourceUtils.getURL("classpath:").getPath();
        log.info("upload_path=" +upload_path);
        File dest=new File(upload_path + "/static/upload/1.png");
        file.transferTo(dest);

        return "redirect:list";
    }

    @RequestMapping("/edit")
    public String edit(){

        return "/demo/edit";
    }

    @RequestMapping("/view")
    public String view(Demo demo, Model model){
        model.addAttribute(demoDao.findById(demo.getId()).get());
        return "/demo/view";
    }

    @RequestMapping("/del")
    public String del(Demo demo){
        demoDao.delete(demo);
        return "redirect:list";
    }
}
