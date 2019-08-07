package com.example.kafkaconnectmysql;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("status")
@RequiredArgsConstructor
public class StatusController {

  private final StatusRepository statusRepository;

  @PostMapping
  public long create() {
    return statusRepository.save(StatusEntity.builder().description("IN-PROGRESS").build()).getId();
  }
}
