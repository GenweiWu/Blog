package com.njust.kafka.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *  2024-04-22 16:19:04 INFO  ProducerConfig:361 - ProducerConfig values:
 * 	acks = 1
 * 	batch.size = 16384
 * 	bootstrap.servers = [localhost:9092]
 * 	buffer.memory = 33554432
 * 	client.dns.lookup = use_all_dns_ips
 * 	client.id = producer-1
 * 	compression.type = none
 * 	connections.max.idle.ms = 540000
 * 	delivery.timeout.ms = 120000
 * 	enable.idempotence = false
 * 	interceptor.classes = []
 * 	internal.auto.downgrade.txn.commit = false
 * 	key.serializer = class org.apache.kafka.common.serialization.StringSerializer
 * 	linger.ms = 0
 * 	max.block.ms = 60000
 * 	max.in.flight.requests.per.connection = 5
 * 	max.request.size = 1048576
 * 	metadata.max.age.ms = 300000
 * 	metadata.max.idle.ms = 300000
 * 	metric.reporters = []
 * 	metrics.num.samples = 2
 * 	metrics.recording.level = INFO
 * 	metrics.sample.window.ms = 30000
 * 	partitioner.class = class org.apache.kafka.clients.producer.internals.DefaultPartitioner
 * 	receive.buffer.bytes = 32768
 * 	reconnect.backoff.max.ms = 1000
 * 	reconnect.backoff.ms = 50
 * 	request.timeout.ms = 30000
 * 	retries = 2147483647
 * 	retry.backoff.ms = 100
 * 	sasl.client.callback.handler.class = null
 * 	sasl.jaas.config = null
 * 	sasl.kerberos.kinit.cmd = /usr/bin/kinit
 * 	sasl.kerberos.min.time.before.relogin = 60000
 * 	sasl.kerberos.service.name = null
 * 	sasl.kerberos.ticket.renew.jitter = 0.05
 * 	sasl.kerberos.ticket.renew.window.factor = 0.8
 * 	sasl.login.callback.handler.class = null
 * 	sasl.login.class = null
 * 	sasl.login.refresh.buffer.seconds = 300
 * 	sasl.login.refresh.min.period.seconds = 60
 * 	sasl.login.refresh.window.factor = 0.8
 * 	sasl.login.refresh.window.jitter = 0.05
 * 	sasl.mechanism = GSSAPI
 * 	security.protocol = PLAINTEXT
 * 	security.providers = null
 * 	send.buffer.bytes = 131072
 * 	socket.connection.setup.timeout.max.ms = 127000
 * 	socket.connection.setup.timeout.ms = 10000
 * 	ssl.cipher.suites = null
 * 	ssl.enabled.protocols = [TLSv1.2]
 * 	ssl.endpoint.identification.algorithm = https
 * 	ssl.engine.factory.class = null
 * 	ssl.key.password = null
 * 	ssl.keymanager.algorithm = SunX509
 * 	ssl.keystore.certificate.chain = null
 * 	ssl.keystore.key = null
 * 	ssl.keystore.location = null
 * 	ssl.keystore.password = null
 * 	ssl.keystore.type = JKS
 * 	ssl.protocol = TLSv1.2
 * 	ssl.provider = null
 * 	ssl.secure.random.implementation = null
 * 	ssl.trustmanager.algorithm = PKIX
 * 	ssl.truststore.certificates = null
 * 	ssl.truststore.location = null
 * 	ssl.truststore.password = null
 * 	ssl.truststore.type = JKS
 * 	transaction.timeout.ms = 60000
 * 	transactional.id = null
 * 	value.serializer = class org.apache.kafka.common.serialization.StringSerializer
 *
 * 2024-04-22 16:19:04 INFO  AppInfoParser:119 - Kafka version: 2.7.0
 * 2024-04-22 16:19:04 INFO  AppInfoParser:120 - Kafka commitId: 448719dc99a19793
 * 2024-04-22 16:19:04 INFO  AppInfoParser:121 - Kafka startTimeMs: 1713773944921
 * 2024-04-22 16:19:06 WARN  NetworkClient:1117 - [Producer clientId=producer-1] Error while fetching metadata with correlation id 2 : {topicName_001=LEADER_NOT_AVAILABLE}
 * 2024-04-22 16:19:06 INFO  Metadata:279 - [Producer clientId=producer-1] Cluster ID: ib_vOs0_TQypqvsdULmopA
 * </pre>
 */
@Slf4j
public class KafkaMessageProducer {

    private final KafkaProducer<String, String> producer;

    public KafkaMessageProducer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");    // 指定 Broker
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(properties);
    }

    /**
     * 同步发送消息
     */
    public void send(ProducerRecord<String, String> record) {
        try {
            producer.send(record).get(200, TimeUnit.MILLISECONDS);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * 异步发送消息
     */
    public void sendAsync(ProducerRecord<String, String> record) {
        try {
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null) {
                        log.error("sendAsync failed with record:{}", record, e);
                    } else {
                        log.info("sendAsync success with record:{}", record);
                    }
                }
            });
        } catch (Exception e) {
            log.error("sendAsync send failed with error", e);
        }

    }


    public static void main(String[] args) {
        KafkaMessageProducer kafkaMessageProducer = new KafkaMessageProducer();
        kafkaMessageProducer.send(new ProducerRecord<>("topicName_001", "key", UUID.randomUUID().toString()));
        //1.发送时不指定key,则key为空；
        kafkaMessageProducer.send(new ProducerRecord<>("topicName_001", UUID.randomUUID().toString()));
        //2.发送时，如果topic不存在，则会自动创建这个topic
    }
}
