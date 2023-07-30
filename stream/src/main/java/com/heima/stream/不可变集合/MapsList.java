package com.heima.stream.不可变集合;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/15 23:46
 */
public class MapsList {
    public static void main(String[] args) {
        /*
        创建map的不可变集合，键值对数量超过二十个
         */
        // 创建一个map集合
        HashMap<String,String> hm = new HashMap<>();
        hm.put("张三","南京");
        hm.put("李四","武汉");
        hm.put("王五","西安");
        hm.put("赵六","青岛");

        // 获取 一个不可变的map

        // 获取到所有的键值对对象
//        Set<Map.Entry<String, String>> entries = hm.entrySet();
//        // 把entries 变成一个数组
//        // Map.Entry[] arr = entries.toArr(new Map.Entry[0])
//
//        Map.Entry[] arr1 = new Map.Entry[0];
//        // toArr方法在底层会比较集合的长度限制数组的长度两者的大小
//        // 如果集合的长度 》 数组的长度； 数据在数组中放不下，此时会
//        // 根据实际数据的个数，重新创建数组
//        // 如果集合的长度 《= 数组的长度；数据在数组中放的下，此时不会创建新的数组，而是直接使用
//        Map.Entry[] arr2 = entries.toArray(arr1);
//        // 不可变的map集合
//        Map map = Map.ofEntries(arr2);
//        map.put("111","222");

        /*
        优化上面方法
         */
//        Map<Object, Object> map = Map.ofEntries(hm.entrySet().toArray(new Map.Entry[0]));
//        map.put("qq","q");

        /*
        继续优化
         */

        Map<String, String> map = Map.copyOf(hm);
        map.put("aa","aa");
    }
}
