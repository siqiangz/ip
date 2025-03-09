package pookie.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import pookie.errors.InvalidDateTimeException;
import pookie.errors.InvalidNewTaskException;

/**
 * NewTaskChecker class checks whether commands to add new tasks are valid.
 */
public class NewTaskChecker {
    /**
     * Checks if deadline command format is correct.
     * @param wordArray String[] contains full user input by word
     * @throws InvalidNewTaskException when /by keyword is missing from command
     */
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

    /**
     * Checks if deadline command has sufficient information.
     * @param wordArray String[] contains full user input by word
     * @throws InvalidNewTaskException when information is insufficient
     */
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

    /**
     * Checks if event command format is correct.
     * @param wordArray String[] contains full user input by word
     * @throws InvalidNewTaskException when /from and /to is missing from command
     */
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

        if (!isFromPresent && !isToPresent) {
            throw new InvalidNewTaskException("Event command is missing /from and /to keywords!");
        }
        if (!isFromPresent) {
            throw new InvalidNewTaskException("Event command is missing /from keyword!");
        }
        if (!isToPresent) {
            throw new InvalidNewTaskException("Event command is missing /to keyword!");
        }
    }

    /**
     * Checks if event command has sufficient information.
     * @param wordArray Checks if deadline command format is correct.
     * @throws InvalidNewTaskException when information is insufficient
     */
    public static void checkIncompleteEventEntry(String[] wordArray) throws InvalidNewTaskException {
        int indexOfFrom = TaskManager.getIndexOfTimeline(wordArray, "from");
        int indexOfTo = TaskManager.getIndexOfTimeline(wordArray, "to");
        boolean hasName = indexOfFrom >= 2;
        boolean hasTime = (indexOfTo - indexOfFrom) >= 2 && indexOfTo < wordArray.length - 1;

        if (!hasName && !hasTime) {
            throw new InvalidNewTaskException("Event has no name and set time!");
        }
        if (!hasName) {
            throw new InvalidNewTaskException("Event has no name!");
        }
        if (!hasTime) {
            throw new InvalidNewTaskException("Event has no proper time!");
        }
    }
}
