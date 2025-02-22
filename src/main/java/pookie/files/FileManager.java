package pookie.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import pookie.errors.ErrorHandler;
import pookie.tasks.Task;
import pookie.tasks.TaskManager;

public class FileManager {

    public static void getSaveFileOnStartup() {
        File saveFile = new File("./tasks.txt");
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
                saveFile.setWritable(true);
                saveFile.setReadable(true);
            } catch (IOException e) {
                ErrorHandler.printCannotAccessSaveFileStartup(e.getMessage());
            }
        }
        getTasksFromSaveFile(saveFile);
    }

    private static void getTasksFromSaveFile(File saveFile) {
        try {
            final Scanner fileReader = new Scanner(saveFile);
            while (fileReader.hasNextLine()) {
                importTasksToTaskList(fileReader.nextLine());
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            ErrorHandler.printCannotAccessSaveFileStartup(e.getMessage());
        }
    }

    // Break up taskString into wordArray
    // Format per line: {done/undone} event {description} /from {time} /to {time}
    // call overloaded TaskManager.addNewTask, adding task to taskList and marking done if needed
    private static void importTasksToTaskList(String taskString) {
        taskString = taskString.trim();
        String[] wordArrayWithMark = taskString.split(" ");
        String[] wordArray = Arrays.copyOfRange(wordArrayWithMark, 1, wordArrayWithMark.length);
        boolean isDone = (wordArrayWithMark[0].equals("/done"));

        TaskManager.addNewTask(wordArray, isDone);
    }

    public static void updateSaveFileOnExit() {
        ArrayList<Task> taskArrayList = TaskManager.getTaskList();

        try {
            FileWriter writer = new FileWriter("./tasks.txt");
            File file = new File("./tasks.txt");

            for (Task task : taskArrayList) {
                writer.write(task.getTaskInSaveFormat() + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            ErrorHandler.printCannotAccessSaveFileWrite(e.getMessage());
        }
    }
}
