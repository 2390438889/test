package com.lujie.mongo_demo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import javafx.scene.shape.Circle;
import org.bson.BsonArray;
import org.bson.BsonObjectId;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.Doc;
import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {
    @Autowired
    MongoTemplate mongoTemplate;
    private MongoCollection mongoCollection;
    @Before
    public void before(){
        mongoCollection=mongoTemplate.getCollection("student");
    }
    @Test
    public void mongoFind(){
        FindIterable findIterable=mongoCollection.find();
        MongoCursor mongoCursor=findIterable.iterator();
        Document document=null;
        Object obj=null;
        while(mongoCursor.hasNext()){
            document=(Document) mongoCursor.next();
            obj=document.get("username");
            if(obj!=null)
             System.out.println(document);
        }
    }
    @Test
    public void mongoFindAll(){
        print(mongoTemplate.find(Query.query(Criteria.where("addList").exists(true)),User.class));
    }
    public void print(List list){
        for (Object obj:list
             ) {
            System.out.println(obj);
        }
    }
    @Test
    public void mongoFindByClass(){
       System.out.println(mongoTemplate.find(Query.query(Criteria.where("username").exists(true)),User.class));
    }
    @Test
    public void mongoInsert(){
        Document document=new Document();
        document.put("username","张三");
        document.put("password","123456");
        document.put("sex","男");
        mongoCollection.insertOne(document);
    }

    @Test
    public void mongoFindGroup(){
        Aggregation aggregation=Aggregation.newAggregation(
                //第一步：挑选所需的字段
                Aggregation.project("username","password"),
                //第二部：筛选符合条件的记录
                Aggregation.match(Criteria.where("password").gt("111111")),
                //第三部：分组字段
                Aggregation.group("password").count().as("count").last("password").as("password"),
                //重新挑选字段
                Aggregation.project("password","count")
        );
        System.out.println(mongoTemplate.aggregate(aggregation,"student",Map.class).getMappedResults());
    }

    @Test
    public void mongoClassInsert(){
        Address add[]={new Address("湖南省长沙市长沙县")};
        List<Address> list= Arrays.asList(add);
        mongoTemplate.insertAll(list);
        list=mongoTemplate.find(Query.query(Criteria.where("add_info").exists(true)),Address.class);
        User user=new User("侯志江","123456","男",list);
        mongoTemplate.insert(user);
    }
    @Test
    public void mongoFindById(){
        System.out.println(mongoTemplate.findOne(Query.query(Criteria.where("_id").is("5b72391c84bb7a1c3db02d0a")),User.class));
    }

    public User mongoFindById(String id){
        return mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)),User.class);
    }
    @Test
    public void mongoUpdate(){
       User user=mongoFindById("5b72391c84bb7a1c3db02d0a");
       user.setSex("女");
       mongoTemplate.save(user);
       mongoTemplate.updateFirst(Query.query(Criteria.where("_id").is("5b72391c84bb7a1c3db02d0a")), Update.update("sex","男"),"student");
    }
    @Test
    public void mongoDelete(){
        mongoTemplate.remove(Query.query(Criteria.where("_id").is("5b72391c84bb7a1c3db02d0a")),"student");
    }


}
