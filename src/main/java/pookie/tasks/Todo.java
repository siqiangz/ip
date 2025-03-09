package pookie.tasks;

/**
 * Todo class is an extension of Task class.
 * Contains formatting methods for printing and saving todo tasks.
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description, "T");
    }

    /**
     * Returns the print format of todo task for printing to command line.
     * @return format of todo save for printing
     */
    public String getTaskInListFormat() {
        return super.getTaskInListFormat();
    }

    /**
     * Returns the save format of todo task for writing save file.
     * @return format of a todo save in save file
     */
    public String getTaskInSaveFormat() {
        return this.getIsDoneInSaveFormat() + " todo " + this.getTaskDescription();
    }
}
