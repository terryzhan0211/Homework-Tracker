package persistence;

import model.Task;
import model.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// cited from JsonSerializationDemo
public class JasonWriterTest {

    private Task task1;
    private Task task2;
    private Task task3;
    private Task task4;
    private Task task5;

    @BeforeEach
    void setup() {
        task1 = new Task(20201010,"cpsc1","good1", false);
        task2 = new Task(20201011,"cpsc2","good2",false);
        task3 = new Task(20201012,"cpsc3","good3",false);
        task4 = new Task(20201013,"cpsc4","good4",true);
        task5 = new Task(20201014,"cpsc5","good5",true);
    }

    @Test
    void testWriterInvalidFile() {
        try {

            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTaskManager() {
        try {
            TaskManager taskManager = new TaskManager();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTaskManager.json");
            writer.open();
            writer.write(taskManager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTaskManager.json");
            taskManager = reader.read();

            assertEquals(0, taskManager.getTodoList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            TaskManager taskManager = new TaskManager();
            taskManager.getTodoList().add(task1);
            taskManager.getTodoList().add(task2);
            taskManager.getTodoList().add(task3);
            taskManager.getTodoList().add(task4);
            taskManager.getTodoList().add(task5);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTaskManager.json");
            writer.open();
            writer.write(taskManager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTaskManager.json");
            taskManager = reader.read();
            assertEquals(5,taskManager.getTodoList().size());
            assertEquals(3,taskManager.queryUncompletedTask().size());
            assertEquals(2,taskManager.queryCompletedTask().size());
            ArrayList<Task> test = taskManager.getTodoList();
            assertEquals(5, test.size());
            assertEquals("cpsc1",test.get(0).getTaskTitle());
            assertEquals("cpsc2",test.get(1).getTaskTitle());
            assertEquals("cpsc3",test.get(2).getTaskTitle());
            assertEquals("cpsc4",test.get(3).getTaskTitle());
            assertEquals("cpsc5",test.get(4).getTaskTitle());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
