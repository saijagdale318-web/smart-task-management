
package com.example.smart_task_management;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskControllerTest {

    @Test
    void testCreateTask() {
        Task task = new Task();

        task.setId(1L);
        task.setTitle("DevOps Project");
        task.setDescription("JUnit Testing");
        task.setAssignedTo("Sai");
        task.setPriority("High");
        task.setStatus("Pending");

        assertEquals(1L, task.getId());
        assertEquals("DevOps Project", task.getTitle());
        assertEquals("JUnit Testing", task.getDescription());
        assertEquals("Sai", task.getAssignedTo());
        assertEquals("High", task.getPriority());
        assertEquals("Pending", task.getStatus());
    }

    @Test
    void testUpdateTaskStatus() {
        Task task = new Task();
        task.setStatus("Pending");

        task.setStatus("Completed");

        assertEquals("Completed", task.getStatus());
    }

    @Test
    void testTaskTitle() {
        Task task = new Task();
        task.setTitle("Smart Task Management");

        assertNotNull(task.getTitle());
        assertEquals("Smart Task Management", task.getTitle());
    }
}
