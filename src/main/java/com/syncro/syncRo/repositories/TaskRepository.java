package com.syncro.syncro.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.syncro.syncro.models.Task;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    List<Task> findByUserId(String userId);
    List<Task> findByUserId(String userId, Pageable pageable);
    List<Task> findByUserIdOrderByDueDateAsc(String userId);
}
