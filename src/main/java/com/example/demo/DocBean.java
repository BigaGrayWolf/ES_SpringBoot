package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data//注在类上，提供类的get、set、equals、hashCode、canEqual、toString方法
@NoArgsConstructor//注在类上，提供类的无参构造
@Accessors(chain = true)//Accessors存取器，@Accessors用于配置getter和setter方法的生成结果，chain的中文含义是链式的，设置为true，则setter方法返回当前对象。
@Document(indexName = "image", shards = 1, replicas = 0)//indexName:索引名，建议项目名称命名；type：类型（已经弃用），实体名称命名；shards:分区数；replicas:每个分区备份数
public class DocBean {

    @Id
    private Long id;

    @Field
    private String name;
//    @Field(type = FieldType.Keyword)
//    private String firstCode;
//
//    @Field(type = FieldType.Keyword)
//    private String secordCode;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")//analyzer 指定分词器 searchAnalyzer 查询时候使用的分词器
    private String content;

//    @Field(type = FieldType.Integer)
//    private Integer type;

//    public DocBean(Long id,String firstCode,String secordCode,String content,Integer type){
//        this.id=id;
//        this.firstCode=firstCode;
//        this.secordCode=secordCode;
//        this.content=content;
//        this.type=type;
//    }
    public DocBean(Long id,String name,String content){
        this.id=id;

        this.content=content;
        this.name = name;
    }
}

