package com.lujie.mongo_demo.pojo;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Document(collection = "student")
public class Menu implements Serializable{


}
