package pookie.errors;

import pookie.tasks.TaskManager;

public class ErrorHandler {

    public static final String CORRECT_DEADLINE_FORMAT = "\tCorrect format: deadline {task name} /by {time specified}";
    public static final String CORRECT_EVENT_FORMAT = "\tCorrect format: event {task name} /from {time specified} /to {time specified}";
    public static final String CORRECT_TODO_FORMAT = "\tCorrect format: todo {task name}";
    public static final String INVALID_COMMAND_FORMAT = "\tInvalid command format!";
    public static final String CORRECT_MARK_UNMARK_FORMAT = "\tCorrect format: (un)mark {index of task}";
    public static final String TRY_AGAIN = "\tTry again! ^_^";

    // For commands mark, unmark
    // Non numbers after command
    public static void printInvalidMarkIndex() {
        System.out.println(INVALID_COMMAND_FORMAT);
        System.out.println(CORRECT_MARK_UNMARK_FORMAT);
        TaskManager.printList();
        System.out.println(TRY_AGAIN);
    }

    // For commands mark, unmark
    // Given index out of bounds of wordArray
    // Given index out of bounds of taskList
    public static void printOutOfBoundsMarkIndex(ArrayIndexOutOfBoundsException e) {
        System.out.println(INVALID_COMMAND_FORMAT);
        System.out.println("\t" + e.getMessage());
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
        System.out.println("\t" + errorDescription);
        if (taskType.equals("deadline")) {
            System.out.println(CORRECT_DEADLINE_FORMAT);
        } else {
            System.out.println(CORRECT_EVENT_FORMAT);
        }
    }
}
