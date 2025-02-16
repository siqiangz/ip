package pookie.tasks;

import pookie.errors.ErrorHandler;
import pookie.errors.InvalidNewTaskException;
import pookie.Pookie;
import java.util.ArrayList;

public class TaskManager {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static int getTaskListSize() {
        return taskList.size();
    }

    public static void markTask(String[] wordArray, String lineInput, String mark) {
        try {
            Task task = getTaskForMarking(wordArray);
            if (mark.equals("mark")) {
                setTaskAsDone(task);
            } else {
                setTaskAsUndone(task);
            }
            Pookie.doLineBreak();
        } catch (NumberFormatException e){
            // Non numbers after "mark"
            ErrorHandler.printInvalidMarkIndex();
        } catch (ArrayIndexOutOfBoundsException e) {
            // Given index out of bounds of wordArray
            // Given index out of bounds of taskList
            ErrorHandler.printOutOfBoundsMarkIndex(e);
        } finally {
            Pookie.doLineBreak();
        }
    }

    private static void setTaskAsUndone(Task task) {
        task.setIsDone(false);
        System.out.println("\tOK, I've marked this task as not done:");
        System.out.println("\t\t[" + task.getStatusIcon() + "] " + task.getTaskDescription());
    }

    private static void setTaskAsDone(Task task) {
        task.setIsDone(true);
        System.out.println("\tGreat! I've marked this task as done:");
        System.out.println("\t\t[" + task.getStatusIcon() + "] " + task.getTaskDescription());
    }

    private static Task getTaskForMarking(String[] wordArray) {
        // No index specified
        if (wordArray.length != 2) {
            throw new ArrayIndexOutOfBoundsException("Please specify task index.");
        }
        // Throws error if parsing non int type
        int taskListIndex = Integer.parseInt(wordArray[1]);
        // Index out of bounds
        if (taskListIndex < 1 || taskListIndex > taskList.size()) {
            throw new ArrayIndexOutOfBoundsException("Index provided is out of bounds.");
        }
        return taskList.get(taskListIndex - 1);
    }

    public static void addNewTask(String[] wordArray) {
        if (isValidNewTask(wordArray)) {
            String taskName = addTaskToList(wordArray);
            printAddedTask(taskName);
        }
        Pookie.doLineBreak();
    }

    private static boolean isValidNewTask(String[] wordArray) {
        // No task specified
        if (wordArray.length <= 1) {
            ErrorHandler.printNoNewTaskNameSpecified(wordArray[0]);
            Pookie.doLineBreak();
            return false;
        }
        // For command deadline
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
        // For command event
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

    private static String addTaskToList(String[] wordArray) {
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
            return deadline.getTaskDescription() + " by " + deadline.getBy();
        // For case "event":
        default:
            int indexOfFrom = getIndexOfTimeline(wordArray, "from");
            int indexOfTo = getIndexOfTimeline(wordArray, "to");
            Event event = new Event(combineString(wordArray, 1, indexOfFrom),
                    combineString(wordArray, indexOfFrom + 1, indexOfTo),
                    combineString(wordArray, indexOfTo + 1, wordArray.length));
            taskList.add(event);
            return event.getTaskDescription() + " from " + event.getFrom() + " to " + event.getTo();
        }
    }

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

    // Returns zero-based indexing
    public static int getIndexOfTimeline(String[] wordArray, String targetWord) {
        int index = 0;
        while (!wordArray[index].equals("/" + targetWord)) {
            index++;
        }
        return index;
    }

    public static void printList() {
        if (taskList.isEmpty()) {
            doEmptyListMessage();
        } else {
            System.out.println("\tHere are all your tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + taskList.get(i).getTaskInListFormat());
            }
            System.out.println("\t\t[" + taskList.size() + " tasks added]");
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
