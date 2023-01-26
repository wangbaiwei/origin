package com.wbw.nio;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class B extends A{

    public static void main(String[] args) throws InterruptedException {
       /* Hashtable t = new Hashtable();
        System.out.println(t.size());

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(null, null);

        System.out.println(objectObjectHashMap.get(null));*/

        B.say();

        new Thread(() -> {
            System.out.println("waiting.....");
            try {
                B.say();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        ReentrantLock reentrantLock = new ReentrantLock(true);

        Condition condition = reentrantLock.newCondition();
        reentrantLock.lock();

        reentrantLock.unlock();

    }

    public synchronized static String say() throws InterruptedException {
        System.out.println("hello world start");
        Thread.sleep(5000);
        System.out.println("hello world end");
        return "end";

    }

    public synchronized void  test() {

        synchronized (this) {

        }
    }



}
