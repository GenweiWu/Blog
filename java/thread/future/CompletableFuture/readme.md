## 🏍️基础

### [创建CompletableFuture的几种方式](CompletableFutureTest.java)

| --               |            |
| ---------------------------------------------- | ---------- |
| `runAsync` 异步执行，无返回值                  | `()->Void` |
| `supplyAsync` 异步执行，有返回值               | `()->T`    |
| `anyOf` 任意一个任务执行完成，就进行下一步动作 |            |
| `allOf `所有任务都执行完成，才进行下一步任务   |            |


### CompletableFuture单个阶段的then处理方法

#### > [CompletableFuture处理正常结果](CompletableFutureThenTest.java)
|              |            | 什么情况下执行   |
| ------------ | ---------- | ---------------- |
| *thenApply*  | ` T->U`    | 上一阶段正常结束 |
| *thenAccept* | `T->void`  | 上一阶段正常结束 |
| *thenRun*    | `()->void` | 上一阶段正常结束 |

#### > [依赖型组合方法中的异常](CompletableFutureThenErrorTest.java)

|                                       | 上一阶段异常完成后，方法是否会执行 | 是否会异常传递 |
| ------------------------------------- | ---------------------------------- | -------------- |
| `thenApply` / `thenAccept` /`thenRun` | ❌不会执行                          | ✅会传递异常    |
| `thenCompose`                         | ❌不会执行                          | ✅会传递异常    |


### [CompletableFuture的三种异常处理](CompletableFutureErrorTest.java)

|                |                      | 什么时候调用      | 是否修复future到正常状态 |
| -------------- | -------------------- | ----------------- | ------------------------ |
| *exceptionally*  | `exception -> T`     | 仅失败时 ❌        | ✅ 通过返回新值           |
| *handle*        | `(result, ex) -> T`  | 成功或失败都会 ✅❌ | ✅ 通过返回新值           |
| *whenComplete* | `(result, ex) -> void` | 成功或失败都会 ✅❌ | ❌仅观察，状态不变        |




## 🏍️[用于**组合两个独立的 CompletableFuture** ，且两个阶段都成功才会执行](CompletableFutureBothTest.java)

### > 两个阶段都成功才会执行
| 方法             | 核心接口        | 适用场景                   |
| :--------------- | :-------------- | :------------------------- |
| *thenCombine*    | `(T,U) -> V`    | 合并两个结果，生成新值     |
| *thenAcceptBoth* | `(T,U) -> void` | 消费两个结果，无返回值     |
| *runAfterBoth*   | `() -> void`    | 不关心结果，只关心完成状态 |

### > 异常时
|    方法          | **任何一个** Future 异常完成，组合操作是否会执行 | 是否会异常传递 |
| ---------------- | ------------------------------------------------ | -------------- |
| thenCombine      | ❌不会执行                                        | ✅会传递异常    |
| *runAfterBoth*   | ❌不会执行                                        | ✅会传递异常    |
| *thenAcceptBoth* | ❌不会执行                                        | ✅会传递异常    |


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

