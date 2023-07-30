package com.heima.stream.不可变集合;

import java.util.Map;
import java.util.Set;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/15 23:12
 */
public class MapBL {
    public static void main(String[] args) {
        /*
        创建map的不可变集合
        键不能重复
        map 里面的of方法参数是有限制的  十个键值对  二十个参数
        要传递多个键值对
         */

        Map<String,String> map = Map.of("张三","南京","李四","武汉","王五","陕西");

        // 获取到map的所有键
        Set<String> keys = map.keySet();
        for (String key : keys){
            String value = map.get(key);
            System.out.println("keys:"+key+"values:"+value);
        }
        System.out.println("----------");
        // set集合装的map
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String,String> entry : entries){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("keys:"+key+"values:"+value);
        }
    }
    /*
    让这个方法可以接受的多个键和值
    解决方案
    键  可变参数 值 可变参数
    类型不确定 泛型方法
     */
    public static<k,v> void of(){

    }
}
