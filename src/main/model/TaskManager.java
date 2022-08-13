package model;

import exception.TaskTitleInvalidException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import java.util.ArrayList;

// represents a list of tasks that contains completed or uncompleted tasks
public class TaskManager implements Writable {

    ArrayList<Task> todoList; // build a list for list of Tasks


    /*  REQUIRES:
     *   EFFECTS:build an empty list for list of tasks
     */
    public TaskManager() {
        todoList = new ArrayList<>();

    }

    //getter
    public ArrayList<Task> getTodoList() {
        return todoList;
    }



    //REQUIRE:
    //MODIFIES:this
    //EFFECTS:if task title is formed by "   ", throw TaskTitleInvalidException
    //        otherwise add the new task to the list of tasks
    public void addTasksToTodolist(Task task) throws TaskTitleInvalidException {
        if (task.getTaskTitle().trim().isEmpty()) {
            throw new TaskTitleInvalidException();
        } else {
            todoList.add(task);

        }

    }

    //REQUIRE:
    //MODIFIES: this,t.ifMarkDone
    //EFFECTS: find if there is a task in the list of tasks that contains the given title
    //         and change the complete status, then return true; otherwise return false
    public boolean modifyTasks(String title) {
        for (Task t: this.todoList) {
            if (t.getTaskTitle().equals(title)) {
                t.setIfMarkDone(!t.getIfMarkDone());
                return true;
            }
        }
        return false;
    }


    //REQUIRE:
    //MODIFIES:this
    //EFFECTS: go through the given list of tasks, if there is a task that is uncompleted,
    //         then add that task to a new list of uncompleted tasks, return that list of
    //         uncompleted tasks.
    public ArrayList<Task> queryUncompletedTask() {
        ArrayList<Task> uncompletedTask = new ArrayList<>();
        for (Task t: this.todoList) {
            if (t.getIfMarkDone().equals(false)) {
                uncompletedTask.add(t);
            }
        }
        return uncompletedTask;
    }


    //REQUIRE:
    //MODIFIES:this
    //EFFECTS:go through the given list of tasks, if there is a task that is completed,
    //         then add that task to a new list of completed tasks, return that list of
    //         completed tasks.
    public ArrayList<Task> queryCompletedTask() {
        ArrayList<Task> completedTask = new ArrayList<>();
        for (Task t: this.todoList) {
            if (t.getIfMarkDone().equals(true)) {
                completedTask.add(t);
            }
        }
        return completedTask;
    }


    //REQUIRE:
    //MODIFIES:this
    //EFFECTS: if there is a task in the selected list contains a given title,
    //         remove that task from the selected list and return true,
    //         otherwise return false
    public boolean removeTasksFromTodolist(String title) {
        for (Task t : todoList) {
            if (t.getTaskTitle().equals(title)) {
                todoList.remove(t);
                return true;
            }

        }
        return false;
    }

    // cited from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("todolist", todolistToJson());
        return json;
    }

    // EFFECTS: returns things in this TaskManager as a JSON array
    private JSONArray todolistToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Task t : todoList) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }


}