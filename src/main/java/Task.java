public class Task {
    protected String taskDescription;
    protected boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
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
}
