package com.harmong.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CommonUtil {
	
	/**
	 * »•ø’∏Ò
	 */
	public static String trim(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString().trim();
	}
	/**
	 * ∑«ø’≈–∂œ
	 */
	public static boolean isNullOrEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj.toString().trim().length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * userId
	 */
	public static String generateUserId() {
		long l = System.currentTimeMillis();
		return String.valueOf(l);
	}
		
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	
	public static void main(String[] args) {
		System.out.println(CommonUtil.class.getResource(""));
	}

}
