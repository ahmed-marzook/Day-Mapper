package com.kaizenflow.daymapper.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long taskId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(nullable = false)
  private String title;

  private String description;

  @Column(nullable = false)
  private String status = "pending";

  @Column(name = "due_date")
  private ZonedDateTime dueDate;

  @Column(name = "created_at", nullable = false)
  private ZonedDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private ZonedDateTime updatedAt;
}
