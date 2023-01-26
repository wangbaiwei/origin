package com.csii.stream;

import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApi2 {


    public static void main(String[] args) {
        // 1.求最大值
        Optional<Person> maxAge = Stream.of(new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 13)
                , new Person("王五", 15)
                , new Person("张三", 19)).collect(Collectors.maxBy((p1, p2) -> p1.age - p2.age));
        System.out.println(maxAge);
        // 2.求最小值
        Optional<Person> minAge = Stream.of(new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 13)
                , new Person("王五", 15)
                , new Person("张三", 19)).collect(Collectors.minBy((p1, p2) -> p1.age - p2.age));
        System.out.println(minAge);
        // 2.求平均值
        Double aDouble = Stream.of(new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 13)
                , new Person("王五", 15)
                , new Person("张三", 19)).collect(Collectors.averagingDouble(p -> p.age));
        System.out.println(aDouble);
        // 3.统计人数
        Long aLong = Stream.of(new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 13)
                , new Person("王五", 15)
                , new Person("张三", 19)).collect(Collectors.counting());
        System.out.println(aLong);

        long count = Stream.of(new Person("张三", 18)
                , new Person("李四", 22)
                , new Person("张三", 13)
                , new Person("王五", 15)
                , new Person("张三", 19)).count();
        System.out.println(count);

    }



}
