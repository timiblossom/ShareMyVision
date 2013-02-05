package com.smv.test.misc;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
 
public class MyThreadPoolExecutor
{
    int poolSize = 2;
 
    int maxPoolSize = 2;
 
    long keepAliveTime = 1;
 
    ThreadPoolExecutor threadPool = null;
 
    final ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(
            3);
 
    public MyThreadPoolExecutor()
    {
        threadPool = new ThreadPoolExecutor(poolSize, maxPoolSize,
                                            keepAliveTime, TimeUnit.SECONDS, 
                                            queue);
 
    }
 
    public void runTask(Runnable task)
    {
        // System.out.println("Task count.."+threadPool.getTaskCount() );
        // System.out.println("Queue Size before assigning the
        // task.."+queue.size() );
        threadPool.execute(task);
        // System.out.println("Queue Size after assigning the
        // task.."+queue.size() );
        // System.out.println("Pool Size after assigning the
        // task.."+threadPool.getActiveCount() );
        // System.out.println("Task count.."+threadPool.getTaskCount() );
        System.out.println("Task count.." + queue.size());
 
    }
 
    public void shutDown()
    {
        threadPool.shutdown();
    }
 
    public static void main(String args[])
    {
        MyThreadPoolExecutor mtpe = new MyThreadPoolExecutor();
        // start first one
        mtpe.runTask(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                    try
                    {
                        System.out.println("Thread id : " + Thread.currentThread().getId() + "-First Task");
                        Thread.sleep(3000);
                    } catch (InterruptedException ie)
                    {
                    }
                }
            }
        });
        // start second one
        /*
         * try{ Thread.sleep(500); }catch(InterruptedException
         * ie){}
         */
        mtpe.runTask(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 10; i++)
                {
                    try
                    {
                        System.out.println("Thread id : " + Thread.currentThread().getId() + "-Second Task");
                        Thread.sleep(3000);
                    } catch (InterruptedException ie)
                    {
                    }
                }
            }
        });
        // start third one
        /*
         * try{ Thread.sleep(500); }catch(InterruptedException
         * ie){}
         */
        mtpe.runTask(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 10000; i++)
                {
                    try
                    {
                        System.out.println("Thread id : " + Thread.currentThread().getId() + "-Third Task");
                        Thread.sleep(4000);
                    } catch (InterruptedException ie)
                    {
                    }
                }
            }
        });
        // start third one
        /*
         * try{ Thread.sleep(500); }catch(InterruptedException
         * ie){}
         */
        mtpe.runTask(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 10000; i++)
                {
                    try
                    {
                        System.out.println("Thread id : " + Thread.currentThread().getId() + "-Third Task");
                        Thread.sleep(4000);
                    } catch (InterruptedException ie)
                    {
                    }
                }
            }
        });
        // start fourth one
        /*
         * try{ Thread.sleep(500); }catch(InterruptedException
         * ie){}
         */
        mtpe.runTask(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 10000; i++)
                {
                    try
                    {
                        System.out.println("Thread id : " + Thread.currentThread().getId() + "-Fourth Task");
                        Thread.sleep(4000);
                    } catch (InterruptedException ie)
                    {
                    }
                }
            }
        });
        // start fifth one
        /*
         * try{ Thread.sleep(500); }catch(InterruptedException
         * ie){}
         */
        mtpe.runTask(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 10000; i++)
                {
                    try
                    {
                        System.out.println("Thread id : " + Thread.currentThread().getId() + "-Fifth Task");
                        Thread.sleep(4000);
                    } catch (InterruptedException ie)
                    {
                    }
                }
            }
        });
        // start Sixth one
        /*
         * try{ Thread.sleep(500); }catch(InterruptedException
         * ie){}
         */
        mtpe.runTask(new Runnable()
        {
            public void run()
            {
                for (int i = 0; i < 10000; i++)
                {
                    try
                    {
                        System.out.println("Thread id : " + Thread.currentThread().getId() + "-Sixth Task");
                        Thread.sleep(4000);
                    } catch (InterruptedException ie)
                    {
                    }
                }
            }
        });
        mtpe.shutDown();
    }
 
}
