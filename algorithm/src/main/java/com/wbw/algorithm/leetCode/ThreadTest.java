package com.wbw.algorithm.leetCode;

public class ThreadTest extends Thread {

    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println("hello");
        }
    }

    public static void main(String[] args) {

        ThreadTest threadTest = new ThreadTest();
        threadTest.start();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程打断");
//        demo7.interrupt();
        threadTest.stop();
        System.out.println(threadTest.isInterrupted()); // true

    }


}
