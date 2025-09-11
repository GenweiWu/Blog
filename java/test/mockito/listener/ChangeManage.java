package com.njust.learn.listener;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学习如何给Listener类型写单元测试
 */
//@Service
public class ChangeManage {

    @Resource
    private List<ChangeListener> changeListenerList;

    public void doUpdate(int oldV, int newV) {
        if (oldV != newV) {
            changeListenerList.forEach(c -> {
                c.onChange(oldV, newV);
            });
        }
    }

}
