package ui;


import exception.TaskTitleInvalidException;
import model.Task;
import model.TaskManager;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WelcomePage extends JFrame {

    private static final String JSON_STORE = "./data/todoList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // buttons
    JButton addTask = new JButton("Add Task");
    JButton deleteTask = new JButton("Delete Task");
    JButton viewTodoList = new JButton("View Todolist");
    JButton changeStatus = new JButton("Change Status");
    JButton viewCompletedTask = new JButton("Number of CompletedTask");
    JButton viewUncompletedTask = new JButton("Number of UncompletedTask");
    JButton save = new JButton("Save");
    JButton load = new JButton("Load");


    JTextField titleBox = new JTextField();
    JTextField contentBox = new JTextField();
    JTextField deadlineBox = new JTextField();

    JLabel titleExplaining = new JLabel();
    JLabel contentExplaining = new JLabel();
    JLabel deadlineExplaining = new JLabel();
    JLabel printOutForCommands = new JLabel();

    JTextArea textArea = new JTextArea();
    JScrollPane viewScrollPanel = new JScrollPane(textArea);//ScrollPane
    ImageIcon image = new ImageIcon("./data/3177321-job.png");
    JLabel imageLabel = new JLabel(image);

    TaskManager taskManager; // build a list for list of Tasks


    //EFFECTS: constructs a welcome page with frame
    public WelcomePage() {

        this.initialize();
        this.setLocationAndAddButtons();//add button


        this.addSaveAndLoadButton();
        this.setTextBox();
        this.createTextarea();
        this.createImageLabel();
        this.setVisible(true);
    }

    //    REQUIRES:
    //    MODIFIES: this
    //    EFFECTS: initialize all elements in the frame

    public void initialize() {
        this.setTitle("Welcome page");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//exit out of application
        this.setSize(800, 1000);
        this.setResizable(true);//prevent from being resized
        this.setLayout(null);
        this.setExplainingTitle();
        this.addAction();
        this.deleteAction();
        this.changeStatusAction();
        this.saveTasksAction();
        this.loadTasksAction();
        this.viewTodolistAction();
        this.viewNumberOfUncompletedTasks();
        this.viewNumberOfCompletedTasks();

        taskManager = new TaskManager();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);




    }
//        REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: set the location and size for buttons and add buttons to frame

    public void setLocationAndAddButtons() {

        addTask.setBounds(600,400,150,50);

        deleteTask.setBounds(600,450,150,50);

        viewTodoList.setBounds(600,500,150,50);

        changeStatus.setBounds(600,550,150,50);

        viewCompletedTask.setBounds(20,400,200,50);

        viewUncompletedTask.setBounds(20,450,200,50);

        this.add(addTask);
        this.add(deleteTask);
        this.add(viewTodoList);
        this.add(changeStatus);
        this.add(viewCompletedTask);
        this.add(viewUncompletedTask);

    }
//        REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: set location and size for save and load button and add them to the frame

    public  void addSaveAndLoadButton() {

        save.setBounds(20,500,200,50);

        load.setBounds(20,550,200,50);
        this.add(save);
        this.add(load);

    }


//        REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: create and set input text box

    public void setTextBox() {
        titleBox.setLayout(null);
        titleBox.setVisible(true);
        titleBox.setBounds(300,650,150,30);
        this.getContentPane().add(titleBox);


        contentBox.setLayout(null);
        contentBox.setVisible(true);
        contentBox.setBounds(300,700,150,30);
        this.getContentPane().add(contentBox);


        deadlineBox.setLayout(null);
        deadlineBox.setVisible(true);
        deadlineBox.setBounds(300,750,150,30);
        this.getContentPane().add(deadlineBox);


    }
//        REQUIRES:
    //    MODIFIES:this
    //    EFFECTS:set and add the explaining text label

    public void setExplainingTitle() {
        titleExplaining.setLayout(null);
        titleExplaining.setText("title");
        titleExplaining.setBounds(250,650,50,30);
        titleExplaining.setOpaque(true);
        titleExplaining.setFont(new Font("MV Boli",Font.PLAIN,20));
        this.add(titleExplaining);

        contentExplaining.setLayout(null);
        contentExplaining.setText("content");
        contentExplaining.setBounds(220,700,80,30);
        contentExplaining.setOpaque(true);
        contentExplaining.setFont(new Font("MV Boli",Font.PLAIN,20));
        this.add(contentExplaining);

        deadlineExplaining.setLayout(null);
        deadlineExplaining.setText("deadline with numbers");
        deadlineExplaining.setBounds(80,750,220,30);
        deadlineExplaining.setOpaque(true);
        deadlineExplaining.setFont(new Font("MV Boli",Font.PLAIN,20));
        this.add(deadlineExplaining);

        this.createExplainingLabel();


    }

