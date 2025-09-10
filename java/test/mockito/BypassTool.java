package com.njust.learn;

/**
 * 绕过封装
 * <p>
 * 1. 获取非公共属性
 * <p>
 * 2. 设置非公共属性
 * <p>
 * 3. 调用非公共方法
 * <p>
 * 4. 调用非公共构造函数
 *
 * <pre>{@code
 * Use ReflectionTestUtils.setField(..) to set a non-public member of an instance or class.
 * Use ReflectionTestUtils.getField(..) to get a non-public member of an instance or class.
 * Use ReflectionTestUtils.invokeMethod(..) to invoke a non-public method of an instance or class.
 * 没有现成方法，只能手动反射 to create an instance of a class with a private constructor.
 * }
 * </pre>
 */
public class BypassTool {

    /**
     * 私有属性
     */
    private int count = 0;

    /**
     * 私有构造函数
     */
    private BypassTool(int count) {
        this.count = count;
    }

    public BypassTool() {
        this(0);
    }

    public void increase() {
        increaseCount(1);
    }

    /**
     * 私有方法
     */
    private void increaseCount(int increase) {
        count += increase;
    }

    public boolean checkCount(int target) {
        return this.count == target;
    }


}
