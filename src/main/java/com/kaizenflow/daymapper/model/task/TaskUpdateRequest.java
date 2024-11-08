package com.kaizenflow.daymapper.model.task;

import com.kaizenflow.daymapper.enums.TaskStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.ZonedDateTime;

public record TaskUpdateRequest(
    @NotBlank(message = "Title of task cannot be empty")
        @Size(min = 1, max = 30, message = "Task title must be between 2 and 30 characters")
        String title,
    @Size(max = 500, message = "Description must not exceed 500 characters") @Nullable
        String description,
    @NotNull(message = "Status cannot be empty.") TaskStatus status,
    @Future(message = "Due date must be in the future") @Nullable ZonedDateTime dueDate) {}
