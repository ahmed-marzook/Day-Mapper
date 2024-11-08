package com.kaizenflow.daymapper.model.task;

import com.kaizenflow.daymapper.enums.TaskStatus;
import java.time.ZonedDateTime;

public record TaskResponse(
    Long taskId, String title, String description, TaskStatus status, ZonedDateTime dueDate) {}
