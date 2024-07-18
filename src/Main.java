import manager.TaskManager;
import task.Task;

public class Main {

    public static void main(String[] args) {System.out.println("Поехали!");

        TaskManager manager = new TaskManager();

        Task task1 = new Task("Кофе", "Купить в магазине");
        int taskid = manager.addNewTask(task1);
        Task task2 = manager.getTask(taskid);
        System.out.println("task2 : " + task2 + "\n" +
               "taskid : " +  taskid);
        if (task1.equals(task2)) {
            System.out.println("task1.equals(task2) ДА");
        }

        Task task4 = new Task("Батарейки", "Заменить в мыши");
        System.out.println("task4 : " + task4);
        if (task1.equals(task4)) {
            System.out.println("task1.equals(task4) ДА");
        }
        int taskid2 = manager.addNewTask(task4);
        Task task5 = manager.getTask(taskid2);
        System.out.println("task4 : " + task4 + "\n" +
                "taskid2 : " +  taskid2);
        if (task1.equals(task4)) {
            System.out.println("task1.equals(task4) ДА");
        }else {
            System.out.println("task1.equals(task4) НЕТ");
        }
    }
}
