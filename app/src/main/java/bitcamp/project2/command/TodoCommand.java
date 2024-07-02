package bitcamp.project2.command;


import static bitcamp.project2.App.getPendingTasks;

import bitcamp.project2.util.MethodInterface;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.Todo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TodoCommand implements MethodInterface {

    private List<Todo> todoList;

    public TodoCommand() {
        this.todoList = new ArrayList<>();
    }

    @Override
    public int addTask() {
        String todo = Prompt.input("할일 작성 >>");
        String category = Prompt.input("카테고리 작성 >>");
        String memo = Prompt.input("메모 작성 >>");
        int priorityIndex = 0;
        while (priorityIndex == 0) {
            try {
                priorityIndex = Prompt.inputInt("우선순위 설정 >>");

                Todo todoItem = new Todo(todo, category, memo, priorityIndex);
                todoList.add(todoItem);
                System.out.println("추가 완료.");
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }

        return 1;
    }

    @Override
    public int removeTask() {
        System.out.println("1. 선택 삭제");
        System.out.println("2. 전체 삭제");
        System.out.println("9. 이전");

        while (true) {
            try {
                int menuNo = Prompt.inputInt("편집/삭제 >>");
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
                    // printTask();
                }
                if (menuNo == 2) {
                    removeAllTask();
                    break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
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
            return 1;
        } else {
            System.out.println("삭제 취소.");
            removeTask();
            return 0;
        }
    }

    @Override
    public int updateTask() {
        System.out.println("1. 수정");
        System.out.println("2. 삭제");
        System.out.println("9. 이전");
        while (true) {
            try {
                int menuNo = Prompt.inputInt("편집/수정 >>");
                if (menuNo == 9 || menuNo == 1 || menuNo == 2) {
                    if (menuNo == 9) {
                        break;
                    }
                    if (menuNo == 1) {
                        int no = Prompt.inputInt("수정할 리스트 번호 >>");
                        Todo[] taskArray = getPendingTasks();
                        int updateNo;
                        Todo task = new Todo();
                        updateNo = taskArray[no - 1].getNo();

                        for (int i = 0; i < todoList.size(); i++) {
                            if (todoList.get(i).getNo() == updateNo) {
                                task = todoList.get(i);
                            }
                        }

                        while (true) {
                            try {
                                int priorityIndex = Prompt.inputInt("%s (%d) >>", "우선 순위 수정",
                                    task.getPriorityIndex());
                                task.inputPriorityIndex(priorityIndex);

                                task.inputTodo(
                                    Prompt.input("%s (%s) >>", "할 일 수정", task.getTodo()));
                                task.inputCategory(
                                    Prompt.input("%s (%s) >>", "카테고리 수정", task.getCategory()));
                                task.inputMemo(Prompt.input("%s (%s) >>", "메모 수정", task.getMemo()));

                                System.out.println("수정 완료.");
                                break;
//                                printTask();
                            } catch (NumberFormatException ex) {
                                System.out.println("숫자로 우선순위를 입력하세요.");
                            }
                        }
                    }
                    if (menuNo == 2) {
                        removeTask();
                    }
                } else {
                    System.out.println("잘못된 메뉴 번호입니다.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }

        return 1;
    }

    @Override
    public void taskCheck() {
        while (true) {
            try {
                System.out.println();
                System.out.println("0번을 누르면 이전으로 돌아갑니다.");
                int no = Prompt.inputInt("체크할 리스트 번호 >>");
                Todo task;
                if (no == 0) {
                    break;
                }
                for (int i = 0; i < todoList.size(); i++) {
                    if ((no - 1) == todoList.get(i).getNo()) {
                        task = todoList.get(i);
                        task.check();
                        System.out.println("체크 완료.");
                        break;
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("체크할 항목을 숫자로 입력하세요.");
            }
        }
    }

    @Override
    public void viewTask() {
        Todo task = new Todo();
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(task = todoList.get(i));
        }

       /* Todo task = new Todo();
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
        }*/
    }

    @Override
    public List<Todo> viewTasks() {
        return new ArrayList<>(todoList);
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

    public void loadDummyData() {
        todoList.add(new Todo("정처기 공부하기", "Dummy Task 1", "테스트를 위한 더미 데이터1", 3, true));
        todoList.add(new Todo("SQLD 공부하기", "Dummy Task 2", "테스트를 위한 더미 데이터2", 2, false));
        todoList.add(new Todo("SPRING 강의보기", "Dummy Task 3", "테스트를 위한 더미 데이터3", 1, true));
        todoList.add(new Todo("JAVA 공부하기", "Dummy Task 3", "테스트를 위한 더미 데이터4", 4, false));
        todoList.add(new Todo("HTML 복습하기", "Dummy Task 3", "테스트를 위한 더미 데이터5", 1, true));
        todoList.add(new Todo("CSS 연습하기", "Dummy Task 3", "테스트를 위한 더미 데이터6", 2, false));
    }
}
