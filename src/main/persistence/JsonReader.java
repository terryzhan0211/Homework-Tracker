package persistence;

import model.Task;
import model.TaskManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// cited from the sample of JsonSerializationDemo
// Represents a reader that reads TaskManager from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads TaskManager from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TaskManager read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTaskManager(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses TaskManager from JSON object and returns it
    private TaskManager parseTaskManager(JSONObject jsonObject) {

        TaskManager tm = new TaskManager();
        addTasks(tm, jsonObject);
        return tm;
    }

    // MODIFIES: tm
    // EFFECTS: parses Tasks from JSON object and adds them to TaskManager
    private void addTasks(TaskManager tm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("todolist");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addTask(tm, nextThingy);
        }

    }

    // MODIFIES: tm
    // EFFECTS: parses Task from JSON object and adds it to TaskManager
    private void addTask(TaskManager tm, JSONObject jsonObject) {
        String taskTitle = jsonObject.getString("Title");
        int deadline = jsonObject.getInt("deadline");
        String taskContent = jsonObject.getString("content");
        Boolean ifMarkDone = jsonObject.getBoolean("status");

        Task task = new Task(deadline,taskTitle,taskContent,
                ifMarkDone);
        tm.getTodoList().add(task);
    }
}
