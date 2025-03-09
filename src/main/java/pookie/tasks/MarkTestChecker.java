package pookie.tasks;

import pookie.errors.ErrorHandler;
import pookie.errors.InvalidMarkTaskException;

/**
 * MarkTestChecker class checks whether commands to mark tasks are valid.
 */
public class MarkTestChecker {

    /**
     * Checks whether formatting of mark, unmark command is proper.
     * Checks whether there is sufficient information for mark, unmark command.
     * @param wordArray String[] contains full user input by word
     * @return true if command format is proper, false otherwise
     */
    public static boolean isValidMarkTest(String[] wordArray) {
        try {
            checkBadIndexMarkEntry(wordArray);
            checkNonNumberMarkEntry(wordArray);
            checkOutOfBoundsMarkEntry(wordArray);
        } catch (InvalidMarkTaskException e) {
            ErrorHandler.printInvalidMarkEntry(e.getMessage());
            return false;
        }
        return true;
    }

    private static void checkBadIndexMarkEntry(String[] wordArray) throws InvalidMarkTaskException {
        if (wordArray.length != 2) {
            throw new InvalidMarkTaskException("Mark/Unmark command has no/too many parameters! >.<\"");
        }
    }

    private static void checkNonNumberMarkEntry(String[] wordArray) throws InvalidMarkTaskException {
        try {
            Integer.parseInt(wordArray[1]);
        } catch (NumberFormatException e) {
            throw new InvalidMarkTaskException("Mark/Unmark command needs a number index! >.<\"");
        }
    }

    private static void checkOutOfBoundsMarkEntry(String[] wordArray) throws InvalidMarkTaskException {
        int markIndex = Integer.parseInt(wordArray[1]);
        if (markIndex < 1 || markIndex > TaskManager.getTaskListSize()) {
            throw new InvalidMarkTaskException("Mark/Unmark command is out of bounds! >.<\"");
        }
    }
}
