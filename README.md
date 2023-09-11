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
<font color=red>*注意：distinct方法是依赖Object的equals方法来判断是否是相同方法的，所以需要注意重写equals方法*</font>

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

## skip 
    跳过流中的前n个元素，返回剩下的元素
例如：打印除了年龄最大的作家外的其他作家，要求不能有重复元素，并且按照年龄降序排序
```java
List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted(Comparator.comparing(Author::getAge).reversed())
                .limit(2)
                .forEach(s -> System.out.println(s.getName()));
```

## flatMap 
    map只能把一个对象转换成另一个对象来作为流中的元素，而flatMap可以把一个对象转换成多个对象作为流中的元素

例一： 打印所有书籍的名字，要求对重复的元素进行去重
```java
List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(s -> s.getBooks()
                        // 将实体类中集合再做流操作
                        .stream()
                        .map(Book::getName))
                .distinct()
                .forEach(System.out::println);
```
例二： 打印现有数据的所有分类，要求对重复的元素进行去重。
```java
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
```


# 终结操作

## foreach
    对流中的元素进行遍历操作，我们通过传入的参数去指定对遍历到的元素进行什么具体的操作

例子：输出所有作家的名字
```java
List<Author> authors = getAuthors();
        authors.stream()
                .map(Author::getName)
                .distinct()
                .forEach(System.out::println);
```

## count
    可以用来获取当前流中元素的个数
例子： 打印这些作家的所出书籍的数目，注意删除重复元素

```java
List<Author> authors=getAuthors();
        long count=authors.stream()
        .flatMap(author->author.getBooks().stream())
        .distinct()
        .count();
        System.out.println(count);
```

## max&min
    可以用来获取流中的最值
例子：分别获取这些作家的所出书籍的最高分和最低分并且打印
```java
List<Author> authors=getAuthors();
        Optional<Integer> max=authors.stream()
        .flatMap(author->author.getBooks().stream())
        .map(Book::getScore)
        .max((score1,score2)->score1-score2);

        Optional<Integer> min=authors.stream()
        .flatMap(author->author.getBooks().stream())
        .map(Book::getScore)
        .min((score1,score2)->score1-score2);
        System.out.println(max.get());
        System.out.println(min.get());
```

## collect
    把当前流转换成一个集合

例子：获取一个存放所有作者名字的List集合
```java
List<Author> authors=getAuthors();
        List<String> nameList=authors.stream()
        .map(Author::getName)
        .collect(Collectors.toList());
        System.out.println(nameList);
```
获取一个所有书名的Set集合
```java
List<Author> authors = getAuthors();
        Set<String> collect = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .map(Book::getName)
                .collect(Collectors.toSet());
        System.out.println(collect);
```
获取一个map集合，map的key为作者名，value为List<Book>
```java
List<Author> authors = getAuthors();
        Map<String, List<Book>> collect = authors.stream()
                .distinct()
                .collect(Collectors.toMap(Author::getName, Author::getBooks));
        System.out.println(collect);
```
## *查找与匹配*
### allMatch
    可以用来判断是否都符合匹配条件，结果为boolean类型，如果都符合结果为true，否则为false.

例子： 判断是否所有的作家都是成年人
```java
List<Author> authors = getAuthors();
        boolean flag = authors.stream()
                .anyMatch(s -> s.getAge() > 18);
        System.out.println(flag);
```

### noneMatch
    可以判断流中的元素是否都不符合匹配条件，如果都不符合结果为true，否则为false

例子：判断作家是否都没有超过100岁的
```java
List<Author> authors = getAuthors();
        boolean b = authors.stream()
                .noneMatch(s -> s.getAge() > 100);
        System.out.println(b);
```

### findAny
    获取流中的任意一个元素，该方法没有办法保证获取的一定是流中的第一个元素

例子：获取任意一个大于18的作家，如果存在就输出他的名字
```java
List<Author> authors = getAuthors();
        Optional<Author> any = authors.stream()
                .filter(s -> s.getAge() > 18)
                .findAny();
        // 判断是否存在 存在输出 不存在不会报空指针异常
        any.ifPresent(s -> System.out.println(s.getName()));
```

### findFirst
    获取流中的第一个元素

例子：获取一个年龄最小的作家，并输出他的名字
```java
List<Author> authors = getAuthors();
        Optional<Author> first = authors.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge())
                .findFirst();
        first.ifPresent(s -> System.out.println(s.getName()));
```
## reduce
### reduce两个参数的重载形式内部计算方式如下 
    对流中的数据按照你制定的计算方式计算出一个结果
reduce的作用是把stream中的元素结合起来，我们可以传入一个初始值，她会按照我们的计算方式依次拿流中
的元素和在初始化值的基础上进行计算，计算结果再和后面的元素计算
```java
// 它内部的计算方式如下
T result = identity;
for (T element : this stream)
    result = accumulator.apply(result,element)
return result;
// 其中identity就是我们可以通过方法参数传入的初始值，accumulator的apply具体进行什么计算也是我们通过方法参数来确定的。
```

