# javaSe

# 好的文章链接：

- <a href="https://blog.csdn.net/zxl646801924/article/details/90374320">Stream教程</a>
-

## Stream流的中间方法

### src/main/java/com/java.stream/stream02

![img.png](image/img.png)
![img.png](image/img_1.png)

# 1.创建流对象

## 单列集合： 集合对象.stream

```java
List<Author> authors=getAuthors();
        Stream<Author> stream=authors.stream;
```

## 数组： Arrays.stream(数组) 或者使用Stream.of()来创建

```java
Integer[]arr={1,2,3,4,5};
        Stream<integer> stream=Arrays.stream(arr);
        Stream<Integer> stream2=Stream.of(arr);
```

## 双列集合：转换成单列集合后再创建

```java
Map<String, Integer> map=new HashMap<>();
        map.put("蜡笔小新",19);
        map.put("黑子",17);
        map.put("襄阳",20);
        Set<Map.Entry<String, Integer>>entrySet=map.entrySet();
        Stream<Map.Entry<String, Integer>>stream=entrySet.stream();
        stream.filter(s->s.getValue()>17).forEach(System.out::println);
```

# 2.中间操作

## filter：

    可以对流中的元素进行条件过滤，符合条件的才能继续留在流中。

例如： 打印所有姓名长度大于1的作家姓名

```java
List<Author> authors=getAuthors();
        authors.stream()
        .filter(s->s.getName().length()>1)
        .forEach(s->System.out.println(s.getName()));
```

## map：

    可以把流中的元素进行计算或者转换

例如： 打印所有作家的姓名

```java
List<Author> authors=getAuthors();
        authors.stream()
        .map(Author::getName)
        .forEach(System.out::println);
```

## distinct：

    可以去除流中重复的元素

例如： 打印所有作家的姓名，并且要求其中不能有重复元素

```java
 List<Author> authors=getAuthors();
        authors.stream()
        .distinct()
        .forEach(s->System.out.println(s.getName()));
```
*注意：distinct方法是依赖Object的equals方法来判断是否是相同方法的，所以需要注意重写equals方法*

## sorted：
    可以对流中的元素排序

例如：对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素

```java
// 升序
List<Author> authors = getAuthors();
        authors.stream()
        .distinct()
        .sorted(Comparator.comparing(Author::getAge))
        .forEach(s -> System.out.println(s.getName()));
// 降序
List<Author> authors = getAuthors();
        authors.stream()
        .distinct()
        .sorted(Comparator.comparing(Author::getAge).reversed())
        .forEach(s -> System.out.println(s.getName()));
```
* 注意：如果调用空参的sorted()方法，需要流中的元素是实现了Comparable（实体类中）*

## limit：
    可以设置流的最大长度，超出的部分将被抛弃（取多少条数据）
例如：对流中的元素按照年龄进行降序排序，并且要求不能有重复的元素，然后打印其中年龄最大的两个作家的姓名
```java
List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted(Comparator.comparing(Author::getAge).reversed())
                .limit(2)
                .forEach(s -> System.out.println(s.getName()));
```