package com.njust.kafka.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * <pre>
 * 2024-04-22 16:09:28 INFO  ConsumerConfig:361 - ConsumerConfig values:
 * allow.auto.create.topics = true
 * auto.commit.interval.ms = 5000
 * auto.offset.reset = latest
 * bootstrap.servers = [localhost:9092]
 * check.crcs = true
 * client.dns.lookup = use_all_dns_ips
 * client.id = consumer-test_service_01-1
 * client.rack =
 * connections.max.idle.ms = 540000
 * default.api.timeout.ms = 60000
 * enable.auto.commit = true
 * exclude.internal.topics = true
 * fetch.max.bytes = 52428800
 * fetch.max.wait.ms = 500
 * fetch.min.bytes = 1
 * group.id = test_service_01
 * group.instance.id = null
 * heartbeat.interval.ms = 3000
 * interceptor.classes = []
 * internal.leave.group.on.close = true
 * internal.throw.on.fetch.stable.offset.unsupported = false
 * isolation.level = read_uncommitted
 * key.deserializer = class org.apache.kafka.common.serialization.StringDeserializer
 * max.partition.fetch.bytes = 1048576
 * max.poll.interval.ms = 300000
 * max.poll.records = 500
 * metadata.max.age.ms = 300000
 * metric.reporters = []
 * metrics.num.samples = 2
 * metrics.recording.level = INFO
 * metrics.sample.window.ms = 30000
 * partition.assignment.strategy = [class org.apache.kafka.clients.consumer.RangeAssignor]
 * receive.buffer.bytes = 65536
 * reconnect.backoff.max.ms = 1000
 * reconnect.backoff.ms = 50
 * request.timeout.ms = 30000
 * retry.backoff.ms = 100
 * sasl.client.callback.handler.class = null
 * sasl.jaas.config = null
 * sasl.kerberos.kinit.cmd = /usr/bin/kinit
 * sasl.kerberos.min.time.before.relogin = 60000
 * sasl.kerberos.service.name = null
 * sasl.kerberos.ticket.renew.jitter = 0.05
 * sasl.kerberos.ticket.renew.window.factor = 0.8
 * sasl.login.callback.handler.class = null
 * sasl.login.class = null
 * sasl.login.refresh.buffer.seconds = 300
 * sasl.login.refresh.min.period.seconds = 60
 * sasl.login.refresh.window.factor = 0.8
 * sasl.login.refresh.window.jitter = 0.05
 * sasl.mechanism = GSSAPI
 * security.protocol = PLAINTEXT
 * security.providers = null
 * send.buffer.bytes = 131072
 * session.timeout.ms = 10000
 * socket.connection.setup.timeout.max.ms = 127000
 * socket.connection.setup.timeout.ms = 10000
 * ssl.cipher.suites = null
 * ssl.enabled.protocols = [TLSv1.2]
 * ssl.endpoint.identification.algorithm = https
 * ssl.engine.factory.class = null
 * ssl.key.password = null
 * ssl.keymanager.algorithm = SunX509
 * ssl.keystore.certificate.chain = null
 * ssl.keystore.key = null
 * ssl.keystore.location = null
 * ssl.keystore.password = null
 * ssl.keystore.type = JKS
 * ssl.protocol = TLSv1.2
 * ssl.provider = null
 * ssl.secure.random.implementation = null
 * ssl.trustmanager.algorithm = PKIX
 * ssl.truststore.certificates = null
 * ssl.truststore.location = null
 * ssl.truststore.password = null
 * ssl.truststore.type = JKS
 * value.deserializer = class org.apache.kafka.common.serialization.StringDeserializer
 * <p>
 * 2024-04-22 16:09:29 INFO  AppInfoParser:119 - Kafka version: 2.7.0
 * </pre>
 */
@Slf4j
public class KafkaMessageConsumer {

    private final KafkaConsumer<String, String> consumer;

    public KafkaMessageConsumer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");  // 指定 Broker
        properties.put("group.id", "test_service_01");              // 指定消费组群 ID
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(properties);
        //1.订阅时，如果topic不存在则自动创建
        //感觉跟这个配置有关
        consumer.subscribe(Collections.singleton("topicName_105"));  // 订阅主题

        new Thread(this::consumerRun).start();
    }

    private void consumerRun() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    String info = String.format("[Topic: %s][Partition:%d][Offset:%d][Key:%s][Message:%s]",
                            record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    log.info("Received:" + info);
                }
            }
        } finally {
            consumer.close();
        }
    }

    public static void main(String[] args) {
        KafkaMessageConsumer kafkaMessageConsumer = new KafkaMessageConsumer();
    }


}
