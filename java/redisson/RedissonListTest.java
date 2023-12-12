package com.njust.redisson;

import org.junit.Assert;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RDeque;
import org.redisson.api.RList;
import org.redisson.api.RQueue;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.ListAddListener;
import org.redisson.api.listener.ListRemoveListener;
import org.redisson.client.codec.DoubleCodec;
import org.redisson.client.codec.IntegerCodec;
import org.redisson.client.codec.StringCodec;

import java.util.List;
import java.util.stream.Stream;

public class RedissonListTest {

    @Test
    public void testList() {

        RedissonClient redissonClient = Redisson.create();
        RList<String> list = redissonClient.getList("test:list:list", StringCodec.INSTANCE);
        list.clear();

        //=RPUSH
        list.add("L1");
        list.add("L2");
        list.add("L3");

        //size=LLEN
        Assert.assertEquals(3, list.size());

        //get=LINDEX
        Assert.assertEquals("L1", list.get(0));
        Assert.assertEquals("L2", list.get(1));
        Assert.assertEquals("L3", list.get(2));

        //range=LRANGE
        List<String> range = list.range(0, 2);
        String[] expect = Stream.of("L1", "L2", "L3").toArray(String[]::new);
        Assert.assertArrayEquals(expect, range.toArray(new String[0]));

        range = list.range(1, 2);
        expect = Stream.of("L2", "L3").toArray(String[]::new);
        Assert.assertArrayEquals(expect, range.toArray(new String[0]));
    }

    /**
     * 如果想支持listener，则需要修改redis.conf
     * <pre>
     *     {@code
     *     notify-keyspace-events "KEA"
     *     }
     * </pre>
     */
    @Test
    public void testListListener() {
        RedissonClient redissonClient = Redisson.create();
        RList<String> list = redissonClient.getList("test:list:list", StringCodec.INSTANCE);
        list.clear();

        list.addListener(new ListAddListener() {
            @Override
            public void onListAdd(String name) {
                System.out.println("add:" + name);
            }
        });
        //没触发，感觉水太深，暂不建议使用
        list.addListener(new ListRemoveListener() {
            @Override
            public void onListRemove(String name) {
                System.out.println("remove:" + name);
            }
        });

        list.add("aaa");
        list.remove("aaa");
        list.remove("bbb");
    }

    /**
     * queue队列，先进先出
     */
    @Test
    public void testQueue() {
        RedissonClient redissonClient = Redisson.create();
        RQueue<Integer> queue = redissonClient.getQueue("test:list:queue", IntegerCodec.INSTANCE);
        queue.clear();

        //=RPUSH
        queue.add(101);
        queue.add(102);

        //=LPOP
        Integer poll = queue.poll();
        Assert.assertNotNull(poll);
        Assert.assertEquals(101, (int) poll);

        poll = queue.poll();
        Assert.assertNotNull(poll);
        Assert.assertEquals(102, (int) poll);

        //expect null
        poll = queue.poll();
        Assert.assertNull(poll);
    }

    /**
     * 想使用lpush、rpush
     *
     * @see <a href="https://github.com/redisson/redisson/wiki/11.-Redis-commands-mapping">参考</a>
     */
    @Test
    public void testDeque() {
        RedissonClient redissonClient = Redisson.create();
        RDeque<Double> deque = redissonClient.getDeque("test:list:deque", DoubleCodec.INSTANCE);
        deque.clear();

        //addFirst=LPUSH
        deque.addFirst(1.0);
        deque.addFirst(2.0);
        //addLast=RPUSH
        deque.addLast(4.0);

        //=LRANGE
        Double[] array = deque.toArray(new Double[0]);
        Double[] expect = {2.0, 1.0, 4.0};
        Assert.assertArrayEquals(expect, array);
    }

    @Test
    public void testDequeExist() {
        RedissonClient redissonClient = Redisson.create();
        RDeque<Double> deque = redissonClient.getDeque("test:list:deque", DoubleCodec.INSTANCE);
        deque.delete();

        //不存在则不添加=LPUSHX
        int len = deque.addFirstIfExists(12.2);
        Assert.assertEquals(0, len);

        //RPUSH
        deque.add(0.0);
        //LPUSHX
        len = deque.addFirstIfExists(12.2);
        Assert.assertEquals(2, len);
    }
}
