package com.java.stream.Stream01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/16 21:14
 */
public class StreamDemo06 {
    public static void main(String[] args) {
        /*
        filter    过滤
        limit     获取前几个元素
        skip      跳过前几个元素

        注意1. 中间方法，返回新的Stream流，原来的Stream流只能使用一次，建议使用链式流程
        注意2. 修改Stream流中的数据，不会影响原来集合或者数组中的数据
         */
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张无忌","周芷若","赵敏","张强","张三丰","张翠山","张良","王二麻子","谢广坤");

        // filter 过滤 把张开头的留下来，其余数据过滤不要
//        list.stream().filter(new Predicate<String>() {
//            @Override
//            public boolean test(String s) {
//                return s.startsWith("张");
//            }
//        }).forEach(s -> System.out.println(s));

        System.out.println("========");
        // 方式一 整合
        list.stream()
                .filter(s -> s.startsWith("张"))
                .forEach(System.out::println);

        System.out.println("=========");
        // 方式二 拆分
        Stream<String> stream1 = list.stream().filter(s -> s.startsWith("张"));
        Stream<String> stream2 = stream1.filter(s -> s.length() ==3);
        stream2.forEach(s -> System.out.println(s));

        /*
        limit     获取前几个元素
        skip      跳过前几个元素
        * */
        System.out.println("=======limit=======");
        list.stream().limit(4)
                .forEach(System.out::println);

        System.out.println("========skip======");
        list.stream().skip(4)
                .forEach(System.out::println);
        System.out.println("==练习" +
                "获取张强，张三丰，张翠山" +
                "==");
        list.stream().skip(3)
                .limit(3)
                .filter(s -> s.startsWith("张"))
                .forEach(System.out::println);
    }
}
