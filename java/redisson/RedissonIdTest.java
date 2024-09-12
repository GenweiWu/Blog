package com.njust.redisson;

import org.junit.BeforeClass;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RIdGenerator;
import org.redisson.api.RedissonClient;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class RedissonIdTest {


    private static RedissonClient redissonClient;

    @BeforeClass
    public static void init() {
        redissonClient = Redisson.create();
    }

    /**
     * 1、不自定义设置的话，默认从1开始，每次申请5000
     * <p>
     * 2、每次调用getIdGenerator后，就会重新申请5000个
     *
     * <ol>
     * <li>===2===前面打印出来的id是连续的</li>
     * <li>===2===之后和之前是断层的，感觉是重新预申请了5000个id</li>
     * </ol>
     *
     * <pre>
     * 20001
     * 20002
     * === 1 ===
     * 20003
     * 20004
     * === 2 ===
     * 25001
     * 25002
     * </pre>
     */
    @Test
    public void nextId() {
        RIdGenerator idGenerator = redissonClient.getIdGenerator("test");
        for (int i = 0; i < 2; i++) {
            long l = idGenerator.nextId();
            System.out.println(l);
        }

        System.out.println("=== 1 ===");
        for (int i = 0; i < 2; i++) {
            long l = idGenerator.nextId();
            System.out.println(l);
        }

        System.out.println("=== 2 ===");
        idGenerator = redissonClient.getIdGenerator("test");
        for (int i = 0; i < 2; i++) {
            long l = idGenerator.nextId();
            System.out.println(l);
        }
    }

    /**
     * 3、通过tryInit方法，自定义预生成的id起始数字和大小
     *
     * <p>
     * 会在redis中对应生成以下数据
     * <ol>
     *     <li>{test2}:allocation = 10</li>
     *     <li>test2 = 41</li>
     * </ol>
     *
     * <pre>
     * init:false
     * 21
     * 22
     * 23
     * 24
     * 25
     * 26
     * 27
     * 28
     * 29
     * 30
     * 31
     * 32
     * 33
     * 34
     * 35
     * 36
     * 37
     * 38
     * 39
     * 40
     * </pre>
     */
    @Test
    public void customInit() {
        RIdGenerator idGenerator = redissonClient.getIdGenerator("test2");

        //这里主动设置大小为10，否则默认是5000
        boolean success = idGenerator.tryInit(1, 10);
        //只要运行过了，redis里就有记录，这个会返回false
        System.out.println("init:" + success);
        for (int i = 0; i < 10; i++) {
            System.out.println(idGenerator.nextId());
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(idGenerator.nextId());
        }
    }

    /**
     * 4、尝试tryInit重新初始化，发现是不行的
     *
     * <pre>
     * nextId:41
     * init:false
     * nextId:42
     * init:false
     * nextId:43
     * </pre>
     */
    @Test
    public void initWithOtherIndex() {
        RIdGenerator idGenerator = redissonClient.getIdGenerator("test2");

        System.out.println("nextId:" + idGenerator.nextId());

        boolean success = idGenerator.tryInit(100, 10);
        System.out.println("init:" + success);
        System.out.println("nextId:" + idGenerator.nextId());

        success = idGenerator.tryInit(200, 10);
        System.out.println("init:" + success);
        System.out.println("nextId:" + idGenerator.nextId());
    }

    /**
     * 5、尝试通过 getIdGenerator+tryInit 重新设置参数，发现是失败的
     *
     * <pre>
     * nextId:51
     * init:false
     * nextId:52
     * init:false
     * nextId:61
     * </pre>
     */
    @Test
    public void initWithOtherIndex222() {
        //111
        RIdGenerator idGenerator = redissonClient.getIdGenerator("test2");

        System.out.println("nextId:" + idGenerator.nextId());

        boolean success = idGenerator.tryInit(100, 10);
        System.out.println("init:" + success);
        System.out.println("nextId:" + idGenerator.nextId());

        //222
        idGenerator = redissonClient.getIdGenerator("test2");

        success = idGenerator.tryInit(200, 10);
        System.out.println("init:" + success);
        System.out.println("nextId:" + idGenerator.nextId());
    }

    /**
     * 6、过期设置
     *
     * <p>我现在测试的效果是：存在设置失效失败的情况，不知道是不是本地部署redis导致的</p>
     */
    @Test
    public void expireTest() {
        //
        long prefix = 20240930163000L;
        RIdGenerator idGenerator = redissonClient.getIdGenerator(String.valueOf(prefix));
        Duration duration = Duration.of(10, ChronoUnit.SECONDS);
        boolean expire = idGenerator.expire(duration);
        System.out.println("expire set:" + expire);

        for (int i = 0; i < 10; i++) {
            System.out.println(prefix + "_" + idGenerator.nextId());
        }

        System.out.println("===2===");

        prefix = 20240930163001L;
        idGenerator = redissonClient.getIdGenerator(String.valueOf(prefix));
        expire = idGenerator.expire(duration);
        System.out.println("expire set:" + expire);

        for (int i = 0; i < 10; i++) {
            System.out.println(prefix + "_" + idGenerator.nextId());
        }

    }

}
