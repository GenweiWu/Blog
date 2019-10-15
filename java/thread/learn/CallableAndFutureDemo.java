package com.njust.test.learn;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFutureDemo
{
    public static void main(String[] args)
        throws ExecutionException, InterruptedException
    {
        //test1();
        //test2();
        test3();
        
    }
    
    /**
     * 3.批量执行
     * --------------
     * <pre>
     * before result...
     * future get result:gogo3507e92e-08fa-463a-b16f-7881609fd8d9
     * future get result:gogo1f9d8192-c238-46c2-88ff-118343dbd791
     * future get result:gogo582acb7b-7e9c-4bbd-b745-8a04763a513b
     * future get result:gogo3a67e892-3012-4b3e-8b3a-e8b1b1d1ee08
     * future get result:gogoc820b1c4-6369-4b0c-b403-dd6466c5d2e6
     * future get result:gogo39e25228-773b-47c9-ab4a-fa01278db320
     * future get result:gogo820f1cbe-da91-48d0-a665-5e50bf7fe64b
     * future get result:gogo94ddd3ce-3987-484f-b4a0-afd8ded4a0e5
     * future get result:gogo03a20494-f23b-483b-b8b4-4047a631cf0a
     * future get result:gogo4397bed2-0e2e-45e5-9599-0463595f695d
     * after result...
     * </pre>
     */
    private static void test3()
        throws InterruptedException, ExecutionException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<String>(executorService);
        
        for (int i = 0; i < 10; i++)
        {
            completionService.submit(new Callable<String>()
            {
                @Override
                public String call()
                    throws Exception
                {
                    return "gogo" + UUID.randomUUID().toString();
                }
            });
        }
        
        System.out.println("before result...");
        for (int i = 0; i < 10; i++)
        {
            System.out.println("future get result:" + completionService.take().get());
        }
        System.out.println("after result...");
    }
    
    /**
     * 2.submit一个callable，支持返回值
     * <pre>
     * before result...
     * future get result:hello:pool-1-thread-1
     * after result...
     * </pre>
     */
    private static void test2()
        throws ExecutionException, InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Future<String> future = executorService.submit(new Callable<String>()
        {
            @Override
            public String call()
                throws Exception
            {
                sleepOneMoment(3000);
                return "hello:" + Thread.currentThread().getName();
            }
        });
        
        System.out.println("before result...");
        System.out.println("future get result:" + future.get());
        System.out.println("after result...");
    }
    
    /**
     * 1.submit一个Runnable
     * 由于Runnable返回值是void，所以无法设置返回值
     * -------------------------
     * <pre>
     * before result...
     * this will return null
     * future1 get result:null
     * this will return T
     * future2 get result:ironMan
     * after result...
     * </pre>
     */
    private static void test1()
        throws ExecutionException, InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        
        Future future1 = executorService.submit(new Runnable()
        {
            @Override
            public void run()
            {
                sleepOneMoment(1000);
                System.out.println("this will return null");
            }
        });
        Future<String> future2 = executorService.submit(new Runnable()
        {
            @Override
            public void run()
            {
                sleepOneMoment(3000);
                System.out.println("this will return ironMan");
            }
        }, "ironMan");
        
        System.out.println("before result...");
        System.out.println("future1 get result:" + future1.get());
        System.out.println("future2 get result:" + future2.get());
        System.out.println("after result...");
    }
    
    private static void sleepOneMoment(int n)
    {
        try
        {
            Thread.sleep(n);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
