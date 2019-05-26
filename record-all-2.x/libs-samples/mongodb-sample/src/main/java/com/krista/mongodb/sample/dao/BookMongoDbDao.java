package com.krista.mongodb.sample.dao;

import com.krista.mongodb.sample.model.Book;
import org.springframework.stereotype.Repository;

@Repository
public class BookMongoDbDao extends MongoDbBaseDao<Book> {
    @Override
    protected Class<Book> getEntityClass() {
        return Book.class;
    }
}
