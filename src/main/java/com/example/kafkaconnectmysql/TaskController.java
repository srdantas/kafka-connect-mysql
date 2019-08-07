package com.example.kafkaconnectmysql;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

  private final TaskRepository taskRepository;

  @PostMapping
  public long create() {
    return taskRepository
        .save(
            TaskEntity.builder()
                .name("DEVELOP")
                .status(StatusEntity.builder().id(1L).build())
                .build())
        .getId();
  }
}
