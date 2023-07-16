package com.java.stream.不可变集合;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListBL {
    public static void main(String[] args) {
        /*
        List遍历
        List.of() 不能修改的list集合
         */
        List<String> list = List.of("张三", "李四", "王五", "赵六");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));

        System.out.println("----------");

        for (String s : list) {
            System.out.println(s);
        }
        System.out.println("---迭代器---");
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String s = it.next();
            System.out.println(s);
        }

        System.out.println("-----------");
        for (int i = 0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
}