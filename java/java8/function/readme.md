```
Supplier       ()    -> x
Consumer       x     -> ()
BiConsumer     x, y  -> ()

Callable       ()    -> x throws ex
Runnable       ()    -> ()

Function       x     -> y
BiFunction     x,y   -> z
UnaryOperator  x1    -> x2
BinaryOperator x1,x2 -> x3

Predicate      x     -> boolean
BiPredicate    x,y   -> boolean 
```


### 消费者consumer和生产者supplier
| 我的理解             | 分类                                     |                                                              | 样例                |
| -------------------- | ---------------------------------------- | ------------------------------------------------------------ | ------------------- |
| (x)-->void<br>消费者 | Consumer                                 | Consumer<br/>DoubleConsumer<br/>IntConsumer<br/>LongConsumer | Optional.ifPresent  |
| (obj,T)->void        |                                          | ObjDoubleConsumer<br/>ObjIntConsumer<br/>ObjLongConsumer     |                     |
| (T,U)->void          | BiConsumer                               | BiConsumer                                                   |                     |
| void->T<br>生产者    | Supplier                                 | Supplier<br>BooleanSupplier<br/>DoubleSupplier<br/>IntSupplier<br/>LongSupplier |                     |







### 转换器Function

| 我的理解             | 分类                                     |                                                              | 样例                |
| -------------------- | ---------------------------------------- | ------------------------------------------------------------ | ------------------- |
| (T,U)->R             | BiFunction                               | BiFunction<br/>ToDoubleBiFunction<br/>ToIntBiFunction<br/>ToLongBiFunction |                     |
| T->R                 | Function                                 | Function<br/>DoubleFunction<br/>DoubleToIntFunction<br/>DoubleToLongFunction<br/>IntFunction<br/>IntToDoubleFunction<br/>IntToLongFunction<br/>LongFunction<br/>LongToDoubleFunction<br/>LongToIntFunction<br/>ToDoubleFunction<br/>ToIntFunction<br/>ToLongFunction |                     |
| (T,T)->T             | BinaryOperator extends BiFunction<T,T,T> | BinaryOperator<br/>DoubleBinaryOperator<br/>IntBinaryOperator<br/>LongBinaryOperator |                     |
| T->T                 | UnaryOperator  extends Function<T, T>    | DoubleUnaryOperator<br/>IntUnaryOperator<br/>LongUnaryOperator<br/>UnaryOperator |                     |

### 判断predicate
| 我的理解             | 分类                                     |                                                              | 样例                |
| -------------------- | ---------------------------------------- | ------------------------------------------------------------ | ------------------- |
| (T,U)->boolean       | BiPredicate                              | BiPredicate                                                  |                     |
| t->boolean           | Predicate                                | DoublePredicate<br/>IntPredicate<br/>LongPredicate<br/>Predicate | Collection.removeIf |



