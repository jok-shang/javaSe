package com.java.stream.不可变集合;

import java.util.Iterator;
import java.util.Set;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/15 23:07
 */
public class SetListBL {
    public static void main(String[] args) {
        /*
        创建不可变的set集合

        要获取不可变的set集合时要保证唯一性

         */

        Set<String> set = Set.of("张三", "李四", "王五", "赵六");

        for (String s : set){
            System.out.println(s);
        }

        System.out.println("-----迭代器----");

        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            String s = it.next();
            System.out.println(s);
        }

        System.out.println("--------");
        /*
        删除失败
         */
        set.remove("王五");
    }
}
