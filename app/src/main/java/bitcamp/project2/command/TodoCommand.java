package bitcamp.project2.command;


import static bitcamp.project2.App.printPendingTasks;
import static bitcamp.project2.App.printCompletedTasks;
import static bitcamp.project2.App.printAllTasks;

import bitcamp.project2.util.MethodInterface;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.CompletedTask;
import bitcamp.project2.vo.PendingTask;
import bitcamp.project2.vo.Todo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TodoCommand implements MethodInterface {

    private List<Todo> todoList;
    private List<PendingTask> pendingTasks;
    private List<CompletedTask> completedTasks;

    public TodoCommand() {
        this.todoList = new ArrayList<>();
        this.pendingTasks = new ArrayList<>();
        this.completedTasks = new ArrayList<>();
    }

    @Override
    public int addTask() {
        String todo = Prompt.input("할일 작성 >>");
        String category = Prompt.input("카테고리 작성 >>");
        String memo = Prompt.input("메모 작성 >>");
        int priorityIndex = Prompt.inputInt("우선순위 설정 >>");

        Todo todoItem = new Todo(todo, category, memo, priorityIndex);

        todoList.add(todoItem);
        System.out.println("추가 완료.");
        printPendingTasks();
        return 1;
    }

    @Override
    public int removeTask() {
        System.out.println("1. 선택 삭제");
        System.out.println("2. 전체 삭제");
        System.out.println("9. 이전");
        while (true) {
            int menuNo = Prompt.inputInt("메뉴 선택 >>");
            if (menuNo == 9) {
                break;
            }
            if (menuNo == 1) {
                int no = Prompt.inputInt("삭제할 리스트 번호 >>");
                int index = 0;
                for (int i = 0; i < todoList.size(); i++) {
                    if ((no - 1) == todoList.get(i).getNo()) {
                        index = i;
                        break;
                    }
                }
                todoList.remove(index);
                System.out.println("삭제 완료.");
                printPendingTasks();
            }
            if (menuNo == 2) {
                removeAllTask();
                break;
            }


        }
        return 1;
    }

    @Override
    public int removeAllTask() {
        String command = Prompt.input("전체 삭제하시겠습니까?(Y/N)");
        if (command.equalsIgnoreCase("Y")) {
            todoList.removeAll(todoList);
            System.out.println("전체 삭제 완료.");
            printPendingTasks();
            return 1;
        } else {
            System.out.println("삭제 취소.");
            return 0;
        }
    }

    @Override
    public int updateTask() {
        System.out.println("1. 수정");
        System.out.println("2. 삭제");
        System.out.println("9. 이전");
        int menuNo = Prompt.inputInt("메뉴 선택 >>");

        if (menuNo == 9 || menuNo == 1 || menuNo == 2) {
            if (menuNo == 9) {
                return 0;
            }
            if (menuNo == 1) {
                int no = Prompt.inputInt("편집할 리스트 번호 >>");
                Todo task = new Todo();
                for (Todo todo : todoList) {
                    if ((no - 1) == todo.getNo()) {
                        task = todo;
                        break;
                    }
                }
                task.inputPriorityIndex(
                    Prompt.inputInt("%s (%d) >>", "우선 순위 수정", task.getPriorityIndex()));
                task.inputTodo(Prompt.input("%s (%s) >>", "할 일 수정", task.getTodo()));
                task.inputCategory(Prompt.input("%s (%s) >>", "카테고리 수정", task.getCategory()));
                task.inputMemo(Prompt.input("%s (%s) >>", "메모 수정", task.getMemo()));

                System.out.println("수정 완료.");
                printPendingTasks();
            }
            if (menuNo == 2) {
                removeTask();
            }
        } else {
            System.out.println("잘못된 메뉴 번호입니다.");
        }

        return 1;
    }

    @Override
    public void taskCheck() {
        int no = Prompt.inputInt("체크할 리스트 번호 >>");
        Todo task;
        for (int i = 0; i < todoList.size(); i++) {
            if ((no - 1) == todoList.get(i).getNo()) {
                task = todoList.get(i);
                task.check();
                System.out.println("체크 완료.");
                break;
            }
        }
        printPendingTasks();
    }

    @Override
    public void viewTask() {
        printPendingTasks();

        Todo task = new Todo();
        int no = Prompt.inputInt("조회할 리스트 번호 >>");
        for (int i = 0; i < todoList.size(); i++) {
            if ((no - 1) == todoList.get(i).getNo()) {
                task = todoList.get(i);
                break;
            }
        }
        if (task != null) {
            System.out.println(
                "No. 우선순위 할 일 \t \t \t \t \t \t \t 카테고리 \t \t \t \t 메모");
            System.out.println(task.toAllString());
        }
    }

    @Override
    public List<Todo> viewTasks() {
        return todoList.stream().sorted(Comparator.comparingInt(Todo::getPriorityIndex))
            .collect(Collectors.toList());
    }

    @Override
    public List<Todo> viewCompletedTasks() {
        return todoList.stream()
            .filter(Todo::isCompleted)
            .sorted(Comparator.comparingInt(Todo::getPriorityIndex))
            .collect(Collectors.toList());
    }

    @Override
    public List<Todo> viewPendingTasks() {
        return todoList.stream()
            .filter(todo -> !todo.isCompleted())
            .sorted(Comparator.comparingInt(Todo::getPriorityIndex))
            .collect(Collectors.toList());
    }

    public static void printPendingTasks() {
        String title = "No. 우선순위 할 일";

        for (int i = 0; i < tasks.size(); i++) {
            PendingTask pendingTask = new PendingTask();
            pendingTask.setNo(i);
            pendingTask.setCategory(tasks.get(i).getCategory());
            pendingTask.setMemo(tasks.get(i).getMemo());
            pendingTask.setPriorityIndex(tasks.get(i).getPriorityIndex());
            pendingTask.setCompleted(false);
            pendingTask.setTodo(tasks.get(i).getTodo());
            pendingTasks.add(pendingTask);
        }

        if (tasks.size() == 0) {
            System.out.println();
            System.out.println("-------------------------");
            System.out.println("등록된 리스트가 없습니다.");
            System.out.println("-------------------------");
        }

        System.out.println();
        if (pendingTasks.size() != 0) {
            System.out.printf("미완료 목록 (%d)\n%s\n", pendingTasks.size(), title);
            for (int i = 0; i < pendingTasks.size(); i++) {
                PendingTask task = pendingTasks.get(i);
                System.out.printf("%d \t \t %d \t \t %s\n",
                    (task.getNo() + 1),
                    task.getPriorityIndex(),
                    task.getTodo());
            }
        }
    }


    public void loadDummyData() {
        todoList.add(new Todo("정처기 공부하기", "Dummy Task 1", "테스트를 위한 더미 데이터1", 3, true));
        todoList.add(new Todo("SQLD 공부하기", "Dummy Task 2", "테스트를 위한 더미 데이터2", 2, false));
        todoList.add(new Todo("SPRING 강의보기", "Dummy Task 3", "테스트를 위한 더미 데이터3", 1, true));
        todoList.add(new Todo("JAVA 공부하기", "Dummy Task 3", "테스트를 위한 더미 데이터4", 4, false));
        todoList.add(new Todo("HTML 복습하기", "Dummy Task 3", "테스트를 위한 더미 데이터5", 1, true));
        todoList.add(new Todo("CSS 연습하기", "Dummy Task 3", "테스트를 위한 더미 데이터6", 2, false));
    }
}
