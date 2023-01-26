package com.csii.stream;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi {

    public static void main(String[] args) {
       /* List<String> strings = Arrays.asList("张三丰", "张三", "李四");
        strings.stream().filter(e -> e.startsWith("张")).filter(s -> s.length() ==3).forEach(System.out:: println);

        Map<String, String> name = Map.of("name", "zs", "age", "12");
        name.keySet().stream().filter(e -> e.startsWith("n")).forEach(System.out::println);
        name.values().stream().filter(e -> e.startsWith("1")).forEach(System.out::println);
        name.entrySet().stream().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });

        strings.stream().skip(2).forEach(System.out::println);*/

/*
        Stream.of("1", "2", "7", "4", "5").map(Integer::parseInt).forEach(System.out::println);

        Stream.of("1", "2", "7", "4", "5").map(Integer::parseInt).sorted().forEach(System.out::println);*/

//        Stream.of("1", "2", "7", "4", "5").map(Integer::parseInt).sorted((o1, o2) -> o1 - o2).forEach(System.out::print);
      /*  1. 第一个队伍只保留姓名长度为3的成员
        2. 第一个队伍筛选之后只要前3个人
        3. 第二个队伍只要姓张的成员
        4. 第二个队伍筛选之后不要前两个人
        5. 将两个队伍合并为一个队伍
        6. 根据姓名创建Person对象
        7. 打印整个队伍的Person信息*/


        List<String> list1 = Arrays.asList("迪丽热巴", "宋远桥", "苏星河", "老子", "庄子", "孙子", "洪七 公");
        List<String> list2 = Arrays.asList("古力娜扎", "张无忌", "张三丰", "赵丽颖", "张二狗", "张天爱", "张三");
        Stream<String> limit = list1.stream().filter(e -> e.length() == 3).limit(3);
//        limit.forEach(System.out::println);

        Stream<String> 张 = list2.stream().filter(e -> e.startsWith("张")).skip(2);
//        张.forEach(System.out::println);

        Stream<String> concat = Stream.concat(list1.stream(), list2.stream());
//        concat.forEach(System.out::println);

        Stream<Person> personStream = concat.map(e -> new Person(e));
//        personStream.forEach(System.out::println);


        // 结果集收集


        List<String> stringList = Stream.of("aa", "bb", "cc").collect(Collectors.toList());
        System.out.println(stringList);


        Set<String> stringSet = Stream.of("aa", "bb", "cc").collect(Collectors.toSet());

        System.out.println(stringSet);

        ArrayList<String> stringArrayList = Stream.of("aa", "bb", "cc").collect(Collectors.toCollection(ArrayList::new));
        System.out.println(stringArrayList);

        Collection<String> stringHashSet = Stream.of("aa", "bb", "cc").collect(Collectors.toCollection(HashSet::new));
        System.out.println(stringHashSet);


        Object[] objects = Stream.of("aa", "bb", "cc").toArray();
        System.out.println(Arrays.toString(objects));

        String[] strings = Stream.of("aa", "bb", "cc").toArray(String[]::new);

    }

}
