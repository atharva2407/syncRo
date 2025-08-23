package com.syncro.syncRo.services;

import com.syncro.syncRo.models.Task;
import com.syncro.syncRo.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;


import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasksForUser(String userId) {
        return taskRepository.findByUserId(userId);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
    public List<Task> getTasksForUserPaginated(String userId, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return taskRepository.findByUserId(userId, pageable);
    }
    public Task getTaskById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }
}
