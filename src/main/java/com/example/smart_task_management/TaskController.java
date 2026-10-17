package com.example.smart_task_management;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(counter.incrementAndGet());

        if (task.getStatus() == null) {
            task.setStatus("Pending");
        }

        if (task.getPriority() == null) {
            task.setPriority("Medium");
        }

        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @RequestBody Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setAssignedTo(updatedTask.getAssignedTo());
                task.setPriority(updatedTask.getPriority());
                task.setStatus(updatedTask.getStatus());
                return task;
            }
        }

        throw new TaskNotFoundException(id);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        boolean removed = tasks.removeIf(
            task -> task.getId().equals(id)
        );

        if (!removed) {
            throw new TaskNotFoundException(id);
        }

        return "Task deleted successfully";
    }
}
