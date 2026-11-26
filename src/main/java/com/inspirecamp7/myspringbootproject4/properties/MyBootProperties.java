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
    // transient: 자바 기본 직렬화 시 age를 저장 대상에서 제외
    // myboot.age 설정값을 주입받는 데는 영향을 주지 않음
    private transient int age;
    private String fullName;

}