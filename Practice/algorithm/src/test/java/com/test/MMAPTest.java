package com.test;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.RandomAccess;

/**
 * 什么是零拷贝?
 * 零拷贝(英语: Zero-copy) 技术是指计算机执行操作时，CPU不需要先将数据从某处内存复制到另一个特定区域。
 * 通常用于通过网络传输文件时节省CPU周期和内存带宽。
 * 比如传统数据传送机制：
 * 1、第一步：将磁盘文件，读取到操作系统内核缓冲区；
 * 2、第二步：将内核缓冲区的数据，copy到应用程序的buffer；
 * 3、第三步：将application应用程序buffer中的数据，copy到socket网络发送缓冲区(属于操作系统内核的缓冲区)；
 * 4、第四步：将socket buffer的数据，copy到网卡，由网卡进行网络传输。
 *
 * mmap内存映射Maps a shared memory region into the process's memory.
 * 硬盘上文件的位置和应用程序缓冲区(application buffers)进行映射（建立一种一一对应关系)
 * 在文件读取时根据这个映射关系，直接将文件从硬盘拷贝到用户空间，只需要3次拷贝就可以完成。
 */
public class MMAPTest {

    @Test
    public void test() throws IOException {

        File file = new File("D:/study/hello.txt");
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
            file.createNewFile();
        } else {
            file.createNewFile();
        }
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel channel = randomAccessFile.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
        String str = "大家，新年好";
        buffer.put(str.getBytes());

        buffer.flip();
        byte[] bb = new byte[str.getBytes(StandardCharsets.UTF_8).length];
        buffer.get(bb, 0, str.getBytes(StandardCharsets.UTF_8).length);
        System.out.println(new String(bb));
    }
}
