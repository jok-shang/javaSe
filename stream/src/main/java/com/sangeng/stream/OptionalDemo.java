package com.sangeng.stream;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Author 尚智江
 * @description
 * @CreateDate 2023/9/10 15:59
 */
public class OptionalDemo {
    public static void main(String[] args) {

//        Author author = getAuthor();
//        // 判断是否为 null
//        Optional<Author> authorOptional = Optional.of(author);
//        authorOptional.ifPresent(Author::getBooks);

     /*   Optional<Author> authorOptional = getAuthorOptional();
        // 如果不为空则输出 name
        authorOptional.ifPresent(s -> System.out.println(s.getName()));*/


/*        Optional<Author> authorOptional = getAuthorOptional();
        // 内部判断是否为空  不为空封装成author输出
//        authorOptional.orElseGet(() -> new Author());
        Author author = authorOptional.orElseGet(Author::new);
        System.out.println(author);*/

/*        // 抛出异常
        Optional<Author> authorOptional = getAuthorOptional();
        try {
            authorOptional.orElseThrow(new Supplier<Throwable>() {
                @Override
                public Throwable get() {
                    return new RuntimeException("数据为null");
                }
            });
        } catch (Throwable e) {
            e.printStackTrace();
        }*/


        // 过滤
//        testFile();

        // 判断
//        testIsPressent();

        // map 数据转换
        testMap();
    }


    // 数据转换
    private static void testMap() {
        Optional<Author> authorOptional = getAuthorOptional();
        Optional<List<Book>> books1 = authorOptional.map(author -> author.getBooks());
        books1.ifPresent(books -> System.out.println(books));
    }

    // 判断
    private static void testIsPressent() {
        Optional<Author> authorOptional = Optional.ofNullable(getAuthor());
        if (authorOptional.isPresent()){
            System.out.println(authorOptional.get().getName());
        }
    }

    // 过滤
    private static void testFile(){
        Optional<Author> authorOptional = getAuthorOptional();
//        authorOptional.filter(new Predicate<Author>() {
//            @Override
//            public boolean test(Author author) {
//                return author.getAge() > 18;
//            }
//        });
        authorOptional
                .filter(s -> getAuthor().getAge() > 18)
                .ifPresent(author -> System.out.println(author.getName()));

    }

    public static Optional<Author> getAuthorOptional(){
        Author author = new Author(1L,"蒙多",33,"祖安人",null);
        return Optional.ofNullable(author);
    }

    public static Author getAuthor(){
        Author author = new Author(1L,"蒙多",33,"祖安人",null);
        return author;
    }
}
