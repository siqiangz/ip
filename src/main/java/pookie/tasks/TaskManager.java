package pookie.tasks;

import pookie.errors.ErrorHandler;
import pookie.errors.InvalidDateTimeException;
import pookie.errors.InvalidNewTaskException;
import pookie.Pookie;

import java.util.ArrayList;
import java.util.Arrays;

import static pookie.customs.ColorAndStyles.BLUE;
import static pookie.customs.ColorAndStyles.RED;
import static pookie.customs.ColorAndStyles.GREEN;
import static pookie.customs.ColorAndStyles.RESET;
import static pookie.customs.ColorAndStyles.YELLOW;
import static pookie.tasks.Deadline.LIST_FORMAT;

/**
 * TaskManager class handles all commands that accesses or manipulates tasks.
 * Handles add, delete, mark, unmark commands.
 */
public class TaskManager {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static int getTaskListSize() {
        return taskList.size();
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Creates a String array of keywords for TaskFinder to use.
     * @param wordArray String[] contains full user input by word
     */
    public static void findTask(String[] wordArray) {
        String[] wordsToFind = Arrays.copyOfRange(wordArray, 1, wordArray.length);
        TaskFinder.findTasksWithKeywords(wordsToFind);
    }

    /**
     * Sets mark status of tasks.
     * @param wordArray String[] contains full user input by word
     */
    public static void markTask(String[] wordArray) {
        if (MarkTestChecker.isValidMarkTest(wordArray)) {
            if (wordArray[0].equals("mark")) {
                taskList.get(Integer.parseInt(wordArray[1]) - 1).setIsDone(true);
                printMarkedTask(taskList.get(Integer.parseInt(wordArray[1]) - 1), GREEN + "DONE" + RESET);
            } else {
                taskList.get(Integer.parseInt(wordArray[1]) - 1).setIsDone(false);
                printMarkedTask(taskList.get(Integer.parseInt(wordArray[1]) - 1), RED + "NOT DONE" + RESET);
            }
        }
        Pookie.doLineBreak();
    }

    private static void markTask(boolean isDone) {
        taskList.get(taskList.size() - 1).setIsDone(isDone);
    }

    private static void printMarkedTask(Task markedTask, String markStatus) {
        System.out.println("\tOK, I've marked this task as " + markStatus + " :");
        System.out.println("\t\t[" + markedTask.getStatusIcon() + "] " + markedTask.getTaskDescription());
    }

    /**
     * Handles live user request to add a new task to track.
     * @param wordArray String[] contains full user input by word
     */
    public static void addNewTask(String[] wordArray) {
        if (isValidNewTask(wordArray)) {
            try {
                String taskName = addTaskToList(wordArray);
                printAddedTask(taskName);
            } catch (InvalidDateTimeException e) {
                ErrorHandler.printInvalidDateTimeFormat(e.getMessage());
            }
        }
        Pookie.doLineBreak();
    }

    /**
     * Handles FileManager class request to add task from save file.
     * Sets the mark status of tasks from save file.
     * @param wordArray String[] contains full user input by word
     * @param isDone boolean mark status of task
     */
    public static void addNewTask(String[] wordArray, boolean isDone) {
        try {
            addTaskToList(wordArray);
            markTask(isDone);
        } catch (InvalidDateTimeException e) {
            ErrorHandler.printInvalidDateTimeFormat(e.getMessage());
        }
    }

    private static boolean isValidNewTask(String[] wordArray) {
        if (wordArray.length <= 1) {
            ErrorHandler.printNoNewTaskNameSpecified(wordArray[0]);
            Pookie.doLineBreak();
            return false;
        }
        if (wordArray[0].equals("deadline")) {
            try {
                NewTaskChecker.checkDeadlineMissingKeyword(wordArray);
                NewTaskChecker.checkIncompleteDeadlineEntry(wordArray);

            } catch (InvalidNewTaskException e) {
                ErrorHandler.printInvalidNewTaskEntry(e.getMessage(), "deadline");
                Pookie.doLineBreak();
                return false;
            }
        }
        if (wordArray[0].equals("event")) {
            try {
                NewTaskChecker.checkEventMissingKeyword(wordArray);
                NewTaskChecker.checkIncompleteEventEntry(wordArray);
            } catch (InvalidNewTaskException e) {
                ErrorHandler.printInvalidNewTaskEntry(e.getMessage(), "event");
                Pookie.doLineBreak();
                return false;
            }
        }
        return true;
    }

