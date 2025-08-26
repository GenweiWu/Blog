
## CompletableFuture的三种异常处理

|                |                      | 什么时候调用      | 是否修复future到正常状态 |
| -------------- | -------------------- | ----------------- | ------------------------ |
| *exceptionally*  | `exception -> T`     | 仅失败时 ❌        | ✅ 通过返回新值           |
| *handle*        | `(result, ex) -> T`  | 成功或失败都会 ✅❌ | ✅ 通过返回新值           |
| *whenComplete* | `(result, ex) -> void` | 成功或失败都会 ✅❌ | ❌仅观察，状态不变        |

