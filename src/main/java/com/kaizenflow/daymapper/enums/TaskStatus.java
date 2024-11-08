package com.kaizenflow.daymapper.enums;

import com.kaizenflow.daymapper.converter.AbstractEnumConverter;

public enum TaskStatus {
  PENDING,
  IN_PROGRESS,
  COMPLETED,
  CANCELLED;

  public boolean isPending() {
    return this == PENDING;
  }

  public boolean isInProgress() {
    return this == IN_PROGRESS;
  }

  public boolean isCompleted() {
    return this == COMPLETED;
  }

  public boolean isCancelled() {
    return this == CANCELLED;
  }

  public static class Converter extends AbstractEnumConverter<TaskStatus> {
    public Converter() {
      super(TaskStatus.class);
    }
  }
}