    private static String addTaskToList(String[] wordArray) throws InvalidDateTimeException {
        switch (wordArray[0]) {
        case "todo":
            Todo todo = new Todo(combineString(wordArray, 1, wordArray.length));
            taskList.add(todo);
            return todo.getTaskDescription();
        case "deadline":
            int indexOfBy = getIndexOfTimeline(wordArray, "by");
            Deadline deadline = new Deadline(combineString(wordArray, 1, indexOfBy),
                    combineString(wordArray, indexOfBy + 1, wordArray.length));
            taskList.add(deadline);
            return deadline.getTaskDescription() + " by " + deadline.getBy(LIST_FORMAT);
        default:
            int indexOfFrom = getIndexOfTimeline(wordArray, "from");
            int indexOfTo = getIndexOfTimeline(wordArray, "to");
            Event event = new Event(combineString(wordArray, 1, indexOfFrom),
                    combineString(wordArray, indexOfFrom + 1, indexOfTo),
                    combineString(wordArray, indexOfTo + 1, wordArray.length));
            taskList.add(event);
            return event.getTaskDescription() + " from " + event.getFrom(LIST_FORMAT) + " to " + event.getTo(LIST_FORMAT);
        }
    }

    /**
     * Removes task from tracking.
     * @param wordArray String[] contains full user input by word
     */
    public static void deleteTask(String[] wordArray) {
        if (DeleteTaskChecker.isValidDeleteTask(wordArray)) {
            Task deletedTask = taskList.remove(Integer.parseInt(wordArray[1]) - 1);
            printDeletedTask(deletedTask);
        }
        Pookie.doLineBreak();
    }

    private static void printDeletedTask(Task deletedTask) {
        System.out.println("\tGotcha! I have deleted this task:");
        System.out.println("\t\t" + deletedTask.getTaskInListFormat());
        System.out.println("\tYou have " + taskList.size() + " tasks left ^o^");
    }

    /**
     * Finds the position of command keywords for setting time in wordArray.
     * @param wordArray String[] contains full user input by word
     * @param targetWord String word to find
     * @return index of targetWord in wordArray
     */
    public static int getIndexOfTimeline(String[] wordArray, String targetWord) {
        int index = 0;
        while (!wordArray[index].equals("/" + targetWord)) {
            index++;
        }
        return index;
    }

    /**
     * Prints all currently tracked tasks on user request.
     */
    public static void printList() {
        if (taskList.isEmpty()) {
            doEmptyListMessage();
        } else {
            System.out.println(YELLOW + "\tHere are all your tasks:" + RESET);
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("\t" + BLUE + (i + 1) + ". " + RESET + taskList.get(i).getTaskInListFormat());
            }
            System.out.println(YELLOW + "\t\t[" + taskList.size() + " tasks added]" + RESET);
        }
        Pookie.doLineBreak();
    }

    private static String combineString(String[] wordArray, int start, int end) {
        String sentence = " ";
        while (start < end) {
            sentence = sentence.concat(wordArray[start] + " ");
            start++;
        }
        return sentence = sentence.trim();
    }

    private static void printAddedTask(String taskName) {
        System.out.println("\tadded: " + taskName);
        System.out.println("\t\t[" + taskList.size() + " tasks added]");
    }

    private static void doEmptyListMessage() {
        System.out.println("\tYou do not have any tasks!");
        System.out.println("\tType something to add to the task list.");
    }
}
