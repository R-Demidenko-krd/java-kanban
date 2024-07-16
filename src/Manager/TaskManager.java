package Manager;

import Task.*;

import java.util.*;

public class TaskManager {

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    private static int generatorId = 0;

    public int addNewTask(Task task) {
        task.setId(generatorId++);
        tasks.put(task.getId(), task);
        return task.getId();

    }

    public int addNewEpic(Epic epic) {
        epic.setId(generatorId++);
        epics.put(epic.getId(), epic);
        return epic.getId();

    }

    public Integer addNewSubtask(Subtask subtask) {
        subtask.setId(generatorId++);
        subtasks.put(subtask.getId(), subtask);
        Epic epic = epics.get(subtask.getEpicId());

        if (epic == null) {
            System.out.println("No such epic: " + subtask.getEpicId());
            return -1;
        }

        epic.addSubtaskId(subtask.getId());
        updateEpicStatus(epic);
        return subtask.getEpicId();

    }


    public ArrayList<Task> getAllTasks() {
        if (tasks == null) return null;
        return new ArrayList<Task>(tasks.values());
    }

    public ArrayList<Subtask> getAllSubtask() {
        if (subtasks == null) return null;
        return new ArrayList<Subtask>(subtasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        if (epics == null) return null;
        return new ArrayList<Epic>(epics.values());
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public ArrayList<Subtask> getEpicSubtask(int epicId) {
        Epic epic = getEpic(epicId);
        if (epic == null) {
            System.out.println("No such epicId: " + epicId);
            return null;
        }
        ArrayList<Subtask> allSubtask = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtaskIds()) {
            allSubtask.add(subtasks.get(subtaskId));
        }
        return allSubtask;
    }

    public void updateEpicStatus(Epic epic) {
        ArrayList<Subtask> allSubtask = new ArrayList<>();

        for (Integer subtaskId : epic.getSubtaskIds()) {
            allSubtask.add(subtasks.get(subtaskId));
        }
        if (allSubtask.isEmpty() || allSubtask.stream().allMatch(subtask -> subtask.getStatus() == Status.NEW))
            epic.setStatus(Status.NEW);

        else if (allSubtask.stream().allMatch(subtask -> subtask.getStatus() == Status.DONE))
            epic.setStatus(Status.DONE);

        else epic.setStatus(Status.IN_PROGRESS);

    }

    public void updateTask(Task task) {
        if (!tasks.containsKey(task.getId())) System.out.println("No such task: " + task.getName());
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        if (!epics.containsKey(epic.getId())) System.out.println("No such epic: " + epic.getName());
        epics.put(epic.getId(), epic);
        updateEpicStatus(epic);
    }

    public void updateSubtask(Subtask subtask) {
        if (!subtasks.containsKey(subtask.getId())) System.out.println("No such subtask: " + subtask.getName());
        subtasks.put(subtask.getId(), subtask);
        updateEpicStatus(getEpic(subtask.getEpicId()));
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteTaskById(int id) {
        tasks.remove(id);
    }

    public void deleteAllSubtasks() {
        subtasks.clear();
        for (Integer key : epics.keySet()) {
            Epic epic = epics.get(key);
            epic.cleanSubtaskIds();
            updateEpicStatus(epic);
        }
    }

    public void deleteSubtaskById(int id) {
        Subtask subtask = subtasks.get(id);

        if (subtask != null) {
            subtasks.remove(id);
            Epic epic = epics.get(subtask.getEpicId());
            epic.removeSubtaskIds(subtask.getId());
            updateEpicStatus(epic);
        }
    }

    public void deleteAllEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void deleteEpicById(int id) {
        Epic epic = epics.get(id);

        if (epic != null) {
            for (Integer subtaskId : epic.getSubtaskIds()) {
                subtasks.remove(subtaskId);
            }
            epics.remove(id);
        }
    }

}
