package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TaskTest {
    @Test
    void testTaskConstructor() {
        Task task = new Task(20201013,"cpsc", "goodjob", false);
        assertEquals(20201013,task.getDeadline());
        assertEquals("cpsc",task.getTaskTitle());
        assertEquals("goodjob",task.getTaskContent());
        assertFalse(task.getIfMarkDone());
    }


    @Test
    void testTaskConstructorWithoutIfMarkDone() {
        Task task = new Task(20201024,"CPSC", "great");
        assertEquals(20201024,task.getDeadline());
        assertEquals("CPSC",task.getTaskTitle());
        assertEquals("great",task.getTaskContent());

    }

    @Test
    void testTaskSetters() {
        Task task = new Task(20201013,"cpsc", "goodjob", false);
        task.setTaskTitle("ubc");
        task.setDeadline(20201225);
        task.setTaskContent("computer");
        task.setIfMarkDone(true);

        assertEquals(20201225,task.getDeadline());
        assertEquals("ubc",task.getTaskTitle());
        assertEquals("computer",task.getTaskContent());
        assertTrue(task.getIfMarkDone());
    }

}
