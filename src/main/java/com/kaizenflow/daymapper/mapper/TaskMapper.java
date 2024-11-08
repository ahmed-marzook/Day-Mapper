package com.kaizenflow.daymapper.mapper;

import com.kaizenflow.daymapper.entities.Task;
import com.kaizenflow.daymapper.model.task.TaskResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
  TaskResponse taskToTaskResponse(Task task);
}
