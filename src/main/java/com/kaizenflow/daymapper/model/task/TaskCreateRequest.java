package com.kaizenflow.daymapper.model.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;

public record TaskCreateRequest(
    @NotBlank(message = "Title of task cannot be empty")
        @Size(min = 1, max = 30, message = "Task title must be between 2 and 30 characters")
        String title,
    String description,
    ZonedDateTime dueDate) {}
