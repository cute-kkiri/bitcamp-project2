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
    public static String line = "--------------------------------------------------";

    private static String listHead(boolean detail) {
        if (detail) {
            return Ansi.bold + "No. 애정도 \t 제목[메모]" + Ansi.reset;
        }

        return Ansi.bold + "No. 애정도 \t 제목" + Ansi.reset;
    }

    private static String printTodoList(Todo todo) {
        String emoji = getEmoji(todo.getPriorityIndex());
        return "\t \t \t" + emoji + " \t " + todo.getTodo();
    }

    public static void listEmpty(int size) {
        if (size == 0) {
            System.out.println();
            System.out.println(line);
            System.out.println("등록된 리스트가 없습니다.");
            System.out.println(line);
        }
    }

    public static void printPendingTasks() {
        List<Todo> pendingTasks = todoCommand.viewPendingTasks();
        Todo[] task;

        listEmpty(pendingTasks.size());

        if (pendingTasks.size() != 0) {
            System.out.println();
            System.out.println(line);
            System.out.printf("시청 할 애니 목록 (%d)\n", pendingTasks.size());
            System.out.println(line);
            System.out.printf("%s\n", listHead(false));
            for (int i = 0; i < pendingTasks.size(); i++) {
                task = pendingTasks.toArray(new Todo[i]);
                System.out.println((i + 1) + printTodoList(task[i]));
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
        List<Todo> completedTasks = todoCommand.viewCompletedTasks();

        listEmpty(completedTasks.size());

        if (completedTasks.size() != 0) {
            System.out.println();
            System.out.println(line);
            System.out.printf("내 시청 기록 (%d)\n", completedTasks.size());
            System.out.println(line);
            System.out.printf("%s\n", listHead(false));
            for (int i = 0; i < completedTasks.size(); i++) {
                Todo task = completedTasks.get(i);
                System.out.println((i + 1) + printTodoList(task));
            }
        }
    }

    public static void printAllTasks() {
        List<Todo> pendingTasks = todoCommand.viewPendingTasks();
        List<Todo> completedTasks = todoCommand.viewCompletedTasks();

        listEmpty(pendingTasks.size());

        if (pendingTasks.size() != 0) {
            System.out.println();
            System.out.println(line);
            System.out.printf("시청 할 애니 목록 (%d)\n", pendingTasks.size());
            System.out.println(line);
            System.out.printf("%s\n", listHead(true));
            for (int i = 0; i < pendingTasks.size(); i++) {
                Todo todo = pendingTasks.get(i);
                printTasks(todo, i, Ansi.green);
            }
        }
        System.out.println(line);

        listEmpty(completedTasks.size());

        if (completedTasks.size() != 0) {
            System.out.println();
            System.out.println(line);
            System.out.printf("내 시청 기록 (%d)\n", completedTasks.size());
            System.out.println(line);
            System.out.printf("%s\n", listHead(true));
            for (int i = 0; i < completedTasks.size(); i++) {
                Todo todo = completedTasks.get(i);
                printTasks(todo, i, Ansi.ligthGray);
            }
        }
    }

    public static String getEmoji(int i) {
        String emoji;
        switch (i) {
            case 1:
                emoji = priorityIndex1;
                break;
            case 2:
                emoji = priorityIndex2;
                break;
            case 3:
                emoji = priorityIndex3;
                break;
            default:
                emoji = priorityIndex4;
                break;
        }
        return emoji;

    }

    public static void printTasks(Todo todo, int i, String ansi) {
        String emoji = getEmoji(todo.getPriorityIndex());
        System.out.println(
            (i + 1) + " \t \t" + emoji + "\t \t " +
                todo.getTodo() + ansi + " [" + todo.getMemo() + "]"
                + Ansi.reset);
    }
}



