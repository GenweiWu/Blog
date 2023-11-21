> https://lmax-exchange.github.io/disruptor/user-guide/index.html  
> 
| 策略 | 性能 | CPU占用 | 低延迟 | 备注 |
| ------------------------ | ---- | ---- | ---- | ---- |
| **BlockingWaitStrategy** `*`   | 最慢 | 最低 | X |  |
| **SleepingWaitStrategy** `**` | 慢 | 低 | X | 不追求高性能，不要求实时性，例如异步打印日志 |
| **YieldingWaitStrategy** `****` | 快 | 高 | V | 追求高性能，且EventHandler线程数量低于CPU逻辑核数(eg.开启超线程) |
| **BusySpinWaitStrategy** `*****` | 最快 | 最高 | V | 追求超高性能，且EventHandler线程数量低于CPU物理核数(eg.不应开启超线程) |

