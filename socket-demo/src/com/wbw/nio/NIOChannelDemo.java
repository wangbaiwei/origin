package com.wbw.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

public class NIOChannelDemo {

    public static void main(String[] args) throws Exception {
      /*  FileOutputStream fo = new FileOutputStream(new File("d:/test.txt"));
        FileChannel channel = fo.getChannel();
        ByteBuffer bf = ByteBuffer.allocate(1024);
        bf.put("hello world".getBytes());
        bf.flip();
        int write = channel.write(bf);
        channel.close();
        fo.close();

        Map map = new HashMap();
        Map<Object, Object> objectObjectMap = Collections.synchronizedMap(map);
        objectObjectMap.put("name", "zs");
        System.out.println(objectObjectMap.get("name"));*/

        A a = new A();
        A b = new A();
        System.out.println(a.equals(a));

        System.out.println("test".equals("hello"));

        System.out.println("a " + a.hashCode());
        System.out.println(b.hashCode());
    }
}