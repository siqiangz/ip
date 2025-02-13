package pookie.tasks;

import pookie.errors.InvalidNewTaskException;

public class NewTaskChecker {
    // Checks whether new deadline command is missing /by keyword
    public static void checkDeadlineMissingKeyword(String[] wordArray) throws InvalidNewTaskException {
        boolean isByPresent = false;
        for (String word : wordArray) {
            if (word.equals("/by")) {
                isByPresent = true;
                break;
            }
        }

        if (!isByPresent) {
            throw new InvalidNewTaskException("Deadline command is missing /by keyword!");
        }
    }

    // Checks whether new deadline command is missing names or times
    public static void checkIncompleteDeadlineEntry(String[] wordArray) throws InvalidNewTaskException {
        int indexOfBy = TaskManager.getIndexOfTimeline(wordArray, "by");

        // Check if deadline task name is present
        boolean isDeadlineNamePresent = indexOfBy >= 2;
        // Check if deadline by time is present
        boolean isDeadlineByTimePresent = indexOfBy < wordArray.length - 1;

        // No deadline name and by time
        if (!isDeadlineByTimePresent && !isDeadlineNamePresent) {
            throw new InvalidNewTaskException("Deadline has no name and by time!");
        }
        // No deadline by time
        if (!isDeadlineByTimePresent) {
            throw new InvalidNewTaskException("Deadline has no by time!");
        }
        // No deadline name
        if (!isDeadlineNamePresent) {
            throw new InvalidNewTaskException("Deadline has no name!");
        }
    }

    // Checks whether new event command is missing /from and /to keywords
    public static void checkEventMissingKeyword(String[] wordArray) throws InvalidNewTaskException{
        boolean isFromPresent = false;
        boolean isToPresent = false;
        for (String word : wordArray) {
            if (word.equals("/from")) {
                isFromPresent = true;
            } else if (word.equals("/to")) {
                isToPresent = true;
            }
            if (isFromPresent && isToPresent) {
                break;
            }
        }

        // Missing /from /to keywords in event command
        if (!isFromPresent && !isToPresent) {
            throw new InvalidNewTaskException("Event command is missing /from and /to keywords!");
        }
        // Missing /from keyword in event command
        if (!isFromPresent) {
            throw new InvalidNewTaskException("Event command is missing /from keyword!");
        }
        // Missing /to keyword in event command
        if (!isToPresent) {
            throw new InvalidNewTaskException("Event command is missing /to keyword!");
        }
    }

    // Checks whether new event command is missing names or times
    public static void checkIncompleteEventEntry(String[] wordArray) throws InvalidNewTaskException {
        int indexOfFrom = TaskManager.getIndexOfTimeline(wordArray, "from");
        int indexOfTo = TaskManager.getIndexOfTimeline(wordArray, "to");
        boolean hasName = indexOfFrom >= 2;
        boolean hasTime = (indexOfTo - indexOfFrom) >= 2 && indexOfTo < wordArray.length - 1;

        // Event command has no name and time
        if (!hasName && !hasTime) {
            throw new InvalidNewTaskException("Event has no name and set time!");
        }
        // Event command has no name
        if (!hasName) {
            throw new InvalidNewTaskException("Event has no name!");
        }
        // Event command has no time
        if (!hasTime) {
            throw new InvalidNewTaskException("Event has no proper time!");
        }
    }
}
