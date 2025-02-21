package pookie.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import pookie.errors.ErrorHandler;
import pookie.tasks.TaskManager;

public class FileManager {

    public static void getSaveFileOnStartup() {
        File saveFile = new File("./savedata/tasks.txt");
        if (!saveFile.exists()) {
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                ErrorHandler.printCannotAccessSaveFile(e.getMessage());
            }
        }
        saveFile.setWritable(true);
        saveFile.setReadable(true);
        //  populate taskList
        // Print line saying loaded previous saves if any
        getTasksFromSaveFile(saveFile);

    }

    private static void getTasksFromSaveFile(File saveFile) {
        try {
            final Scanner fileReader = new Scanner(saveFile);
            while (fileReader.hasNextLine()) {
                importTasksToTaskList(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            ErrorHandler.printCannotAccessSaveFile(e.getMessage());
        }
    }

    // Break up taskString into wordArray
    // Format per line: event {description} /from {time} /to {time} {done/undone}
    // call overloaded TaskManager.addNewTask, adding task to taskList and marking done if needed
    private static void importTasksToTaskList(String taskString) {
        String[] wordArrayWithMark = taskString.split("\\s+");
        String[] wordArray = Arrays.copyOf(wordArrayWithMark, wordArrayWithMark.length - 1);
        boolean isDone = (wordArrayWithMark[wordArrayWithMark.length - 1].equals("/done"));

        TaskManager.addNewTask(wordArray, isDone);
    }

}
