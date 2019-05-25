package com.krista.mongodb.sample.controller;

import com.krista.mongodb.sample.model.Book;
import com.krista.mongodb.sample.service.MongoDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mongo")
public class MongoController {
    @Autowired
    private MongoDbService mongoDbService;

    @RequestMapping("/save")
    public String save(){
        Book book = new Book();
        book.setDescription("消暑");
        book.setId("1");
        book.setName("从ta的世界路过");
        book.setPrice(12);
        book.setPublish("某某出版社");

        return mongoDbService.saveBook(book);
    }
}
