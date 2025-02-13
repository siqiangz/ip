package pookie.tasks;

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

    public String getTaskType() {
        return type;
    }

    public void setIsDone(boolean state) {
        this.isDone = state;
    }

    public String getTaskInListFormat() {
        return "[" + type + "]" +
                "[" + this.getStatusIcon() + "] " +
                taskDescription;
    }
}
