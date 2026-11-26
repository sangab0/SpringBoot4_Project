package com.inspirecamp7.myspringbootproject4.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity // 이 클래스를 DB 테이블과 연결하는 JPA 엔티티로 지정
@Table(name = "customers") // 연결할 테이블 이름을 customers로 지정
public class Customer {
    @Id // 각 행을 구분하는 기본키(PK)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 AUTO_INCREMENT로 기본키 자동 생성
    private Long id;

    @Column(nullable = false, unique = true) // NULL 및 중복 값 허용 안 함
    private String customerId;

    @Column(nullable = false) // NULL 허용 안 함
    private String customerName;

    @Column(nullable = false, unique = false) // NULL은 허용 안 하고, 중복 값은 허용
    @CreationTimestamp // DB에 처음 저장할 때 Hibernate가 생성 시간을 자동으로 설정
    LocalDateTime createdAt = LocalDateTime.now(); // 객체 생성 시 현재 날짜와 시간 저장

    // private String customerAddress;
}