//        REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: set and add the label for presenting different information(add successfully...)

    public void createExplainingLabel() {
        printOutForCommands.setLayout(null);
        printOutForCommands.setBackground(Color.white);
        printOutForCommands.setBounds(100,800,500,50);
        printOutForCommands.setFont(new Font("MV Boli",Font.PLAIN,20));
        printOutForCommands.setOpaque(true);
        this.add(printOutForCommands);
    }

    //        REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: set and add the label for presenting a image

    public void createImageLabel() {
        imageLabel.setLayout(null);
        imageLabel.setBounds(250,400,300,200);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.white);
        Image resizedIcon = getScaledImage(image.getImage(),300,200);
        image = new ImageIcon(resizedIcon);
        imageLabel.setIcon(image);

        imageLabel.setVisible(false);
        this.add(imageLabel);

    }

    //    REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: resize the imported image to fit the imageLabel

    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    //    REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: add task to todolist

    public void addAction() {
        addTask.addActionListener(e -> {
            String taskTitleImported = titleBox.getText();
            String contentImported = contentBox.getText();
            int deadlineImported = Integer.valueOf(deadlineBox.getText()).intValue();
            Task taskImported = new Task(deadlineImported,taskTitleImported,contentImported);
            try {
                taskManager.addTasksToTodolist(taskImported);
                printOutForCommands.setText("Add successfully");
            } catch (TaskTitleInvalidException taskTitleInvalidException) {
                printOutForCommands.setText("invalid task title");
            }


            contentBox.setText("");
            deadlineBox.setText("");
            titleBox.setText("");

        });

    }

    //    REQUIRES:
    //    MODIFIES:this
    //    EFFECTS:delete given task from todolist

    public void deleteAction() {
        deleteTask.addActionListener(e -> {
            String taskTitleImported = titleBox.getText();

            if (taskManager.removeTasksFromTodolist(taskTitleImported)) {
                printOutForCommands.setText("delete successfully");
            } else {
                printOutForCommands.setText("no task found");
            }

            contentBox.setText("");
            deadlineBox.setText("");
            titleBox.setText("");
        });

    }

    //    REQUIRES:
    //    MODIFIES:this
    //    EFFECTS:change task finish status to true or false
    public void changeStatusAction() {
        changeStatus.addActionListener(e -> {
            String taskTitleImported = titleBox.getText();

            if (taskManager.modifyTasks(taskTitleImported)) {
                printOutForCommands.setText("change successfully");
            } else {
                printOutForCommands.setText("no task found");
            }

            contentBox.setText("");
            deadlineBox.setText("");
            titleBox.setText("");
        });

    }

    //    REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: save todolist to Jason file
    public void saveTasksAction() {
        save.addActionListener(e -> {

            try {
                jsonWriter.open();
                jsonWriter.write(taskManager);
                jsonWriter.close();

                for (Task task : taskManager.getTodoList()) {
                    printOutForCommands.setText("Saved " + task.getTaskTitle() + " to " + JSON_STORE);
                }
            } catch (FileNotFoundException er) {
                printOutForCommands.setText("Unable to write to file: " + JSON_STORE);
            }


        });

    }
    //    REQUIRES:
    //    MODIFIES:this
    //    EFFECTS:load todolist from Jason file

    public void loadTasksAction() {
        load.addActionListener(e -> {

            try {
                taskManager = jsonReader.read();
                for (Task task : taskManager.getTodoList()) {
                    printOutForCommands.setText("loaded " + task.getTaskTitle() + " from " + JSON_STORE);
                }
            } catch (IOException er) {
                printOutForCommands.setText("Unable to read from file: " + JSON_STORE);
            }

            for (Task t:taskManager.getTodoList()) {
                System.out.println(t.getDeadline() + t.getTaskTitle() + t.getTaskContent());
            }

        });

    }
    //    REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: create JTextarea for viewing

    public void createTextarea() {

        viewScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        viewScrollPanel.setBackground(Color.white);
        viewScrollPanel.setBounds(40,20,700,350);
        textArea.setFont(new Font("MV Boli",Font.PLAIN,30));

        textArea.setTabSize(4);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        this.add(viewScrollPanel);//add scrollPanel to frame
    }

    //    REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: view the todolist
    public void viewTodolistAction() {
        viewTodoList.addActionListener(e -> {

            if (taskManager.getTodoList().size() == 0) {
                textArea.setText("no task found");
                return;
            }
            StringBuilder temp = new StringBuilder();
            for (Task t:taskManager.getTodoList()) {
                temp.append(t.getDeadline()).append(t.getTaskTitle()).append(t.getTaskContent()).append("\r\n");
            }
            textArea.setText(temp.toString());

        });
    }

    //    REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: view the number of completedTask if the number of uncompleted task is greater than 0
    //             make the good job image invisible
    public void viewNumberOfCompletedTasks() {
        viewCompletedTask.addActionListener(e -> {
            textArea.setText(String.valueOf(taskManager.queryCompletedTask().size()));
            if (taskManager.queryUncompletedTask().size() > 0) {
                imageLabel.setVisible(false);

            }

        });
    }

    //    REQUIRES:
    //    MODIFIES:this
    //    EFFECTS: view the number of uncompleted if the number of uncompleted task is 0
    //             make the good job image visible, and make the image invisible otherwise
    public void viewNumberOfUncompletedTasks() {
        viewUncompletedTask.addActionListener(e -> {
            textArea.setText(String.valueOf(taskManager.queryUncompletedTask().size()));

            if (taskManager.queryUncompletedTask().size() == 0) {
                imageLabel.setVisible(true);
            } else {
                imageLabel.setVisible(false);
            }


        });
    }

}
