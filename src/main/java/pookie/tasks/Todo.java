package pookie.tasks;

public class Todo extends Task{

    public Todo(String description) {
        super(description, "T");
    }

    public String getTaskInListFormat() {
        return super.getTaskInListFormat();
    }
}
