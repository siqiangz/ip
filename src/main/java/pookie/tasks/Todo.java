package pookie.tasks;

public class Todo extends Task{

    public Todo(String description) {
        super(description, "T");
    }

    public String getTaskInListFormat() {
        return super.getTaskInListFormat();
    }

    // return {/done or /undone} todo {description}
    public String getTaskInSaveFormat() {
        return this.getIsDoneInSaveFormat() + " todo " + this.getTaskDescription();
    }
}
