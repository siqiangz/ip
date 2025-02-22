package pookie.tasks;

public class Event extends Task{
    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, "E");
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getTaskInListFormat() {
        return super.getTaskInListFormat() +
                " (from: " + from + " || " +
                "to: " + to + ")";
    }

    // return {/done or /undone} event {description} /from {time} /to {time}
    public String getTaskInSaveFormat() {
        return this.getIsDoneInSaveFormat() + " event " + this.getTaskDescription() + " /from " + this.getFrom() +
                " /to " + this.getTo();
    }
}
