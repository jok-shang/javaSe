package com.java.stream.stream;

import java.util.stream.Stream;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/16 14:09
 */
public class StreamDemo05 {
    public static void main(String[] args) {
        /*
           一堆零散数据  public static<T> Stream<T> of(T...values)    Stream接口中的静态方法
         */
        Stream.of(1,2,3,4,5,6).forEach(s->System.out.println(s));
        Stream.of("a","b","c").forEach(s->System.out.println(s));
    }
}
