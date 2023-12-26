
## ProducerType
```
SINGLE - 单生产者。这是最常见的情况，其中只有一个线程或者是一个EventProcessor将事件发布到RingBuffer中。

MULTI - 多生产者。在这种情况下，有多个线程或者是多个EventProcessor将事件发布到RingBuffer中。
每个生产者都有自己的Sequence，并且在发布事件时，需要使用Sequencer的next()方法来获取下一个可用的序列号。
```

## FAQ 
### 单线程模式实际使用多线程会出现数据丢失问题

> https://www.lixin.help/2019/10/07/Disruptor-ProducerType.html  
```java
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class DisruptorTest3 {
	public static void main(String[] args) throws Exception {
		AtomicInteger inc = new AtomicInteger(1);
		// 1. 初始化线程池工厂s
		ThreadFactory factory = (r) -> {
			int index = inc.getAndIncrement();
			Thread t = new Thread(r);
			t.setName("disruptor " + index);
			return t;
		};

		// 2. 初始化RingBuffer的大小,必须是2的指数
		int bufferSize = 1024;

		// 3.Event处理器(消费者)
		LongEventHandler consumerHandler = new LongEventHandler();

		// 默认生产者为:多线程模式
//		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent.EVENT_FACTORY, bufferSize, factory);
		// 多线程模式(多线程模式情况下,会存在吞吐量有所下降,但能保证不丢失数据)
//		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent.EVENT_FACTORY, bufferSize, factory,ProducerType.MULTI,new BlockingWaitStrategy());
		// 单线程模式(注意:单线程模式情况下,却开启了多线程,会存在丢数据的可能性)
		Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(LongEvent.EVENT_FACTORY, bufferSize, factory,
				ProducerType.SINGLE, new BlockingWaitStrategy());
		
		// 指定消费者
		disruptor.handleEventsWith(consumerHandler);
		// 该方法只能调用一次,并且所有的EventHandler必须在start之前添加,包括:ExeceptionHandler
		disruptor.start();

		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		// 自定义生产者
		LongEventProducer producer = new LongEventProducer(ringBuffer);

		int count = 10;
		final CyclicBarrier barrier = new CyclicBarrier(count);
		ExecutorService executor = Executors.newCachedThreadPool();
		for (long i = 0; i < count; i++) {
			final long data = i;
			executor.submit(() -> {
				try {
					System.out.println(data + " is ready...");
					barrier.await();
					producer.publish(data);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		TimeUnit.SECONDS.sleep(1000000);
		disruptor.shutdown();
	}
}
```
