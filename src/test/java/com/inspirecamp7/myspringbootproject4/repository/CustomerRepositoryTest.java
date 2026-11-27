package com.inspirecamp7.myspringbootproject4.repository;

import com.inspirecamp7.myspringbootproject4.entity.Customer;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// 스프링 부트 환경을 불러와 Bean과 DB 연결 설정 등을 사용하는 테스트
@SpringBootTest
public class CustomerRepositoryTest {
    // @Autowired: 스프링이 관리하는 Bean 중 타입에 맞는 객체를 찾아 자동으로 주입하는 어노테이션
    @Autowired
    CustomerRepository customerRepository;

    //1.Customer 등록
    @Test
    @Transactional
    //등록한 데이터를 반드시 확인하고 싶으니 Rollback 처리를 하지 마세요
    @Rollback(value = false)
    void testInsert() {
        // Given-When-Then
        // Given (준비단계)
        Customer customer = new Customer(); // ctrl + alt + v 이건 리턴타입 자동생성
        customer.setCustomerId("AB001");
        customer.setCustomerName("스프링AB");

        // When (실행단계)
        Customer addedCustomer = customerRepository.save(customer);

        // Then (검증단계)
        assertThat(addedCustomer).isNotNull();
        assertThat(addedCustomer.getCustomerId()).isEqualTo("AB001");
        assertThat(addedCustomer.getCustomerName()).isEqualTo("스프링AB");
    }

    //Customer 수정
    @Test
    void testUpdate() {

        Customer customer = customerRepository.findByCustomerId("A001")
                .orElseGet(() -> new Customer());
        customer.setCustomerName("SpringA");
        Customer updatedCustomer = customerRepository.save(customer);
        assertThat(updatedCustomer.getCustomerName()).isEqualTo("SpringA"));
    }

    // 2.Customer 조회 ( 값이 일치하는 Customer 존재하는 경우 )
    @Test
    @Transactional(readOnly = true)
    void testRead() {
        Optional<Customer> optionalCustomer = customerRepository.findByCustomerId("B001");
        // Optional이 담고 있는 Customer가 값을 가지고 있으면?
        if(optionalCustomer.isPresent()){
            Customer existCustomer = optionalCustomer.get();
            assertThat(existCustomer.getCustomerId()).isEqualTo("B001");
        }

        // 함수형 인터페이스 Customer 의 추상메서드 void accept(T t)
        optionalCustomer.ifPresent(customer -> System.out.println(customer.getCustomerName()));

    }

    // 3.Customer 조회 ( 값이 일치하는 Customer 존재하지 않는 경우 )
    @Test
    void testReadNotFound() {
        // Optional orElseGet(Supplier supplier)
        // 함수형인터페이스 Supplier 추상메서드 T get()

        Customer existCustomer = customerRepository.findByCustomerId("B001")  // Optional<Customer>
                .orElseGet(() -> new Customer());
        // Customer가 값이 있는 경우는 성공함
        assertThat(existCustomer.getCustomerId()).isNotNull();

        Customer notExistCustomer = customerRepository.findByCustomerId("F001")  // Optional<Customer>
                .orElseGet(() -> new Customer());
        // Customer가 값이 없는 경우는 성공함
        assertThat(notExistCustomer.getCustomerId()).isNull();
    }

    @Test
    void testReadNotFoundException() {
        //Optional orElseThrow(Supplier<? extends X> supplier)
        // <X extends Throwable>
        //함수형인터페이스 Supplier 추상메서드 X get()
        Customer existCustomer = customerRepository.findByCustomerId("B001")
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        assertThat(existCustomer.getCustomerId()).isNotNull();

        Customer notExistCustomer = customerRepository.findByCustomerId("F001")
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
        assertThat(notExistCustomer.getCustomerId()).isNull();
    }

}
