package pookie.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pookie.errors.InvalidDateTimeException;

/**
 * Deadline class is an extension of Task class.
 * Contains formatting methods for printing and saving deadline tasks.
 */
public class Deadline extends Task{
    public static final String LIST_FORMAT = "list format";
    private static final String SAVE_FORMAT = "save format";
    private LocalDateTime dateTime;

    public Deadline(String description, String by) throws InvalidDateTimeException {
        super(description, "D");
        makeLocalDateTime(by);
    }

    private void makeLocalDateTime(String by) throws InvalidDateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            dateTime = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Bad date and time format, or invalid date and time numbers! >.<");
        }
    }

    public String getBy(String printFormat) {
        if (printFormat.equals(LIST_FORMAT)) {
            return dateTime.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy hh:mm a"));
        }
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Returns the print format of deadline task for printing to command line.
     * @return format of deadline save for printing
     */
    public String getTaskInListFormat() {
        return super.getTaskInListFormat() + " (by: " + this.getBy(LIST_FORMAT) + ")";
    }

    /**
     * Returns the save format of deadline task for writing save file.
     * @return format of a deadline save in save file
     */
    public String getTaskInSaveFormat() {
        return this.getIsDoneInSaveFormat() + " deadline " + this.getTaskDescription() + " /by " + this.getBy(SAVE_FORMAT);
    }
}
