package com.java.stream.StreamTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/29 23:49
 */
public class Test3 {
    public static void main(String[] args) {
        /*
        现有两个ArrayList集合，分别存储6名男/女演员的名字和年龄
        姓名和年龄之间用,隔开
        按要求完成一下操作
        1. 男演员只要名字为三个的前两人
        2. 女演员只要姓杨的，并且不要第一个
        3. 把过滤后的男演员姓名和女演员姓名合并到一起
        4. 将上一步的演员姓名封装成Actor对象
        5. 将所有的演员对象都报存到List集合中

        备注：演员类Actor 属性有：name，age
         */
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list, "蔡旭坤,24", "叶问问,23", "刘不甜,22",
                "吴签,24", "谷嘉,30", "肖梁梁,27");
        Collections.addAll(list1, "赵小,35", "杨颖,36", "高员员,43",
                "张天天,31", "刘诗,35", "杨米,33");
        List<String> collect = list.stream()
                .filter(s -> s.split(",")[0].length() == 3)
                .limit(2)
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("=========================");
        List<String> collect1 = list1.stream()
                .filter(s -> s.split(",")[0]
                        .startsWith("杨"))
                .skip(1)
                .collect(Collectors.toList());
        collect1.forEach(System.out::println);
        System.out.println("=========================");

        /// 合并流
        Stream<String> stream1 = list.stream()
                .filter(s -> s.split(",")[0].length() == 3)
                .limit(2);
        Stream<String> stream2 = list1.stream()
                .filter(s -> s.split(",")[0]
                        .startsWith("杨"))
                .skip(1);

        // String ->actor 对象（类型转换）
 /*       Stream<Actor> actorStream = Stream.concat(stream1, stream2).map(new Function<String, Actor>() {
            @Override
            public Actor apply(String s) {
                String name = s.split(",")[0];
                Integer age = Integer.parseInt(s.split(",")[1]);
                return new Actor(name, age);
            }
        });
        actorStream.forEach(System.out::println);*/

        System.out.println("=============a============");

        List<Actor> collect2 = Stream.concat(stream1, stream2)
                .map(s -> new Actor(s.split(",")[0],
                        Integer.parseInt(s.split(",")[1])))
                .collect(Collectors.toList());
        collect2.forEach(System.out::println);
    }
}
