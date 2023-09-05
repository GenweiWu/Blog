
## 使用wakeup方法，触发wakeupException

> 完整样例：
> https://github.com/gwenshap/kafka-examples/blob/master/SimpleMovingAvg/src/main/java/com/shapira/examples/newconsumer/simplemovingavg/SimpleMovingAvgNewConsumer.java  

### [1]触发wakeup
```java
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Starting exit...");
                // Note that shutdownhook runs in a separate thread, so the only thing we can safely do to a consumer is wake it up
                movingAvg.consumer.wakeup();  //[1]这里主线程调用了wakeup方法
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
```

### [2][3]consumer退出poll+close
```java
while (true) {
                ConsumerRecords<String, String> records = movingAvg.consumer.poll(1000);
                System.out.println(System.currentTimeMillis() + "  --  waiting for data...");
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
                //...
            }
        } catch (WakeupException e) {
            // ignore for shutdown
            //[2]捕获异常，只需忽略异常，使其退出poll方法
        } finally {
            movingAvg.consumer.close();  //[3]关闭consumer
            System.out.println("Closed consumer and we are done");
        }
```
