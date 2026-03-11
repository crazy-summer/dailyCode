package com.liuao.monoflux;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.security.cert.CollectionCertStoreParameters;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.max;

@SpringBootTest
class MonofluxApplicationTests {

    @Test
    void contextLoads() {
        int a = max(Integer.MAX_VALUE, 4);
    }

    @Test
    void test1() {
        String body = "hello";
        Flux<String> just = Flux.just(body, "world").map(s -> {
            String s1 = "";
            try {

                throw new RuntimeException("test");

            } catch (Exception e) {
                e.printStackTrace();
            }
            s1 = s.toUpperCase(Locale.ROOT);

            return s1;
        });

        just.subscribe(System.out::println,
                e -> e.printStackTrace(),
                () -> {
                    System.out.println(11);
                });
    }

    @Test
    void test02() {
//		IntStream intStream = IntStream.of(1, 2, 3);
//		int sum = intStream.sum();
//
//		IntStream.range(1,10).forEachOrdered(this::myPrint);

        // 使用random创建一个无限流
        DoubleStream doubleStream = new Random().doubles().limit(20).map(d -> Math.round(d * 1000));

        doubleStream.forEachOrdered(this::myPrint);

        Stream<List<Integer>> generate = Stream.generate(() -> Arrays.asList(1, 2, 3)).limit(5);
        generate.forEach(System.out::println);
    }

    void myPrint(Object o) {
        System.out.print(o + " ");
    }

    class peihuolong {
        int attack;
        int defense;

        void doubleAttack(Function<Integer, Integer> fucntion) {

        }
    }


    @Test
    void test03() {
        String str = "my name is 007";
        Stream.of(str.split(" ")).flatMap(s -> s.chars().boxed()).forEach(i -> System.out.println((char) i.intValue()));
    }

    @Test
    void test04() {
        String str = "my name is 007";
        Stream.of(str.split(" ")).peek(this::myPrint).forEach(this::myPrint);
    }

    @Test
    void test05() {
        // 拼接单词
//		String str = "my name is 007";
//		String s = Stream.of(str.split(" ")).reduce((s1, s2) -> s1 + "|" + s2).get();
//		System.out.println(s);

        // 不带初始化值的reduce
        String[] arr = {"my", null, "name", "is", "0000007"};
        Optional<String> reduce = Stream.of(arr).reduce((s1, s2) -> s1 + "|" + s2);
        System.out.println(reduce.orElse(""));

        // 带初始化值的reduce
        String start = Stream.of(arr).reduce("start", (s1, s2) -> s1 + "|" + s2);
        System.out.println(start);

        String s3 = Stream.of(arr)
                .map(s -> Optional.ofNullable(s).orElse(""))
                .max((s1, s2) -> s1.length() - s2.length())
                .get();
        System.out.println(s3);

        // 短路操作findFirst
        OptionalInt first = new Random().ints().findFirst();

    }

    @Test
    void test06() {
        IntStream.range(1, 100).parallel().peek(this::debug).sum();
    }

    void debug(int i) {
        System.out.println("deBug:" + i);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test07() {
        TreeSet<Integer> collect = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toCollection(TreeSet::new));
    }
}
