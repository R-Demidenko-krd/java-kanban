package task;

public class Subtask extends Task {

    protected int epicId;

    public Subtask(int id, String name, String description, Status status, int epicId) {
        super(id, name, description);
        this.epicId = epicId;
    }

    public Subtask(String name, String description, Status status, int epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }
}
