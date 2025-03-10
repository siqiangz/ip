package pookie.tasks;

/**
 * Task class is a superclass for todo, deadline and event tasks.
 * Contains
 */
public class Task {
    private String taskDescription;
    private boolean isDone;
    private String type;

    public Task(String taskDescription, String type) {
        this.taskDescription = taskDescription;
        this.isDone = false;
        this.type = type;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setIsDone(boolean state) {
        this.isDone = state;
    }

    public String getTaskInListFormat() {
        return "[" + type + "]" +
                "[" + this.getStatusIcon() + "] " +
                taskDescription;
    }

    protected String getIsDoneInSaveFormat() {
        return (this.getStatusIcon().equals("X")) ? "/done" : "/undone";
    }

    public String getTaskInSaveFormat() {
        return "";
    }
}
