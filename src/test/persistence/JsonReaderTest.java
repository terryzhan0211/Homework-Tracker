package persistence;

import model.Task;
import model.TaskManager;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// cited from JsonSerializationDemo
public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            TaskManager taskManager = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTaskManager() {
        JsonReader reader = new JsonReader("./data/testEmptyTodolist.json");
        try {
            TaskManager taskManager = reader.read();
            assertEquals(0, taskManager.getTodoList().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    @Test
    void testReaderGeneralTaskManager() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTaskManager.json");
        try {
            TaskManager taskManager = reader.read();
            ArrayList<Task> todoList= taskManager.getTodoList();
            assertEquals("aaa", todoList.get(0).getTaskTitle());
            assertEquals("bb", todoList.get(1).getTaskTitle());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
