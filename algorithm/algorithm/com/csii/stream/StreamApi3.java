package com.csii.stream;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi3 {


    public static void main(String[] args) {
        // 1.求最大值
        Map<Integer, List<Person>> collect = Stream.of(new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 13)
                , new Person("王五", 15)
                , new Person("张三", 19)).collect(Collectors.groupingBy(Person::getAge));
//        System.out.println(collect);


        Map<String, List<Person>> collect1 = Stream.of(new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 13)
                , new Person("王五", 15)
                , new Person("张三", 19)).collect(Collectors.groupingBy(p -> p.getAge() > 18 ? "未成年" : "成年"));
//        System.out.println(collect1);



        // 多级分组
        Map<String, Map<String, List<Person>>> collect2 = Stream.of(
                new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 14)
                , new Person("李四", 15)
                , new Person("张三", 19)).collect(Collectors.groupingBy(Person::getName, Collectors.groupingBy(p -> p.getAge() > 18 ? "成年" : "未成年")));
        System.out.println(collect2);
    }



}
