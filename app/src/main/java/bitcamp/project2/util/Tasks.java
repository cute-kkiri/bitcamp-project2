package bitcamp.project2.util;

import bitcamp.project2.command.TodoCommand;
import bitcamp.project2.vo.Todo;

import java.util.List;

public class Tasks {

    static String priorityIndex1 = "😍";
    static String priorityIndex2 = "🥰";
    static String priorityIndex3 = "😀";
    static String priorityIndex4 = "🙂";

    public static TodoCommand todoCommand = new TodoCommand();

    public static void printPendingTasks() {
        String title = "No. 애정도 제목";
        List<Todo> pendingTasks = todoCommand.viewPendingTasks();
        Todo[] task;

        if (pendingTasks.size() == 0) {
            System.out.println();
            System.out.println("-------------------------");
            System.out.println("등록된 리스트가 없습니다.");
            System.out.println("-------------------------");
        }

        System.out.println();
        if (pendingTasks.size() != 0) {
            System.out.printf("미완료 목록 (%d)\n%s\n", pendingTasks.size(), title);
            for (int i = 0; i < pendingTasks.size(); i++) {
                task = pendingTasks.toArray(new Todo[i]);
                System.out.printf("%d \t  %d \t %s\n", (i + 1), task[i].getPriorityIndex(),
                    task[i].getTodo());
            }
        }
    }

    public static Todo[] getPendingTasks() {
        List<Todo> pendingTasks = todoCommand.viewPendingTasks();
        Todo[] task = null;

        if (!pendingTasks.isEmpty()) {
            for (int i = 0; i < pendingTasks.size(); i++) {
                task = pendingTasks.toArray(new Todo[i]);
            }
        }
        return task;
    }

    public static Todo[] getCompletedTasks() {
        List<Todo> completedTasks = todoCommand.viewCompletedTasks();
        Todo[] task = null;

        if (!completedTasks.isEmpty()) {
            for (int i = 0; i < completedTasks.size(); i++) {
                task = completedTasks.toArray(new Todo[i]);
            }
        }
        return task;
    }

    public static void printCompletedTasks() {
        String title = "No. 우선순위 할 일";

        List<Todo> completedTasks = todoCommand.viewCompletedTasks();

        if (completedTasks.size() == 0) {
            System.out.println();
            System.out.println("-------------------------");
            System.out.println("등록된 리스트가 없습니다.");
            System.out.println("-------------------------");
        }

        System.out.println();
        if (completedTasks.size() != 0) {
            System.out.printf("완료 목록 (%d)\n%s\n", completedTasks.size(), title);
            for (int i = 0; i < completedTasks.size(); i++) {
                Todo task = completedTasks.get(i);
                System.out.printf("%d \t \t %d \t \t %s\n", (i + 1), task.getPriorityIndex(),
                    task.getTodo());
            }
        }
    }

    public static void printAllTasks() {
        String title = "No. 우선순위 할 일";

        List<Todo> pendingTasks = todoCommand.viewPendingTasks();
        List<Todo> completedTasks = todoCommand.viewCompletedTasks();

        if (pendingTasks.size() == 0) {
            System.out.println();
            System.out.println("-------------------------");
            System.out.println("등록된 리스트가 없습니다.");
            System.out.println("-------------------------");
        }

        System.out.println();
        if (pendingTasks.size() != 0) {
            System.out.printf("\033[1m%s %s \t %s\033[0m\n",
                "No.", "애정도", "제목[메모]");
            for (int i = 0; i < pendingTasks.size(); i++) {
                Todo todo = pendingTasks.get(i);
                printPendingTasks(todo, i);
            }
        }

        if (completedTasks.size() == 0) {
            System.out.println();
            System.out.println("-------------------------");
            System.out.println("등록된 리스트가 없습니다.");
            System.out.println("-------------------------");
        }

        System.out.println();
        if (completedTasks.size() != 0) {
            System.out.printf("\033[1m%s %s \t %s\033[0m\n",
                "No.", "애정도", "제목[후기]");
            for (int i = 0; i < completedTasks.size(); i++) {
                Todo todo = completedTasks.get(i);
                printCompletedTasks(todo, i);
            }
        }


    }


    public static void printPendingTasks(Todo todo, int i) {

        switch (todo.getPriorityIndex()) {
            case 1:
                System.out.println(
                    (i + 1) + " \t \t" + priorityIndex1 + " \t \t" +
                        todo.getTodo() + Ansi.green + " [" + todo.getMemo() + "]"
                        + Ansi.reset);
                break;
            case 2:
                System.out.println(
                    (i + 1) + " \t \t" + priorityIndex2 + " \t \t" +
                        todo.getTodo() + Ansi.green + " [" + todo.getMemo() + "]"
                        + Ansi.reset);
                break;
            case 3:
                System.out.println(
                    (i + 1) + " \t \t" + priorityIndex3 + " \t \t" +
                        todo.getTodo() + Ansi.green + " [" + todo.getMemo() + "]"
                        + Ansi.reset);
                break;
            default:
                System.out.println(
                    (i + 1) + " \t \t" + priorityIndex4 + " \t \t" +
                        todo.getTodo() + Ansi.green + " [" + todo.getMemo() + "]"
                        + Ansi.reset);
        }
    }

    public static void printCompletedTasks(Todo todo, int i) {

        switch (todo.getPriorityIndex()) {
            case 1:
                System.out.println(
                    (i + 1) + " \t \t" + priorityIndex1 + " \t \t" +
                        todo.getTodo() + Ansi.ligthGray + " [" + todo.getMemo() + "]"
                        + Ansi.reset);
                break;
            case 2:
                System.out.println(
                    (i + 1) + " \t \t" + priorityIndex2 + " \t \t" +
                        todo.getTodo() + Ansi.ligthGray + " [" + todo.getMemo() + "]"
                        + Ansi.reset);
                break;
            case 3:
                System.out.println(
                    (i + 1) + " \t \t" + priorityIndex3 + " \t \t" +
                        todo.getTodo() + Ansi.ligthGray + " [" + todo.getMemo() + "]"
                        + Ansi.reset);
                break;
            default:
                System.out.println(
                    (i + 1) + " \t \t" + priorityIndex4 + " \t \t" +
                        todo.getTodo() + Ansi.ligthGray + " [" + todo.getMemo() + "]"
                        + Ansi.reset);
        }
    }
}
