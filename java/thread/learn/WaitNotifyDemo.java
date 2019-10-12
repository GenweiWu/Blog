package com.njust.test.learn;

/**
 * 实现下面的功能：
 * 子线程循环10次，主线程循环50次，接着子线程循环10次，主线程循环20次，如此循环5次。
 * <p>
 * ======================================================================
 * <ul>
 * <li>1. synchronized实现原子性(如10次子循环不被打断)</li>
 * <li>2. 一人一次则通过wait和notify实现</li>
 * <li>3. wait一般总是和while判断一起，而不是if判断一起</li>
 * </ul>
 * As in the one argument version, interrupts and spurious wakeups are
 * possible, and this method should always be used in a loop:
 * <pre>
 *     synchronized (obj) {
 *         while (&lt;condition does not hold&gt;)
 *             obj.wait(timeout, nanos);
 *         ... // Perform action appropriate to condition
 *     }
 * </pre>
 */
public class WaitNotifyDemo
{
    private static final int COUNT = 50;
    
    public static void main(String[] args)
    {
        Manage manage = new Manage();
        
        new Thread(() -> {
            for (int i = 1; i <= COUNT; i++)
            {
                manage.subDo(i);
            }
        }).start();
        
        for (int i = 1; i <= COUNT; i++)
        {
            manage.mainDo(i);
        }
    }
    
}

class Manage
{
    private static final int SUB_INTERVAL = 10;
    
    private static final int MAIN_INTERVAL = 50;
    
    private boolean subTurn = true;
    
    public void subDo(int index)
    {
        synchronized (this)
        {
            while (!subTurn)
            {
                try
                {
                    this.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            
            for (int i = 1; i <= SUB_INTERVAL; i++)
            {
                System.out.println(String.format("-> Sub[%s]: %s/%s", index, i, SUB_INTERVAL));
            }
            
            subTurn = false;
            this.notify();
        }
    }
    
    public void mainDo(int index)
    {
        synchronized (this)
        {
            
            while (subTurn)
            {
                try
                {
                    this.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            
            for (int i = 1; i <= MAIN_INTERVAL; i++)
            {
                System.out.println(String.format("=====> Main[%s]: %s/%s", index, i, MAIN_INTERVAL));
            }
            
            subTurn = true;
            this.notify();
        }
    }
}

/**
 * <pre>
 * -> Sub[1]: 1/10
 * -> Sub[1]: 2/10
 * -> Sub[1]: 3/10
 * -> Sub[1]: 4/10
 * -> Sub[1]: 5/10
 * -> Sub[1]: 6/10
 * -> Sub[1]: 7/10
 * -> Sub[1]: 8/10
 * -> Sub[1]: 9/10
 * -> Sub[1]: 10/10
 * =====> Main[1]: 1/50
 * =====> Main[1]: 2/50
 * =====> Main[1]: 3/50
 * =====> Main[1]: 4/50
 * =====> Main[1]: 5/50
 * =====> Main[1]: 6/50
 * =====> Main[1]: 7/50
 * =====> Main[1]: 8/50
 * =====> Main[1]: 9/50
 * =====> Main[1]: 10/50
 * =====> Main[1]: 11/50
 * =====> Main[1]: 12/50
 * =====> Main[1]: 13/50
 * =====> Main[1]: 14/50
 * =====> Main[1]: 15/50
 * =====> Main[1]: 16/50
 * =====> Main[1]: 17/50
 * =====> Main[1]: 18/50
 * =====> Main[1]: 19/50
 * =====> Main[1]: 20/50
 * =====> Main[1]: 21/50
 * =====> Main[1]: 22/50
 * =====> Main[1]: 23/50
 * =====> Main[1]: 24/50
 * =====> Main[1]: 25/50
 * =====> Main[1]: 26/50
 * =====> Main[1]: 27/50
 * =====> Main[1]: 28/50
 * =====> Main[1]: 29/50
 * =====> Main[1]: 30/50
 * =====> Main[1]: 31/50
 * =====> Main[1]: 32/50
 * =====> Main[1]: 33/50
 * =====> Main[1]: 34/50
 * =====> Main[1]: 35/50
 * =====> Main[1]: 36/50
 * =====> Main[1]: 37/50
 * =====> Main[1]: 38/50
 * =====> Main[1]: 39/50
 * =====> Main[1]: 40/50
 * =====> Main[1]: 41/50
 * =====> Main[1]: 42/50
 * =====> Main[1]: 43/50
 * =====> Main[1]: 44/50
 * =====> Main[1]: 45/50
 * =====> Main[1]: 46/50
 * =====> Main[1]: 47/50
 * =====> Main[1]: 48/50
 * =====> Main[1]: 49/50
 * =====> Main[1]: 50/50
 * -> Sub[2]: 1/10
 * -> Sub[2]: 2/10
 * -> Sub[2]: 3/10
 * -> Sub[2]: 4/10
 * -> Sub[2]: 5/10
 * -> Sub[2]: 6/10
 * -> Sub[2]: 7/10
 * -> Sub[2]: 8/10
 * -> Sub[2]: 9/10
 * -> Sub[2]: 10/10
 * =====> Main[2]: 1/50
 * =====> Main[2]: 2/50
 * =====> Main[2]: 3/50
 * =====> Main[2]: 4/50
 * =====> Main[2]: 5/50
 * =====> Main[2]: 6/50
 * =====> Main[2]: 7/50
 * =====> Main[2]: 8/50
 * =====> Main[2]: 9/50
 * =====> Main[2]: 10/50
 * =====> Main[2]: 11/50
 * =====> Main[2]: 12/50
 * =====> Main[2]: 13/50
 * =====> Main[2]: 14/50
 * =====> Main[2]: 15/50
 * =====> Main[2]: 16/50
 * =====> Main[2]: 17/50
 * =====> Main[2]: 18/50
 * =====> Main[2]: 19/50
 * =====> Main[2]: 20/50
 * =====> Main[2]: 21/50
 * =====> Main[2]: 22/50
 * =====> Main[2]: 23/50
 * =====> Main[2]: 24/50
 * =====> Main[2]: 25/50
 * =====> Main[2]: 26/50
 * =====> Main[2]: 27/50
 * =====> Main[2]: 28/50
 * =====> Main[2]: 29/50
 * =====> Main[2]: 30/50
 * =====> Main[2]: 31/50
 * =====> Main[2]: 32/50
 * =====> Main[2]: 33/50
 * =====> Main[2]: 34/50
 * =====> Main[2]: 35/50
 * =====> Main[2]: 36/50
 * =====> Main[2]: 37/50
 * =====> Main[2]: 38/50
 * =====> Main[2]: 39/50
 * =====> Main[2]: 40/50
 * =====> Main[2]: 41/50
 * =====> Main[2]: 42/50
 * =====> Main[2]: 43/50
 * =====> Main[2]: 44/50
 * =====> Main[2]: 45/50
 * =====> Main[2]: 46/50
 * =====> Main[2]: 47/50
 * =====> Main[2]: 48/50
 * =====> Main[2]: 49/50
 * =====> Main[2]: 50/50
 * ....类似循环50次后结束....
 * =====> Main[50]: 50/50
 *
 * Process finished with exit code 0
 * </pre>
 */
