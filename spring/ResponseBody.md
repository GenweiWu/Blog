
## ResponseEntity<T> and @ResponseBody 一样的效果

```java
@RequestMapping(value = "/message")
@ResponseBody
public Message get() {
    return new Message(penguinCounter.incrementAndGet() + " penguin!");
}
```

```java
@RequestMapping(value = "/message")
ResponseEntity<Message> get() {
    Message message = new Message(penguinCounter.incrementAndGet() + " penguin!");
    return new ResponseEntity<Message>(message, HttpStatus.OK);
}
```
  
  ### 区别
  > https://stackoverflow.com/a/22725259  
  ResponseEntity更灵活
