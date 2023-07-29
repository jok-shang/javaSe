package com.java.stream.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/29 21:10
 */
public class StreamCollect {
    public static void main(String[] args) {
        /*
        collect(Collector collector)   收集流中的数据，放到集合中(List Set Map)
         */
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,
                "张无忌-男-15", "周芷若-女-14", "赵敏-女-13",
                "张强-男-20", "张三丰-男-100", "张翠山-男-40", "张良-男-25",
                "王二麻子-男-37", "谢广坤-男-40");

        /* 收集到list集合中
         需求：
         把所有的男性收集起来
         */
        List<String> collect = list.stream().filter(s -> "男".equals((s.split("-"))[1]))
                .collect(Collectors.toList());
        System.out.println(collect);
        System.out.println("=========================");

        /* 收集到Set集合中
         需求：
         把所有男性收集起来
        set 去重
         */
        Set<String> collect1 = list.stream().filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toSet());
        System.out.println(collect1);
        System.out.println("=========================");

        /*
        收集到 Map 集合中
        谁作为键，谁作为值
        键：姓名  值：年龄
         */
        Map<String, Integer> collect2 = list.stream().filter(s -> "男".equals(s.split("-")[1]))
                /*
                toMap: 参数一表示键的生成规则
                       参数二值的生成规则

                参数一:
                    Function泛型一：表示流中每一个数据的类型
                            泛型二：表示Map集合中键的数据类型

                         方法apply形参：依次表示流里面的每一个数据
                                方法体：生成键的代码
                                返回值：已经生产的键

                 参数二：
                    Function泛型一：表示流中每一个数据的类型
                            泛型二：表示Map集合中值的数据类型

                    方法apply形参：依次表示流里面的每一个数据
                            方法体：生成值的代码
                            返回值：已经生成的值


                 */
                .collect(Collectors.toMap(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        // 张无忌-男-15
                        return s.split("-")[0];
                    }
                }, new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s.split("-")[2]);
                    }
                }));
        System.out.println(collect2);
        System.out.println("=========================");

        Map<String, Integer> collect3 = list.stream().filter(s -> "男".equals(s.split("-")[1]))
                .collect(Collectors.toMap(
                        s -> s.split("-")[0],
                        s -> Integer.parseInt(s.split("-")[2])));
        System.out.println(collect3);
    }
}
