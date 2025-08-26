## 🏍️基础

### [创建CompletableFuture的几种方式](CompletableFutureTest.java)

| --               |            |
| ---------------------------------------------- | ---------- |
| `runAsync` 异步执行，无返回值                  | `()->Void` |
| `supplyAsync` 异步执行，有返回值               | `()->T`    |
| `anyOf` 任意一个任务执行完成，就进行下一步动作 |            |
| `allOf `所有任务都执行完成，才进行下一步任务   |            |


### [CompletableFuture处理正常结果](CompletableFutureThenTest.java)

|              |            | 什么情况下执行   |
| ------------ | ---------- | ---------------- |
| *thenApply*  | ` T->U`    | 上一阶段正常结束 |
| *thenAccept* | `T->void`  | 上一阶段正常结束 |
| *thenRun*    | `()->void` | 上一阶段正常结束 |


### [CompletableFuture的三种异常处理](./error/)

|                |                      | 什么时候调用      | 是否修复future到正常状态 |
| -------------- | -------------------- | ----------------- | ------------------------ |
| *exceptionally*  | `exception -> T`     | 仅失败时 ❌        | ✅ 通过返回新值           |
| *handle*        | `(result, ex) -> T`  | 成功或失败都会 ✅❌ | ✅ 通过返回新值           |
| *whenComplete* | `(result, ex) -> void` | 成功或失败都会 ✅❌ | ❌仅观察，状态不变        |

## 🚀进阶
### [CompletableFuture的串行和并行执行](CompletableFutureTest2.java)

|                 --            |         |
| -------------------------------- | ------- |
| future返回值没接收，则结果被丢失 | `future1.thenApply` //这个结果被丢弃<br>`future1.thenAccept` //并发执行<br> |
| 串行执行 | `future1.thenApply.thenAccept` //串行执行 |
| 并行执行 | `future1.thenApply`<br/>`future1.thenAccept`<br/> |

### [CompletableFuture的业务代码和回调分别由哪个线程执行](CompletableFutureThreadTest.java)

| --                    |                                                              |
| --------------------- | ------------------------------------------------------------ |
| 同步方法(即不带Async) | `thenAccept`执行时，<br>- 如果CompletableFuture任务还在执行则由前述的线程执行；<br>- 否则则由调用的线程执行 |
| 异步方法(即带Async)   | - `CompletableFuture.supplyAsync`可以指定线程池；<br>- 不指定时使用默认的`ForkJoinPool`线程池 |


### [将方法转化为CompletableFuture形式](CompletableFutureConvertTest.java)
| --                              |
| ------------------------------- |
| 正常结束:complete(T)            |
| 报错时:completeExceptionally(E) |


### CompletableFuture.thenCompose
#### - [CompletableFuture.thenCompose的串行和并行](CompletableFutureComposeTest.java)

| --                    |                                                              |
| --------------------- | ------------------------------------------------------------ |
| `thenCompose`串行执行 | thenCompose(ignore -> {<br/>    return CompletableFuture.*runAsync*(() -> {<br/>        //do something<br/>    });<br/>}); |
| `thenCompose`并行执行 | future1 = CompletableFuture.*runAsync*<br />future2 = CompletableFuture.*runAsync*<br />future1.thenCompose(s -> future2)  只是说future1执行完成后会等future2执行完(但是其实2可能已经执行完了) |

#### - [CompletableFuture.thenCompose需要future1正常结束](CompletableFutureComposeTest2.java)

✈️✈️✈️

