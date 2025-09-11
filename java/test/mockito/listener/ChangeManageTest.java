package com.njust.learn.listener;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  //用于注解初始化
public class ChangeManageTest {

    @InjectMocks
    private ChangeManage changeManage;

    //1.这里要用spy，因为我们要一个真正能用的list
    @Spy
    private List<ChangeListener> changeListenerList = new ArrayList<>();

    @Mock
    private ChangeListener changeListener1;

    @BeforeEach
    public void setup() {
        //2.这个操作要写在后面
        changeListenerList.add(changeListener1);
    }

    @Test
    public void doUpdate_changed() {
        changeManage.doUpdate(11, 22);

        verify(changeListener1, times(1)).onChange(anyInt(), anyInt());
    }

    @Test
    public void doUpdate_notChanged() {
        changeManage.doUpdate(11, 11);

        verify(changeListener1, never()).onChange(anyInt(), anyInt());
    }
}
