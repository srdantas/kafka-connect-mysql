package com.example.kafkaconnectmysql;

import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(name = "task")
public class TaskEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "NAME")
  private String name;

  @ManyToOne
  @JoinColumn(name = "status")
  private StatusEntity status;

  @UpdateTimestamp
  @Column(name = "DATE", nullable = false)
  private Instant date;
}
