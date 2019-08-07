package com.example.kafkaconnectmysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class KafkaConnectMysqlApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaConnectMysqlApplication.class, args);
  }
}
