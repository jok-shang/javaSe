package com.java.stream.StreamTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/29 23:35
 */
public class Test2 {
    public static void main(String[] args) {
        /*
        创建一个ArrayList集合，并添加一下字符串，字符串中前面的是姓名，后面的是年龄
        “zhangsan，23”
        “lisi,24”
        “wangwu，25”
        保留年龄大于24岁的人，并将结果收集到Map集合中，姓名为键，年龄为值
         */
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"zhangsan,23","lisi,24","wangwu,25");
        List<String> collect = list.stream()
                .filter(s -> Integer.parseInt(s.split(",")[1]) >= 24)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("=========================");

        Map<String, Integer> collect1 = list.stream()
                .filter(s -> Integer.parseInt(s.split(",")[1]) >= 24)
                .collect(Collectors.toMap(
                        s -> s.split(",")[0],
                        s -> Integer.parseInt(s.split(",")[1])
                ));
        System.out.println(collect1);
    }
}
