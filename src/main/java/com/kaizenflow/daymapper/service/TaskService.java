package com.kaizenflow.daymapper.service;

import com.kaizenflow.daymapper.entities.Task;
import com.kaizenflow.daymapper.entities.User;
import com.kaizenflow.daymapper.enums.TaskStatus;
import com.kaizenflow.daymapper.exception.ResourceNotFoundException;
import com.kaizenflow.daymapper.mapper.TaskMapper;
import com.kaizenflow.daymapper.model.task.TaskRequest;
import com.kaizenflow.daymapper.model.task.TaskResponse;
import com.kaizenflow.daymapper.model.task.TaskUpdateRequest;
import com.kaizenflow.daymapper.repository.TaskRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class TaskService {

  private final TaskRepository taskRepository;

  private final UserService userService;

  private final TaskMapper taskMapper;

  @Autowired
  public TaskService(
      TaskRepository taskRepository, UserService userService, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.userService = userService;
    this.taskMapper = taskMapper;
  }

  public TaskResponse createTask(@Valid TaskRequest request, @NotBlank String username) {
    User user = userService.getUserByEmail(username);

    Task task =
        Task.builder()
            .user(user)
            .title(request.title())
            .description(request.description())
            .status(TaskStatus.PENDING)
            .dueDate(request.dueDate())
            .build();

    Task savedTask = taskRepository.save(task);

    return taskMapper.taskToTaskResponse(savedTask);
  }

  public List<TaskResponse> getAllTasksForUser(String username) {
    User user = userService.getUserByEmail(username);
    List<Task> tasks = taskRepository.findByUser(user);

    return tasks.stream().map(taskMapper::taskToTaskResponse).collect(Collectors.toList());
  }

  public TaskResponse updateTask(
      @NotNull Long taskId,
      @Valid
          TaskUpdateRequest request, // Keep @Valid here as service might be called from elsewhere
      @NotBlank String username)
      throws ResourceNotFoundException {

    User user = userService.getUserByEmail(username);

    Task task =
        taskRepository
            .findByTaskIdAndUser(taskId, user)
            .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

    task.setTitle(request.title());
    task.setDescription(request.description());
    task.setStatus(request.status());
    task.setDueDate(request.dueDate());

    Task updatedTask = taskRepository.save(task);
    return taskMapper.taskToTaskResponse(updatedTask);
  }
}
