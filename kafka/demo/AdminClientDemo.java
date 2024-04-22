package com.njust.kafka.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.*;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Slf4j
public class AdminClientDemo {

    private AdminClient adminClient;

    public AdminClientDemo() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("client.id", "admin_client_001");
        adminClient = AdminClient.create(properties);
    }

    public boolean checkTopicExist(String topicName) throws ExecutionException, InterruptedException {
        try {
            DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Collections.singletonList(topicName));
            Map<String, TopicDescription> stringTopicDescriptionMap = describeTopicsResult.all().get();
            if (!stringTopicDescriptionMap.containsKey(topicName)) {
                log.error("checkTopicExist failed,cannot find topic:{}", topicName);
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("checkTopicExist failed with error,cannot find topic:{}", topicName, e);
            return false;
        }
    }

    public void createTopic(String topicName) throws ExecutionException, InterruptedException {
        //topic的分区数：数据平铺到多个分区,分区存储不同的数据; 多个消费者可以同时消费不同的分区，提升消费的速度
        //topic的副本数：可以理解为多个备份；是为了增加系统的可靠性
        NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
        CreateTopicsResult createTopicsResult = adminClient.createTopics(Collections.singletonList(newTopic));

        createTopicsResult.all().get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AdminClientDemo adminClientDemo = new AdminClientDemo();

        System.out.println(adminClientDemo.checkTopicExist("topicName_001")); //存在的topic
        System.out.println(adminClientDemo.checkTopicExist("topicName_notExist")); //不存在的topic

        adminClientDemo.createTopic("topicName_create_001");
    }
}
