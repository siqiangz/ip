package pookie.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pookie.errors.InvalidDateTimeException;

/**
 * Event class is an extension of Task class.
 * Contains formatting methods for printing and saving event tasks.
 */
public class Event extends Task{
    public static final String LIST_FORMAT = "list format";
    public static final String SAVE_FORMAT = "save format";
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public Event(String description, String from, String to) throws InvalidDateTimeException {
        super(description, "E");
        makeLocalDateTime(from, to);
    }

    private void makeLocalDateTime(String from, String to) throws InvalidDateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            fromDateTime = LocalDateTime.parse(from, formatter);
            toDateTime = LocalDateTime.parse(to, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("Bad date and time format, or invalid date and time numbers! >.<");
        }
    }

    public String getFrom(String printFormat) {
        if (printFormat.equals(LIST_FORMAT)) {
            return fromDateTime.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy hh:mm a"));
        }
        return fromDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    public String getTo(String printFormat) {
        if (printFormat.equals(LIST_FORMAT)) {
            return toDateTime.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy hh:mm a"));
        }
        return toDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Returns the print format of event task for printing to command line.
     * @return format of event save for printing
     */
    public String getTaskInListFormat() {
        return super.getTaskInListFormat() +
                " (from: " + this.getFrom(LIST_FORMAT) + " || " +
                "to: " + this.getTo(LIST_FORMAT) + ")";
    }

    /**
     * Returns the save format of event task for writing save file.
     * @return format of an event save in save file
     */
    public String getTaskInSaveFormat() {
        return this.getIsDoneInSaveFormat() + " event " + this.getTaskDescription() + " /from " + this.getFrom(SAVE_FORMAT) +
                " /to " + this.getTo(SAVE_FORMAT);
    }
}
