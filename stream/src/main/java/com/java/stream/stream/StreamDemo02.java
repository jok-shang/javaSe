package com.java.stream.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/16 13:32
 */
public class StreamDemo02 {
    public static void main(String[] args) {
        /*
        单列集合    default Stream<E> stream()  Collection中的默认方法
        双列集合    无                          无法直接使用stream流
        数组       public static<T> Stream<T>  stream(T<> array) Arrays工具类中的静态方法
        一堆零散数据  public static<T> Stream<T> of(T...values)    Stream接口中的静态方法
         */

        // 1. 单例集合中获取Stream流
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"a","b","c","d","e");
        list.stream().forEach(s -> System.out.println(s));

//        // 读取到一条流水线，并把集合中的数据放到流水线上
//        Stream<String> stream = list.stream();
//        // 使用终结方式打印一下流水线上的所有数据
//        stream.forEach(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                // s: 依次表示流水线上的每一个数据
//                System.out.println(s);
//            }
//        });


    }
}
