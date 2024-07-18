package task;

import java.util.ArrayList;

public class Epic extends Task {

    protected ArrayList<Integer> subtaskIds = new ArrayList<>();

    public Epic(int id, String name, String description, Status status) {
        super(id, name, description);
    }

    public Epic(String name, String description, Status status) {
        super(name, description);
    }

    @Override
    public boolean isEpic() {
        return true;
    }

    public void addSubtaskId(int id) {
        subtaskIds.add(id);
    }

    public ArrayList<Integer> getSubtaskIds() {
        return subtaskIds;
    }

    public void cleanSubtaskIds() {
        subtaskIds.clear();
    }

    public void removeSubtaskIds(Integer subId){
        subtaskIds.remove(subId);
    }


}
