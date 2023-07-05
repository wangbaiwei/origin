package com.wbw.algorithm.leetCode;

import java.util.concurrent.Semaphore;

public class ThreadTest2 extends Thread {

    @Override
    public void run() {
        System.out.println("hello");
        try {
            this.wait(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(5);

        try {
            semaphore.acquire();
            System.out.println("hello");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }

        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("hello");


        System.out.println(threadLocal.get());
    }

}
