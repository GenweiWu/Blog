## disruptor使用入门


### 依赖
```xml
<dependency>
    <groupId>com.lmax</groupId>
    <artifactId>disruptor</artifactId>
    <version>3.3.2</version>
</dependency>
```

### 简单例子

#### （1）创建event和工厂

> OrderEvent.java 
```java
package com.njust.disruptor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@Getter
public class OrderEvent {
    private Integer id;
}
```

> OrderEventFactory.java
```java
package com.njust.disruptor;

import com.lmax.disruptor.EventFactory;

public class OrderEventFactory implements EventFactory<OrderEvent> {

    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}
```

#### （2）创建消费者

> OrderEventHandler.java
```java
package com.njust.disruptor;

import com.lmax.disruptor.EventHandler;

public class OrderEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(event);
    }
}
```


#### （3）创建生产者

> OrderEventProducer.java
```java
package com.njust.disruptor;

import com.lmax.disruptor.RingBuffer;

public class OrderEventProducer {

    private final RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(Integer data) {
        //1.可以把ringBuffer看成一个事件队列，则next是得到下一个事件槽
        long sequence = ringBuffer.next();

        //2.填充数据
        try {
            OrderEvent orderEvent = ringBuffer.get(sequence);
            orderEvent.setId(data);
        } finally {
            //3.将事件通过序号发布出去
            ringBuffer.publish(sequence);
        }
    }
}
```

#### （4）整合下

> DisruptorDemo.java

```java
package com.njust.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorDemo {

    //bufferSize must be a power of 2
    //2 * 2^n = 2 <<n
    private static final int BUFFER_SIZE = 2 << 9;

    /**
     * console:
     * <pre>
     * OrderEvent(id=0)
     * OrderEvent(id=1)
     * OrderEvent(id=2)
     * OrderEvent(id=3)
     * OrderEvent(id=4)
     * OrderEvent(id=5)
     * OrderEvent(id=6)
     * OrderEvent(id=7)
     * OrderEvent(id=8)
     * OrderEvent(id=9)
     * </pre>
     */
    public static void main(String[] args) {


        // 消费者线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 事件工厂
        OrderEventFactory eventFactory = new OrderEventFactory();
        Disruptor<OrderEvent> disruptor = new Disruptor<>(eventFactory, BUFFER_SIZE, executorService,
                ProducerType.SINGLE, new SleepingWaitStrategy());

        disruptor.handleEventsWith(new OrderEventHandler());
        disruptor.start();

        //生产者
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();
        OrderEventProducer orderEventProducer = new OrderEventProducer(ringBuffer);

        for (int i = 0; i < 10; i++) {
            orderEventProducer.onData(i);
        }
    }


}
```