例子： 使用reduce求所有作者年龄的和
```java
List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .distinct()
                .map(Author::getAge)
                // result:返回值 element:循环元素
//                .reduce(0,(result,element) -> result + element);
                .reduce(0, Integer::sum);
        System.out.println(reduce);
```
使用reduce求所有作者中年龄的最大值
```java
List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .distinct()
                .map(Author::getAge)
                .reduce(Integer.MIN_VALUE, (result, element) -> result < element ? element : result);
        System.out.println(reduce);
```
使用reduce求所有作者中年龄的最小值
```java
List<Author> authors = getAuthors();
        Integer reduce = authors.stream()
                .map(Author::getAge)
                .reduce(Integer.MAX_VALUE, (result, element) -> result > element ? element : result);
        System.out.println(reduce);
```
### reduce 一个参数重载形式
```java
// 一个参数内部方法
boolean foundAny = false;
     T result = null;
     // 第一个元素作为初始化值
     for (T element : this stream) {
         if (!foundAny) {
             foundAny = true;
             result = element;
         }
         else
             result = accumulator.apply(result, element);
     }
     return foundAny ? Optional.of(result) : Optional.empty();
 
```
例子：使用reduce求所有作者中年龄的最小值
```java
List<Author> authors = getAuthors();
        Optional<Integer> minOptional = authors.stream()
                .map(Author::getAge)
                .reduce(new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return result > element ? element : result;
                    }
                });
        System.out.println(minOptional.get());
```
# <font color = red>注意事项</font>
* 惰性求值(如果没有终结操作，没有中间操作是不会得到执行的)
* 流是一次性的(一旦一个流对象经过一个终结操作后，这个流就不能再被使用)
* 不会影响原数据(我们在流中可以多数据做很多处理，但是正常情况下是不会影响原来集合中的元素的。这往往也是我们期望的)

# Optional
## 概述
我们在编写代码的时候出现最多的就是空指针异常，所以在很多情况下我们需要做各种非空的判断
例如：
```java
Author author = getAuthor();
if (author != null){
    System.out.println(author.getName());
        }
/* 
    尤其是对象中的属性还是一个对象的情况下，这种判断会更多
    而过多的判断语句会让我们的代码显得臃肿不堪。
    所以在JDK8中引入了`Optional`的习惯后你可以写出更优雅的代码来避免空指针异常
    并且在很多函数式编程相关的API中也都用到了`Optional`，如果不会使用`Optional`也会对函数式编程的学习造成影响
*/ 
```
## 使用
### 创建对象
#### ofNullable 判断不为空
  `Optional`就好像是包装类，可以把我们的具体数据封装`Optional`对象内部，然后我们去使用`Optional`中封装好的方法操作，
封装进去的数据就可以非常优雅的避免空指针异常
  我们一般使用``Optional`的*静态方法ofNullable*来把数据封装成`Optional`对象，无论传入的参撒是否为null都不会出现问题。
```java
Author author = getAuthor();
Optional<Author> authorOptional = Optional.ofNullable(author);
```
  dao层也支持封装成`Optional`,Mybatis会自己把数据封装成`Optional`对象返回,封装的过程不需要我们自己操作。
如果你*确定一个对象不是空*的则可以使用*Optional*的*静态方法of*来把数据封装成Optional
```java
Author author = getAuthor();
// 使用of时传入的参数必须不为null
Optional<Author> authorOptional = Optional.of(author);
```
#### Optional.empty()
  如果一个方法的返回值为`Optional`类型，而如果我们经常判断发现某次计算得到的返回值为null，这个时候就需要把null封装成`Optional`
对象返回，这时则可以使用*Optional*的*静态方法empty*来进行封装
```java
Optional.empty()
```

### 安全消费值
  我们获取到一个`Optional`对象后要对其中的数据进行使用。这个时候我们可以使用其ifPresent方法来对消费其中的值。
这个方法会判断其内封装的数据是否为空，不为空时才会执行具体的消费代码，这样使用起来更加安全。
例如：以下写法就优雅的避免了空指针异常。
```java
Optional<Author> authorOptional = optional.ofNullable(getAuthor());
authorOptional.ifPresent(author -> System.out.println(author.geTName))
```
### 获取值
  如果我们想获取值自己进行处理可以使用get方法获取，但是不推荐。因为当Optional内部的数据为空的时候会出现异常。

### 安全获取值
  如果我们期望安全的获取值。我们不推荐使用get方法，而是使用Optional内部的数据为空的时候会出现异常。
* orElseGet
  获取数据并且设置数据为空时的默认值，如果数据不为空就能获取到该数据。如果为空则根据你传入的参数来创建对象作为默认值返回
```java
Optional<Author> authorOptional = optional.ofNullable(getAuthor());
Author author = authorOptional.orElseGet(() -> new Author())
```
* orElseThrow
  获取数据，如果数据不为空就能获取到该数据，如果为空则根据你传入的参数来创建异常抛出
```java
Optional<Author> authorOptional=Optional.ofNullable(getAuthor());
        try{
        Author author=authorOptional.orElseThrow((supplier<Throwable>)()->new
        RuntimeException("author为空"));
        System.out.println(author.getName());
        }catch(Throwable throwable){
        throwable.printStackTrace();
        }

        // 抛出异常
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
        }
```

### 过滤
  我们可以使用filter方法对数据进行过滤，如果原本是有数据的，但是不符合判断，也会成为一个无数据的`Optional`对象。  
```java
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
```

### 判断
  我们可以使用`isPresent` 方法进行是否存在数据的判断。如果为空返回值为false，如果不为空，返回值为true。但是这种方式并不能提现
`Optional`的好处，更推荐使用`ifPresent`方法。
```java
Optional<Author> authorOptional = Optional.ofNullable(getAuthor());
if (authorOptional.isPresent()){
    System.out.println(authorOptional.get().getName());
        }
```

### 数据转换
  `Optional` 还提供了map可以让我们对数据的进行转换，并且转换得到的数据也还是被`Optional`包装好的，保证了我们的使用安全。
例如：我们想获取作家的书籍集合。
```java
Optional<Author> authorOptional = getAuthorOptional();
Optional<List<Book>> books1 = authorOptional.map(author -> author.getBooks());
books1.ifPresent(books -> System.out.println(books));
```
