package model;

import exception.TaskTitleInvalidException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {


    TaskManager todolist;
    Task task_1;
    Task task_3;

    @BeforeEach
    void runBefore() {
        todolist = new TaskManager();
        task_1 = new Task(20201014,"cpsc","project",false);
        task_3 = new Task(20201014,"   ","project",false);
    }


    @Test
    void testAddTasksToTodolistWithInvalidTaskTitle() {
        try {
            todolist.addTasksToTodolist(task_3);
            fail("TaskTitleInvalidException expected but not thrown");
        } catch (TaskTitleInvalidException e) {
           //expected
        }
        assertEquals(0,todolist.getTodoList().size());
    }

    @Test
    void testAddTasksToTodolistSuccessfully() {
        try {
            todolist.addTasksToTodolist(task_1);
        } catch (TaskTitleInvalidException e) {
            fail("Exception should not have been throw");
        }
        assertEquals(1,todolist.getTodoList().size());
    }

    @Test
    void testModifyTasksWithOneUncompleted() {
        try {
            todolist.addTasksToTodolist(task_1);
        } catch (TaskTitleInvalidException e) {
            fail("Exception should not have been throw");
        }
        assertFalse(task_1.getIfMarkDone());
        assertTrue(todolist.modifyTasks("cpsc"));
        assertTrue(task_1.getIfMarkDone());

    }

    @Test
    void testModifyTasksWithOneCcompleted() {
        Task task_2 = new Task(20201020,"math","projectPhase",true);
        try {
            todolist.addTasksToTodolist(task_2);
        } catch (TaskTitleInvalidException e) {
            fail("Exception should not have been throw");
        }
        assertTrue(task_2.getIfMarkDone());
        assertTrue(todolist.modifyTasks("math"));
        assertFalse(task_2.getIfMarkDone());

    }

    @Test
    void testConstructor(){
        assertEquals(0,todolist.getTodoList().size());

    }

    @Test
    void testModifyTasksWithNoTaskFound() {
        Task task_2 = new Task(20201020,"math","projectPhase",false);
        try {
            todolist.addTasksToTodolist(task_2);
        } catch (TaskTitleInvalidException e) {
            fail("Exception should not have been throw");
        }
        assertFalse(todolist.modifyTasks("cpsc"));
        assertFalse(task_2.getIfMarkDone());

    }

    @Test
    void testQueryUncompletedTaskWithOneCompleted() {
        try {
            todolist.addTasksToTodolist(task_1);
        } catch (TaskTitleInvalidException e) {
            fail("Exception should not have been throw");
        }
        assertEquals(1,todolist.queryUncompletedTask().size());
    }

    @Test
    void testQueryUncompletedTaskWithNoUncompleted() {
        Task task_2 = new Task(20201020,"math","projectPhase",true);
        try {
            todolist.addTasksToTodolist(task_2);
        } catch (TaskTitleInvalidException e) {
            fail("Exception should not have been throw");
        }
        assertEquals(0,todolist.queryUncompletedTask().size());
    }


    @Test
    void testQueryCompletedTaskWithOneCompleted(){
        Task task_2 = new Task(20201020,"math","projectPhase",true);
        try {
            todolist.addTasksToTodolist(task_2);
        } catch (TaskTitleInvalidException e) {
            fail("Exception should not have been throw");
        }
        assertEquals(1,todolist.queryCompletedTask().size());
    }

    @Test
    void testQueryCompletedTaskWithNoCompleted(){

        try {
            todolist.addTasksToTodolist(task_1);
        } catch (TaskTitleInvalidException e) {
            fail("Exception should not have been throw");
        }
        assertEquals(0,todolist.queryCompletedTask().size());
    }

    @Test
    void testRemoveTasksFromTodolist() {
        try {
            todolist.addTasksToTodolist(task_1);
        } catch (TaskTitleInvalidException e) {
            fail("Exception should not have been throw");
        }
        assertTrue(todolist.removeTasksFromTodolist("cpsc"));
    }

    @Test
    void testRemoveTasksFromTodolistWithNoTaskFound() {
        try {
            todolist.addTasksToTodolist(task_1);
        } catch (TaskTitleInvalidException e) {
            fail("Exception should not have been throw");
        }
        assertFalse(todolist.removeTasksFromTodolist("math"));
    }



}