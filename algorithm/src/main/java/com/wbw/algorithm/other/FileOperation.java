package com.wbw.algorithm.other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileOperation {

    // 正则
    public static final Pattern URL_REGEX = Pattern.compile("http://[a-zA-Z\\.]+");

    // 文件路径
    public static final String FILE_PATH = "d://test.txt";

    // 统计ip地址及出现的次数
    public static Map<String, Integer> countUrl() {

        File file = new File(FILE_PATH);
        // key：地址 value：次数
        Map<String, Integer> countUrl = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                Matcher matcher = URL_REGEX.matcher(line);
                while (matcher.find()) {
                    String group = matcher.group();
                    countUrl.put(group, countUrl.getOrDefault(group, 0) + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countUrl;
    }

    // 获取出现次数最多的ip地址
    public static List<String> getTopX(Map<String, Integer> map, int topX) {
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        List<String> res = new ArrayList<>();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(entries);
        Collections.sort(list, ((o1, o2) -> o2.getValue() - o1.getValue()));
        list.stream().limit(topX).forEach(entry -> res.add(entry.getKey()));
        return res;
    }


    public static void main(String[] args) {
        Map<String, Integer> stringIntegerMap = countUrl();
        System.out.println(getTopX(stringIntegerMap, 5));
    }
}
