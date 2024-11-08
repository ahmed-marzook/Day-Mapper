package com.kaizenflow.daymapper.controller;

import com.kaizenflow.daymapper.model.task.TaskCreateRequest;
import com.kaizenflow.daymapper.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
  public String createNewTask(
      @RequestBody @Valid TaskCreateRequest taskCreateRequest, Principal principal) {
    System.out.println(principal.getName());
    return "Hello World";
  }

  @Operation(
      summary = "Get all Task",
      description =
          "Get all tasks for the authenticated user.",
      security = {@SecurityRequirement(name = "basicAuth")})
  @GetMapping()
  public String getAllTask(Principal principal) {
    System.out.println(principal.getName());
    return "Hello World";
  }
}
