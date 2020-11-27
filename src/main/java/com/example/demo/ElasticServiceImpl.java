package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Iterator;
import java.util.List;


//这个类应该用泛型去写
@Service("elasticService")
public class ElasticServiceImpl implements IElasticService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @Autowired
    private ElasticRepository elasticRepository;

    private Pageable pageable = PageRequest.of(0,10);//是一个分页方法，规定起始页为0，每页大小为10

    @Override
    public void createIndex() {//这边以后直接用indexName吧
        if(elasticsearchTemplate.indexExists(DocBean.class)){
            elasticsearchTemplate.deleteIndex(DocBean.class);
        }
        elasticsearchTemplate.createIndex(DocBean.class);
    }

    @Override
    public void deleteIndex(String index) {
        elasticsearchTemplate.deleteIndex(index);
    }

    @Override
    public void save(DocBean docBean) {
        elasticRepository.save(docBean);
    }

    @Override
    public void saveAll(List<DocBean> list) {
        elasticRepository.saveAll(list);
    }

    @Override
    public Iterator<DocBean> findAll() {
        return elasticRepository.findAll().iterator();
    }

//    @Override
//    public Page<DocBean> findByContent(String content) {
//        return elasticRepository.findByContent(content,pageable);
//    }
//
//    @Override
//    public Page<DocBean> findByFirstCode(String firstCode) {
//        return elasticRepository.findByFirstCode(firstCode,pageable);
//    }
//
//    @Override
//    public Page<DocBean> findBySecordCode(String secordCode) {
//        return elasticRepository.findBySecordCode(secordCode,pageable);
//    }
//
    @Override
    public Page<DocBean> query(String key) {
        return elasticRepository.findByContent(key,pageable);
    }
}
