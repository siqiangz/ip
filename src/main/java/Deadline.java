public class Deadline extends Task{
    private String by;

    public Deadline(String description, String by) {
        super(description, "D");
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String getTaskInListFormat() {
        return super.getTaskInListFormat() +
                " (by: " + by + ")";
    }
}
