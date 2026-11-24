package com.inspirecamp7.myspringbootproject4.runner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Order(2)
public class MyRunner implements ApplicationRunner {
    @Value("${myboot.name}")
    String myBootName;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("환경변수 myboot.name = " + myBootName);
        System.out.println("server.port VM argument 확인" + args.containsOption("server.port"));
        System.out.println("myboot.name Program argument 확인" + args.containsOption("myboot.name"));

        // 환경변수 목록을 확인하기
        Set<String> optionNames = args.getOptionNames();
        // 기존의 for loop
        for (String name: optionNames){
            System.out.println("name = " + name); // soutv하면 생성됨
        }

        // Iterable의 default void foEach(Consumer<? super T action>
        // 함수형 인터페이스(Consumer)의 추상 메서드 void accept(T,t)
        // Consumer 인터페이스의 accept() 메서드를 람다식으로 표현
        optionNames.forEach(optionName -> System.out.println(optionName));

        // Consumer 인터페이스의 accept() 메서드를 Method Reference 로 표현하기
        optionNames.forEach(System.out::println);
    }
}
