package com.example.kafkaconnectmysql;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Consumer {

  @KafkaListener(topics = "db-todo-custom-query")
  public void execute(final String message) {
    log.info("Consumes: {}", message);
  }
}
