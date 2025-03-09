package pookie.tasks;

import pookie.errors.ErrorHandler;
import pookie.errors.InvalidDeleteTaskException;

/**
 * DeleteTaskChecker class checks whether delete commands are valid.
 */
public class DeleteTaskChecker {

    /**
     * Checks whether formatting of delete command is correct.
     * Checks whether there is sufficient information for delete command.
     * @param wordArray String[] contains full user input by word
     * @return true if command format is proper, false otherwise
     */
    public static boolean isValidDeleteTask(String[] wordArray) {
        try {
            checkBadIndexDeleteEntry(wordArray);
            checkNonNumberDeleteEntry(wordArray);
            checkOutOfBoundsDeleteEntry(wordArray);
        } catch (InvalidDeleteTaskException e) {
            ErrorHandler.printInvalidDeleteEntry(e.getMessage());
            return false;
        }
        return true;
    }

    private static void checkBadIndexDeleteEntry(String[] wordArray) throws InvalidDeleteTaskException {
        if (wordArray.length != 2) {
            throw new InvalidDeleteTaskException("Delete command has no/too many parameters! >.<\"");
        }
    }

    private static void checkNonNumberDeleteEntry(String[] wordArray) throws InvalidDeleteTaskException {
        try {
            Integer.parseInt(wordArray[1]);
        } catch (NumberFormatException e) {
            throw new InvalidDeleteTaskException("Delete command needs a number index! >.<\"");
        }
    }

    private static void checkOutOfBoundsDeleteEntry(String[] wordArray) throws InvalidDeleteTaskException {
        int deleteIndex = Integer.parseInt(wordArray[1]);
        if (deleteIndex < 1 || deleteIndex > TaskManager.getTaskListSize()) {
            throw new InvalidDeleteTaskException("Delete command is out of bounds! >.<\"");
        }
    }
}
