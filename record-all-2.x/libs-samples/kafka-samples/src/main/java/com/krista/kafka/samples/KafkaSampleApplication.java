package com.krista.kafka.samples;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

/**
 * @author wangdonghong
 */
public class KafkaSampleApplication {
    public static final ClassPathXmlApplicationContext CONTEXT =
            new ClassPathXmlApplicationContext("/kafka-producer.xml");

    public static void main(String[] args) {
        System.out.println("begin");
        for (int i = 0; i < 5; i++) {
            try {
                sendMessage("topic1", 0, null, "key", "kafka-test" + i);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("OK");
    }

    public static <K, T> void sendMessage(String topic, Integer partition, Long timestamp, K key, T data) {
        KafkaTemplate<K, T> kafkaTemplate = (KafkaTemplate<K, T>) CONTEXT.getBean("kafkaTemplate");
        ListenableFuture<SendResult<K, T>> listenableFuture = null;
        if (kafkaTemplate.getDefaultTopic().equals(topic)) {
            listenableFuture = kafkaTemplate.sendDefault(partition, timestamp, key, data);
        } else {
            listenableFuture = kafkaTemplate.send(topic, partition, timestamp, key, data);
        }
        //发送成功回调
        SuccessCallback<SendResult<K, T>> successCallback = result -> {
            //成功业务逻辑
            System.out.println("成功");
        };
        //发送失败回调
        FailureCallback failureCallback = ex -> {
            //失败业务逻辑
            throw new RuntimeException(ex);
        };
        listenableFuture.addCallback(successCallback, failureCallback);
    }
}
