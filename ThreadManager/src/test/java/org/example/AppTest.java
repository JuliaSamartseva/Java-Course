package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;

public class AppTest 
{
    @Test
    public void groupsInformation() throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup(Thread.currentThread().getThreadGroup(),"Group 1");

        Thread thread1 = new Thread(group1, new ThreadRunner(10000));
        thread1.start();

        Thread thread2 = new Thread(group1, new ThreadRunner(7000));
        thread2.start();

        Thread thread3 = new Thread(group1, new ThreadRunner(10000));
        thread3.start();

        ThreadsInformation information = new ThreadsInformation();
        information.getInformation(group1);

        ArrayList<Thread> threads = new ArrayList<>(Arrays.asList(thread1, thread2, thread3));
        synchronized(Thread.currentThread()) {
            try {
                Thread.currentThread().wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(information.processedThreads, threads);
        thread1.join();
        thread2.join();
        thread3.join();
    }

    @Test
    public void subgroupsInformation() throws InterruptedException {
        ThreadGroup group1 = new ThreadGroup(Thread.currentThread().getThreadGroup(),"Group 1");
        ThreadGroup group2 = new ThreadGroup(group1,"Group 2");

        Thread thread1 = new Thread(group1, new ThreadRunner(10000));
        thread1.start();

        Thread thread2 = new Thread(group1, new ThreadRunner(7000));
        thread2.start();

        Thread thread3 = new Thread(group2, new ThreadRunner(10000));
        thread3.start();

        ThreadsInformation information = new ThreadsInformation();
        information.getInformation(group1);
        ArrayList<Thread> threads = new ArrayList<>(Arrays.asList(thread1, thread2, thread3));
        synchronized(Thread.currentThread()) {
            try {
                Thread.currentThread().wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertEquals(information.processedThreads, threads);
        thread1.join();
        thread2.join();
        thread3.join();
    }
}
