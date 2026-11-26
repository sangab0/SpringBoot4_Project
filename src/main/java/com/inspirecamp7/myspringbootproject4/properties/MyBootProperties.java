package com.inspirecamp7.myspringbootproject4.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("myboot")
@Getter @Setter
public class MyBootProperties {
    private String name;
    // myboot.age 설정값은 age에 주입되지만,
    // 자바 기본 직렬화로 객체를 파일에 저장하거나 전송할 때 age는 제외됨
    private transient int age;
    private String fullName;

}