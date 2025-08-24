package com.syncro.syncro.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.syncro.syncro.models.Task;
import com.syncro.syncro.models.User;
import com.syncro.syncro.services.TaskService;
import com.syncro.syncro.services.UserService;

import java.util.List;
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task, Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUserId(currentUser.getId());
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(
            Authentication authentication,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        User currentUser = userService.findByUsername(authentication.getName()).orElseThrow();
        return ResponseEntity.ok(taskService.getTasksForUserPaginated(currentUser.getId(), page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable String id, @RequestBody Task task, Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task existingTask = taskService.getTaskById(id);
        if (!existingTask.getUserId().equals(currentUser.getId())) {
            return ResponseEntity.status(403).body("You cannot update someone else's task");
        }

        task.setId(id);
        task.setUserId(currentUser.getId());
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id, Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // Optionally, verify that the task belongs to currentUser before deleting
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/complete")
    public ResponseEntity<?> markCompleted(@PathVariable String id, Authentication authentication) {
        User currentUser = userService.findByUsername(authentication.getName()).orElseThrow();
        Task task = taskService.getTaskById(id);

        if (!task.getUserId().equals(currentUser.getId())) {
            return ResponseEntity.status(403).body("Unauthorized");
        }

        task.setCompleted(true);
        return ResponseEntity.ok(taskService.updateTask(task));
    }
}
