package com.kaizenflow.daymapper.repository;

import com.kaizenflow.daymapper.entities.Task;
import com.kaizenflow.daymapper.entities.User;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByUser(User user);

  List<Task> findByUserAndStatus(User user, String status);

  List<Task> findByUserOrderByDueDateAsc(User user);

  List<Task> findByUserAndDueDateBefore(User user, ZonedDateTime date);

  List<Task> findByUserAndDueDateBetween(User user, ZonedDateTime start, ZonedDateTime end);

  Optional<Task> findByTaskIdAndUser(Long taskId, User user);
}
