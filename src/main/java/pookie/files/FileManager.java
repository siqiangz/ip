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

/**
 * FileManager saves the state of tasks being tracked into a save file.
 * Reads from a save file and repopulates the tracked tasks when Pookie runs again.
 */
public class FileManager {

    /**
     * Repopulates tracked tasks for Pookie in subsequent runs.
     * Reads from a save file and adds each task, maintaining mark status from previous Pookie run.
     */
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

    private static void importTasksToTaskList(String taskString) {
        taskString = taskString.trim();
        String[] wordArrayWithMark = taskString.split(" ");
        String[] wordArray = Arrays.copyOfRange(wordArrayWithMark, 1, wordArrayWithMark.length);
        boolean isDone = (wordArrayWithMark[0].equals("/done"));

        TaskManager.addNewTask(wordArray, isDone);
    }

    /**
     * Writes save file when Pookie is closed.
     * Creates a save file if one does not exist, otherwise rewrites the save file.
     */
    public static void updateSaveFileOnExit() {
        ArrayList<Task> taskArrayList = TaskManager.getTaskList();

        try {
            FileWriter writer = new FileWriter("./tasks.txt");

            for (Task task : taskArrayList) {
                writer.write(task.getTaskInSaveFormat() + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            ErrorHandler.printCannotAccessSaveFileWrite(e.getMessage());
        }
    }
}
