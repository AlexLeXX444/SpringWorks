package ru.app.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.application.entity.Task;
import ru.app.application.entity.TaskClass;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskClass(TaskClass taskClass);
    List<Task> findByTaskClassId(Long taskClassId);
    List<Task> findByTaskClass_ClassName(String className);
}
