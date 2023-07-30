package com.heima.stream.stream;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/16 13:54
 */
public class StreamDemo04 {
    public static void main(String[] args) {
        /*
   数组       public static<T> Stream<T>  stream(T<> array) Arrays工具类中的静态方法
         */
        // 1. 创建数组
        int[] arr = {1,2,3,4,5,6,7,8,9};
        String[] arr2 = {"a","b","c"};
        // 2. 获取stream流
        Arrays.stream(arr).forEach(s -> System.out.println(s));

        Arrays.stream(arr2).forEach(s -> System.out.println(s));

    }
}
