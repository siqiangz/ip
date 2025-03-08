package pookie.errors;

import static pookie.customs.ColorAndStyles.BLUE;
import static pookie.customs.ColorAndStyles.CYAN;
import static pookie.customs.ColorAndStyles.GREEN;
import static pookie.customs.ColorAndStyles.RED;
import static pookie.customs.ColorAndStyles.RESET;

import pookie.tasks.TaskManager;

public class ErrorHandler {

    public static final String CORRECT_DEADLINE_FORMAT = BLUE + "\tCorrect format: deadline {task name} /by {time specified}" + RESET;
    public static final String CORRECT_EVENT_FORMAT = BLUE + "\tCorrect format: event {task name} /from {time specified} /to {time specified}" + RESET;
    public static final String CORRECT_TODO_FORMAT = BLUE + "\tCorrect format: todo {task name}" + RESET;
    public static final String INVALID_COMMAND_FORMAT = RED + "\tInvalid command format!" + RESET;
    public static final String CORRECT_MARK_UNMARK_FORMAT = BLUE + "\tCorrect format: mark/unmark {index of task}" + RESET;
    public static final String TRY_AGAIN = CYAN + "\tTry again! ^_^" + RESET;
    public static final String CORRECT_DELETE_FORMAT = BLUE + "\tCorrect format: delete {index of task}" + RESET;
    public static final String ENSURE_CORRECT_DIRECTORY = BLUE + "\tEnsure you are in project directory /CS2113iP" + RESET;
    public static final String CORRECT_DATE_TIME_FORMAT = BLUE + "\tWhen entering date and times, use \" {dd-mm-yyyy hh:mm} \"" + RESET;


    // For commands mark, unmark
    // Missing index, non-number index and out of bounds index
    public static void printInvalidMarkEntry(String errorDescription) {
        System.out.println(INVALID_COMMAND_FORMAT);
        System.out.println(RED + "\t" + errorDescription + RESET);
        System.out.println(CORRECT_MARK_UNMARK_FORMAT);
        TaskManager.printList();
        System.out.println(TRY_AGAIN);
    }

    // For commands todo, deadline, event
    // Missing new task name
    public static void printNoNewTaskNameSpecified(String taskType) {
        System.out.println("\tGive your task a name!");
        if (taskType.equals("todo")) {
            System.out.println(CORRECT_TODO_FORMAT);
            System.out.println("\tDo you have any todo tasks in mind? :)");
        } else if (taskType.equals("deadline")) {
            System.out.println(CORRECT_DEADLINE_FORMAT);
            System.out.println("\tGive me a deadline to track! :3");
        } else {
            System.out.println(CORRECT_EVENT_FORMAT);
            System.out.println("\tWhat events do you have in mind? ^_^");
        }
    }

    // For command deadline, event
    // Missing keywords, or parameters like name and time
    public static void printInvalidNewTaskEntry(String errorDescription, String taskType) {
        System.out.println(RED + "\t" + errorDescription + RESET);
        if (taskType.equals("deadline")) {
            System.out.println(CORRECT_DEADLINE_FORMAT);
        } else {
            System.out.println(CORRECT_EVENT_FORMAT);
        }
    }

    // For command delete
    // Missing index, non-int index and out of bounds index
    public static void printInvalidDeleteEntry(String errorDescription) {
        System.out.println(INVALID_COMMAND_FORMAT);
        System.out.println(RED + "\t" + errorDescription + RESET);
        System.out.println(CORRECT_DELETE_FORMAT);
        TaskManager.printList();
        System.out.println(TRY_AGAIN);
    }

    public static void printCannotAccessSaveFileStartup(String message) {
        System.out.println(message);
        System.out.println(RED + "\tTrouble creating save file due to security level! X.X" + RESET);
        System.out.println(RED + "\tOr in the wrong working directory!" + RESET);
        System.out.println(ENSURE_CORRECT_DIRECTORY);
    }

    public static void printCannotAccessSaveFileWrite(String message) {
        System.out.println(message);
        System.out.println(RED + "\tTrouble getting save file!" + RESET);
        System.out.println(ENSURE_CORRECT_DIRECTORY);
    }

    public static void printInvalidDateTimeFormat(String message) {
        System.out.println(INVALID_COMMAND_FORMAT);
        System.out.println(RED + "\t" + message + RESET);
        System.out.println(CORRECT_DATE_TIME_FORMAT);
        System.out.println(TRY_AGAIN);
    }
}
