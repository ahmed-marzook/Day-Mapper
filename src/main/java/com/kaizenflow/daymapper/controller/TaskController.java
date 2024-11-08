package com.kaizenflow.daymapper.controller;

import com.kaizenflow.daymapper.exception.ResourceNotFoundException;
import com.kaizenflow.daymapper.model.task.TaskRequest;
import com.kaizenflow.daymapper.model.task.TaskResponse;
import com.kaizenflow.daymapper.model.task.TaskUpdateRequest;
import com.kaizenflow.daymapper.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
@Tag(name = "Task", description = "Task management endpoints")
public class TaskController {

  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @Operation(
      summary = "Create new Task",
      description =
          "Creates a new task for the authenticated user. The task includes title, description, and due date.",
      security = {@SecurityRequirement(name = "basicAuth")})
  @PostMapping()
  public ResponseEntity<TaskResponse> createNewTask(
      @RequestBody @Valid TaskRequest taskRequest, Principal principal) {
    TaskResponse response = taskService.createTask(taskRequest, principal.getName());
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @Operation(
      summary = "Get all Tasks",
      description = "Get all tasks for the authenticated user.",
      security = {@SecurityRequirement(name = "basicAuth")})
  @GetMapping()
  public ResponseEntity<List<TaskResponse>> getAllTasks(Principal principal) {
    List<TaskResponse> tasks = taskService.getAllTasksForUser(principal.getName());
    return ResponseEntity.ok(tasks);
  }

  @Operation(
      summary = "Update Task",
      description = "Update an existing task for the authenticated user.",
      security = {@SecurityRequirement(name = "basicAuth")})
  @PutMapping("/{taskId}")
  public ResponseEntity<TaskResponse> updateTask(
      @PathVariable @Positive(message = "Task ID must be positive") Long taskId,
      @RequestBody @Valid TaskUpdateRequest taskUpdateRequest,
      Principal principal)
      throws ResourceNotFoundException {
    TaskResponse response = taskService.updateTask(taskId, taskUpdateRequest, principal.getName());
    return ResponseEntity.ok(response);
  }
}
