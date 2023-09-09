package com.sangeng.stream;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/7/31 22:18
 */
public class StreamDemo {
    public static void main(String[] args) {


        // 获取一个年龄最小的作家，并输出他的名字
        test22();
/*        // 获取一个年龄最小的作家，并输出他的名字
        test21();*/
/*        // 获取任意一个大于18的作家，如果存在就输出他的名字
        test20();*/
/*        // 判断作家是否都没有超过100岁的
        test19();*/
/*        // 判断是否所有的作家都是成年人
        test18();*/
/*        // 获取一个map集合，map的key为作者名，value为List<Book>
        test17();*/
/*        // 获取一个所有书名的Set集合
        test16();*/
/*        // 获取一个存放所有作者名字的List集合
        test15();*/
/*        // 分别获取这些作家的所出书籍的最高分和最低分并且打印
        test14();*/
/*//        打印这些作家的所出书籍的数目，注意删除重复元素
        test13();*/
/*        // 输出所有作家名字
        test12();*/
/*        // 打印现有数据的所有分类，要求对重复的元素进行去重。
        test11();*/
        /*//打印所有书籍的名字，要求对重复的元素进行去重
        test10();*/
/*        // 打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序
        test09();*/
        /*//对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素，然后打印其中年龄最大的两个作家的姓名
        test08();*/
        /*// 对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素
        test07();*/
        /*// 打印所有作家的姓名，并且要求其中不能有重复元素
        test06();*/
        /*// 打印所有作家的姓名
        test05();*/
        /*
        // 打印所有姓名长度大于1的作家姓名
        test04();
        */
        /*
        // 双列集合转换
        test03();
        */
        /*        
        //数组操作
        test02();
        */
        /*
        // 打印所有年龄小于18的作家的名字，并且去重
        test01();
        */


    }

    // 获取一个年龄最小的作家，并输出他的名字
    private static void test22() {
        List<Author> authors = getAuthors();
        authors.stream()
                .reduce()
    }

    // 获取一个年龄最小的作家，并输出他的名字
    private static void test21() {
        List<Author> authors = getAuthors();
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        first.ifPresent(s -> System.out.println(s.getName()));
    }

    // 获取任意一个大于18的作家，如果存在就输出他的名字
    private static void test20() {
        List<Author> authors = getAuthors();
        Optional<Author> any = authors.stream()
                .filter(s -> s.getAge() > 18)
                .findAny();
        // 判断是否存在 存在输出 不存在不会报空指针异常
        any.ifPresent(s -> System.out.println(s.getName()));
    }

    // 判断作家是否都没有超过100岁的
    private static void test19() {
        List<Author> authors = getAuthors();
        boolean b = authors.stream()
                .noneMatch(s -> s.getAge() > 100);
        System.out.println(b);
    }

    // 判断是否所有的作家都是成年人
    private static void test18() {
        List<Author> authors = getAuthors();
        boolean flag = authors.stream()
                .anyMatch(s -> s.getAge() > 18);
        System.out.println(flag);
    }

    // 获取一个map集合，map的key为作者名，value为List<Book>
    private static void test17() {
        List<Author> authors = getAuthors();
        Map<String, List<Book>> collect = authors.stream()
                .distinct()
                .collect(Collectors.toMap(Author::getName, Author::getBooks));
        System.out.println(collect);
    }

    // 获取一个所有书名的Set集合
    private static void test16() {
        List<Author> authors = getAuthors();
        Set<String> collect = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getName)
                .collect(Collectors.toSet());
        System.out.println(collect);
    }

    // 获取一个存放所有作者名字的List集合
    private static void test15() {
        List<Author> authors = getAuthors();
        List<String> nameList = authors.stream()
                .map(Author::getName)
                .collect(Collectors.toList());
        System.out.println(nameList);
    }

    // 分别获取这些作家的所出书籍的最高分和最低分并且打印
    private static void test14() {
        List<Author> authors = getAuthors();
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getScore)
                .max((score1, score2) -> score1 - score2);

        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getScore)
                .min((score1, score2) -> score1 - score2);
        System.out.println(max.get());
        System.out.println(min.get());

    }

    // 打印这些作家的所出书籍的数目，注意删除重复元素
    private static void test13() {
        List<Author> authors = getAuthors();
        long count = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .count();
        System.out.println(count);

    }

    // 输出所有作家名字
    private static void test12() {
        List<Author> authors = getAuthors();
        authors.stream()
                .map(Author::getName)
                .distinct()
                .forEach(System.out::println);
    }

    // 打印现有数据的所有分类，要求对重复的元素进行去重。
    private static void test11() {
        List<Author> authors = getAuthors();
        // 使用流处理作者列表
        authors.stream()
                // 将每个作者的书籍流扁平化为单一流
                .flatMap(author -> author.getBooks()
                        .stream())
                // 对书籍进行去重，确保每本书只被处理一次
                .distinct()
                // 将书籍的分类字符串拆分成多个分类，并将其扁平化为单一流
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                // 对分类进行去重，确保每个分类只被处理一次
                .distinct()
                // 对每个分类执行打印操作
                .forEach(System.out::println);
    }

    // 打印所有书籍的名字，要求对重复的元素进行去重
    private static void test10() {
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(s -> s.getBooks()
                        // 将实体类中集合再做流操作
                        .stream()
                        .map(Book::getName))
                .distinct()
                .forEach(System.out::println);
    }

    // 打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序
    private static void test09() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted(Comparator.comparing(Author::getAge).reversed())
                .skip(1)
                .forEach(s -> System.out.println(s.getName()));
    }

    //对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素，然后打印其中年龄最大的两个作家的姓名
    private static void test08() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted(Comparator.comparing(Author::getAge).reversed())
                .limit(2)
                .forEach(s -> System.out.println(s.getName()));
    }

    // 对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素
    private static void test07() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted(Comparator.comparing(Author::getAge).reversed())
                .forEach(s -> System.out.println(s.getName()));
    }

    // 打印所有作家的姓名，并且要求其中不能有重复元素
    private static void test06() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .forEach(s -> System.out.println(s.getName()));
    }

    // 打印所有作家的姓名
    private static void test05() {
        List<Author> authors = getAuthors();
        authors.stream()
                .map(Author::getName)
                .forEach(System.out::println);
    }

    // 打印所有姓名长度大于1的作家姓名
    private static void test04() {
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(s -> s.getName().length() > 1)
                .forEach(s -> System.out.println(s.getName()));
    }

    // 双列集合转换
    private static void test03() {
        Map<String,Integer> map = new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("黑子",17);
        map.put("襄阳",20);
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Stream<Map.Entry<String, Integer>> stream = entrySet.stream();
        stream.filter(s -> s.getValue() > 17).forEach(System.out::println);
    }

    //数组操作
    private static void test02() {
        Integer[] arr = {1,2,3,4,5};
//        Stream<Integer> stream = Arrays.stream(arr);
        Stream<Integer> stream = Stream.of(arr);
        stream.distinct().forEach(System.out::println);
    }

    private static void test01() {
        List<Author> authors = getAuthors();
        /*
        打印所有年龄小于18的作家的名字，并且去重
         */
        authors.stream()
                .filter(s -> s.getAge() < 18)
                .distinct()
                .forEach(s -> System.out.println(s.getName()));
    }

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        Author author2 = new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null);
        Author author3 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);
        Author author4 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L,"刀的两侧是光明与黑暗","哲学,爱情",88,"用一把刀划分了爱恨"));
        books1.add(new Book(2L,"一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(4L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author,author2,author3,author4));
        return authorList;
    }
}
