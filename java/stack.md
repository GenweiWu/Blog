
# stack堆栈


### stack类是线程安全的，非线程安全可以使用Deque代替


```java
    @Test
    public void test01()
    {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        Assert.assertEquals(Integer.valueOf(2), stack.pop());
        Assert.assertEquals(Integer.valueOf(1), stack.pop());
        Assert.assertTrue(stack.isEmpty());
    }
    
    @Test
    public void test02()
    {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        Assert.assertEquals(Integer.valueOf(2), stack.pop());
        Assert.assertEquals(Integer.valueOf(1), stack.pop());
        Assert.assertTrue(stack.isEmpty());
    }
```
