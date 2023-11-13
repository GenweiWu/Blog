package com.njust.timer;

import org.junit.Test;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {

    /**
     * reschedule定时器1：
     * <p>
     * 1. 复用Timer
     * 2. 但是需要每次new TimeTask
     * <p>
     * <pre>
     * schedule here!Mon Nov 13 18:56:35 CST 2023
     * schedule here222!Mon Nov 13 18:56:36 CST 2023
     * end:Mon Nov 13 18:56:37 CST 2023
     * </pre>
     */
    @Test
    public void timerDemo1() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("schedule here!" + new Date());

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("schedule here222!" + new Date());
                    }
                }, 1000);
            }
        }, 1000);


        try {
            Thread.sleep(3200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end:" + new Date());
    }

    /**
     * reschedule定时器2：
     * <p>
     * 1. 调用cancel后，无法复用Timer
     * 2. 重新创建Timer + new TimeTask()
     * <p>
     * <pre>
     * schedule here!Mon Nov 13 19:00:10 CST 2023
     * schedule here222!Mon Nov 13 19:00:11 CST 2023
     * end:Mon Nov 13 19:00:13 CST 2023
     * </pre>
     */
    @Test
    public void timerDemo2() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("schedule here!" + new Date());

                //如果cancel了就不能复用Timer了
                timer.cancel();
                //重新创建Timer
                Timer timer2 = new Timer();
                timer2.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("schedule here222!" + new Date());
                    }
                }, 1000);
            }
        }, 1000);


        try {
            Thread.sleep(3200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end:" + new Date());
    }
}
