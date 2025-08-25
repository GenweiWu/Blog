
### 创建completableFuture的几种方式 

| --               |            |
| ---------------------------------------------- | ---------- |
| `runAsync` 异步执行，无返回值                  | `()->Void` |
| `supplyAsync` 异步执行，有返回值               | `()->T`    |
| `anyOf` 任意一个任务执行完成，就进行下一步动作 |            |
| `allOf `所有任务都执行完成，才进行下一步任务   |            |

- [创建completableFuture的几种方式](CompletableFutureTest.java)

### CompletableFuture的串行和并行执行

|                 --            |         |
| -------------------------------- | ------- |
| future返回值没接收，则结果被丢失 | `future1.thenApply` //这个结果被丢弃<br>`future1.thenAccept` //并发执行<br> |
| 串行执行 | `future1.thenApply.thenAccept` //串行执行 |
| 并行执行 | `future1.thenApply`<br/>`future1.thenAccept`<br/> |

