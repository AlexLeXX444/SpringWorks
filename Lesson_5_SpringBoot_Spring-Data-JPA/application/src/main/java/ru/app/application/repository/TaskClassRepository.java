package ru.app.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.app.application.entity.TaskClass;

@Repository
public interface TaskClassRepository extends JpaRepository<TaskClass, Long> {
    TaskClass findTaskClassByClassName(String className);
}
