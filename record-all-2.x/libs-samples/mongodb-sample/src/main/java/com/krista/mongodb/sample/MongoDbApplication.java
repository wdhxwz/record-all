package com.krista.mongodb.sample;


import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * mongodb test
 */
@SpringBootApplication
public class MongoDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoDbApplication.class, args);
    }

    private void test() {
        MongoClient mongoClient = new MongoClient(
                "47.98.105.37"
        );

        // MongoDatabase mongoDatabase = mongoClient.getDatabase("config");
        // MongoCollection<Document> collection = mongoDatabase.getCollection("System");

        for (String name : mongoClient.listDatabaseNames()) {
            System.out.println(name);
        }

        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = mongoDatabase.getCollection("Student");
        for (Document document : collection.find()) {
            System.out.println(document);
        }
    }
}
