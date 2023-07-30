package com.heima.stream.stream;

import java.util.HashMap;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/16 13:47
 */
public class StreamDemo03 {
    public static void main(String[] args) {
     /*
      双列集合    无      无法直接使用stream流
     */

        // 1. 创建双列集合
        HashMap<String, Integer> hm = new HashMap<>();
        // 2. 添加数据
        hm.put("aaa",111);
        hm.put("bbb",222);
        hm.put("ccc",333);
        hm.put("ddd",444);

        // 3. 第一种获取stream流 将双列集合转换为set单列集合 取出键
        hm.keySet().stream().forEach(System.out::println);
        //  4. 第二种获取stream流  键值对
        hm.entrySet().stream().forEach(s -> System.out.println(s));
    }
}
