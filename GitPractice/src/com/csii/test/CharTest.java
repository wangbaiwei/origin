package com.csii.test;

import java.util.Scanner;

import com.sun.javadoc.Type;

/**
 * 
 * @author Youth
 *
 */
public class CharTest {
	
	public static void main(String[] args) {
		/**
		 * 	制表符
		 */
		var str = "\t";
		System.out.println("|" + str + "|");
		// 换行符
		System.out.print("---------");
		System.out.println("\t" + "\\");
		System.out.println("|\r|");
		System.out.println("\'");
		System.out.println("\"");
		System.out.println("我是中国人\n呢");
		System.out.println("\f");
		System.out.println("我是中国人\r");
		Scanner scan = new Scanner(System.in);
		String a = null;
		/*
		 * while (scan.hasNext()) { a = scan.next(); System.out.print(a + "\r"); }
		 */
		
		// java 提供的三个特殊的浮点值 正无穷大 负无穷大 非数
		System.out.println(Float.NEGATIVE_INFINITY);
		System.out.println(Float.POSITIVE_INFINITY);
		System.out.println(-8.0 / 0.0); // -Infinity
		System.out.println(Float.NEGATIVE_INFINITY / Float.POSITIVE_INFINITY);
		
		// 浮点型数据默认是 double类型
		float f = 0.56561321f;
		double d = 0.56561321;
		
		System.out.println(f);
		System.out.println(d);
		
		// 整数类型默认是int类型
		long l = 4545456464546546L;
		int i = 454545646;
		// 为了快速辨别数字的位数，数字之间可以用下划线来标识（java 7中引入）
		double ll = 1222_7222_9999_9999_9999_1234D;
		// 布尔类型的值 只有 true 和 false 布尔类型的值和字符串进行连接运算 boolean值会自动转换为字符串
		boolean b = false;
		System.out.println(b + "Hello!"); // falseHello!
		
		byte bt = 127; // 1111111 1*2E0 + 1*2E1 + 1*2E2 + 1*2E3 + 1*2E4 + 1*2E5 + 1*2E6
		short st = 32767; // 
		
		int num = 8;
		System.out.println(num << 2);
		System.out.println(num >>> 2);
		System.out.println(num >> 2);
 		System.out.println(5 & 9);
		System.out.println(5 | 9);
		System.out.println(5 ^ 9);
		System.out.println(~5);
		
		
		
	}

}
