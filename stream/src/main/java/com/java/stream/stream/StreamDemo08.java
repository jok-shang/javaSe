package com.java.stream.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Function;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/17 1:00
 */
public class StreamDemo08 {
    public static void main(String[] args) {
        /*
         map    数据转换

         注意1. 中间方法，返回新的Stream流，原来的stream流只能使用一次，建议使用链式编程
         注意2. 修改stream流中的数据，不会影响原来的集合或者数组中的数据
        * */
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list,"张无忌-15","周芷若-14","赵敏-13","张强-20","张三丰-100","张翠山-40","张良-25","王二麻子-37","谢广坤-40");

        
        // 只获取里面的年龄并且打印

        // 第一个类型，表示流中原本的数据类型
        // 第二个类型，要转换之后的类型
        // apply的形参s： 依次表示流里面的每一个数据
        // 返回值：表示转换之后的数据
//        list.stream().map(new Function<String, Integer>() {
//            @Override
//            public Integer apply(String s) {
//                String[] arr = s.split("-");
//                String ageString = arr[1];
//                int age = Integer.parseInt(ageString);
//                return age;
//            }
//        }).forEach(s -> System.out.println(s));

        list.stream()
                .map(s -> Integer.parseInt(s.split("-")[1]))
                .forEach(s -> System.out.println(s));

    }
}
