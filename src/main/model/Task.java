package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a task having deadline, title, content,
//                          and a boolean represents it if is mark done



public class Task implements Writable {
    private int deadline;             //task deadline
    private String taskContent;       //task content
    // private int taskID;            //task id, let program know when running
    private String taskTitle;         // task title like CPSC210 or something
    private Boolean ifMarkDone;       //true is completed, false is uncompleted


    /*
     * REQUIRES: Task has non-empty String input: title, content; int deadline, boolean doneOrNotDone
     * EFFECTS:  Task ddl is set to deadline;
     *           Task title is set to taskTitle;
     *           Task content is set to taskContent;
     *           Task doneOrNotDone is set to doneOrNotDone;
     */
    public Task(int ddl, String title, String content, Boolean doneOrNotDone) {

        this.deadline = ddl;
        this.taskTitle = title;
        this.taskContent = content;
        this.ifMarkDone = doneOrNotDone;
    }

    /*
     * REQUIRES: Task has  String input: title, content; int deadline, no boolean doneOrNotDone input
     * EFFECTS:  Task ddl is set to deadline;
     *           Task title is set to taskTitle;
     *           Task content is set to taskContent;
     *
     */
    public Task(int ddl, String title, String content) {

        this.deadline = ddl;
        this.taskTitle = title;
        this.taskContent = content;
        this.ifMarkDone = false;
    }


    //MODIFIES:this
    //EFFECTS:set boolean if mark done to the selected task
    public void setIfMarkDone(Boolean ifMarkDone) {
        this.ifMarkDone = ifMarkDone;
    }

    //MODIFIES:this
    //EFFECTS:set int deadline to the selected task

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    //MODIFIES:this
    //EFFECTS:set string task content to the selected task

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    //MODIFIES:this
    //EFFECTS:set string task title to the selected task

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }


    public int getDeadline() {
        return this.deadline;
    }

    public String getTaskTitle() {
        return this.taskTitle;
    }

    public String getTaskContent() {
        return this.taskContent;
    }

    public Boolean getIfMarkDone() {
        return this.ifMarkDone;
    }

    // cited from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Title", taskTitle);
        json.put("deadline", deadline);
        json.put("content", taskContent);
        json.put("status", ifMarkDone);
        return json;
    }
}
