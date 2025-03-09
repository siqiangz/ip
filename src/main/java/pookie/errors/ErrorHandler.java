package pookie.errors;

import static pookie.customs.ColorAndStyles.BLUE;
import static pookie.customs.ColorAndStyles.CYAN;
import static pookie.customs.ColorAndStyles.GREEN;
import static pookie.customs.ColorAndStyles.RED;
import static pookie.customs.ColorAndStyles.RESET;

import pookie.tasks.TaskManager;

/**
 * ErrorHandler class manages unsuccessful processes of the task tracker.
 */
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

    /**
     * Prints a description of error and follow-up actions.
     * @param errorDescription String message from unsuccessful mark and unmark process
     */
    public static void printInvalidMarkEntry(String errorDescription) {
        System.out.println(INVALID_COMMAND_FORMAT);
        System.out.println(RED + "\t" + errorDescription + RESET);
        System.out.println(CORRECT_MARK_UNMARK_FORMAT);
        TaskManager.printList();
        System.out.println(TRY_AGAIN);
    }

    /**
     * Prints a description of error when trying to add new task without a name.
     * Prints follow-up actions depending on the type of new task.
     * @param taskType String of the type of new task for adding task process
     */
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

    /**
     * Prints a description of error when trying to add new deadline and event
     * without proper format.
     * Prints follow-up actions depending on type of new task being added.
     * @param errorDescription String message from unsuccessful deadline and event process
     * @param taskType String type of new task being added
     */
    public static void printInvalidNewTaskEntry(String errorDescription, String taskType) {
        System.out.println(RED + "\t" + errorDescription + RESET);
        if (taskType.equals("deadline")) {
            System.out.println(CORRECT_DEADLINE_FORMAT);
        } else {
            System.out.println(CORRECT_EVENT_FORMAT);
        }
    }

    /**
     * Prints description of error when trying to delete a task with improper indexing.
     * Prints follow-up actions of proper use of delete command.
     * @param errorDescription String message from unsuccessful delete process
     */
    public static void printInvalidDeleteEntry(String errorDescription) {
        System.out.println(INVALID_COMMAND_FORMAT);
        System.out.println(RED + "\t" + errorDescription + RESET);
        System.out.println(CORRECT_DELETE_FORMAT);
        TaskManager.printList();
        System.out.println(TRY_AGAIN);
    }

    /**
     * Prints description of error when trying to access save file at startup.
     * Prints possible causes for error.
     * @param message String message from unsuccessful access of save file at startup
     */
    public static void printCannotAccessSaveFileStartup(String message) {
        System.out.println(message);
        System.out.println(RED + "\tTrouble creating save file due to security level! X.X" + RESET);
        System.out.println(RED + "\tOr in the wrong working directory!" + RESET);
        System.out.println(ENSURE_CORRECT_DIRECTORY);
    }

    /**
     * Prints description of error when trying to access save file at close.
     * Prints possible causes for error.
     * @param message String message from unsuccessful access of save file on close
     */
    public static void printCannotAccessSaveFileWrite(String message) {
        System.out.println(message);
        System.out.println(RED + "\tTrouble getting save file!" + RESET);
        System.out.println(ENSURE_CORRECT_DIRECTORY);
    }

    /**
     * Prints description of error when user inputs improper time format in commands.
     * Prints follow-up actions for proper time formatting.
     * @param message String message from improper time format
     */
    public static void printInvalidDateTimeFormat(String message) {
        System.out.println(INVALID_COMMAND_FORMAT);
        System.out.println(RED + "\t" + message + RESET);
        System.out.println(CORRECT_DATE_TIME_FORMAT);
        System.out.println(TRY_AGAIN);
    }
}
