package com.inspirecamp7.myspringbootproject4.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Order(1)
@Slf4j
public class DBRunner implements ApplicationRunner {
    @Autowired
    DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //HikariDataSource implements DataSource
        log.info("DataSource 구현 클래스 이름 = {}", dataSource.getClass().getName());    }
}
