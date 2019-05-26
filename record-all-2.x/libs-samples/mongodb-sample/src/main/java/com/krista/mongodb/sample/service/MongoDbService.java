package com.krista.mongodb.sample.service;

import com.krista.mongodb.sample.dao.BookMongoDbDao;
import com.krista.mongodb.sample.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MongoDbService {
    private static final Logger logger = LoggerFactory.getLogger(MongoDbService.class);
    private static final String collectionName = "Books";
    @Autowired
    private BookMongoDbDao bookMongoDbDao;

    public String saveBook(Book book) {
        logger.info("save start......");
        book.setCreateTime(new Date());
        book.setUpdateTime(new Date());
        bookMongoDbDao.save(book);

        return "success";
    }
}
