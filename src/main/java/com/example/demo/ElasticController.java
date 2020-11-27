package com.example.demo;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;






@Log4j2//注在类上，提供对应的 Logger 对象，变量名为 log
@RestController//说明返回的是json
@SpringBootApplication
@RequestMapping("/elastic")//配置url映射
public class ElasticController {

    @Autowired
    private IElasticService elasticService;

    @GetMapping("/init")
    public void init(){

        elasticService.createIndex();
        List<DocBean> list =new ArrayList<>();
        list.add(new DocBean(1L,"m1","123"));
        list.add(new DocBean(2L,"m2","1234"));

        elasticService.saveAll(list);
    }

    @GetMapping("/all")
    public Iterator<DocBean> all(){
        return elasticService.findAll();
    }

    public static void main(String[] args) {
        SpringApplication.run(ElasticController.class, args);
    }
}
