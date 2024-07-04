package bitcamp.project2.command;

import bitcamp.project2.util.Menus;
import bitcamp.project2.util.MethodInterface;
import bitcamp.project2.util.Prompt;
import bitcamp.project2.vo.Todo;
import bitcamp.project2.util.Tasks;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static bitcamp.project2.util.Tasks.*;

public class TodoCommand implements MethodInterface {

    private List<Todo> todoList;

    public TodoCommand() {
        this.todoList = new ArrayList<>();
    }

    @Override
    public int addTask() {
        String todo = Prompt.input("제목 >>");
        String category = Prompt.input("카테고리 작성 >>");
        String memo = Prompt.input("메모 작성 >>");
        while (true) {
            try {
                int priorityIndex = Prompt.inputInt("우선순위 설정 >>");

                Todo todoItem = new Todo(todo, category, memo, priorityIndex);
                todoList.add(todoItem);
                System.out.println("추가 완료.");
                break;
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
        return 1;
    }

    @Override
    public int removeTask() {
        while (true) {
            try {
                printSubMenu(new String[]{"선택 삭제", "전체 삭제"});
                int menuNo = Prompt.inputInt("편집/삭제 >>");
                Todo[] taskArray = getPendingTasks();

                if (taskArray == null) {
                    System.out.println("등록된 애니가 없습니다.");
                    break;
                }

                switch(menuNo) {
                    case 1:
                        try {
                            printPendingTasks();
                            System.out.println();
                            int no = Prompt.inputInt("삭제할 목록 번호 (이전: 0)>>");
                            int updateNo;

                            if (no == 0) {
                                continue;
                            }

                            if (no >= 1 && no <= taskArray.length) {
                                updateNo = taskArray[no - 1].getNo();
                                for (int i = 0; i < todoList.size(); i++) {
                                    if (todoList.get(i).getNo() == updateNo) {
                                        todoList.remove(i);
                                        break;
                                    }
                                }
                                printPendingTasks();
                                System.out.println("삭제 완료.");
                                continue;
                            } else {
                                System.out.println("잘못된 번호입니다.");
                                continue;
                            }

                        } catch (NumberFormatException ex) {
                            System.out.println("숫자로 메뉴 번호를 입력해주세요.");
                        }
                        break;
                    case 2: removeAllTask();
                        break;
                    case 9: editTask();
                        break;
                    default: System.out.println("유효한 번호를 입력해주세요.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력해주세요.");
            }
        }

        return 1;
    }

    @Override
    public int removeAllTask() {
        Todo[] taskArray = getPendingTasks();
        if (taskArray == null) {
            System.out.println("등록된 애니가 없습니다.");
            return 0;
        }

        String command = Prompt.input("전체 삭제하시겠습니까?(Y/default: N)");
        int size = todoList.size();

        if (command.equalsIgnoreCase("Y")) {
            for (int i = 0; i < size; i++) {
                todoList.removeIf(todo -> !todo.isCompleted());
            }
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
        while (true) {
            Todo[] taskArray = Tasks.getPendingTasks();

            if (taskArray == null) {
                System.out.println("등록된 애니가 없습니다.");
                break;
            }

            printPendingTasks();
            try {
                System.out.println();
                int no = Prompt.inputInt("수정할 목록 번호 (이전: 0)>>");
                int updateNo;

                /*if (no == 0) {
                    editTask();
                    break;
                }*/

                if (no >= 1 && no <= taskArray.length) {
                    updateNo = taskArray[no - 1].getNo();
                } else {
                    System.out.println("잘못된 번호입니다.");
                    continue;
                }

                Todo task = new Todo();

                for (int i = 0; i < todoList.size(); i++) {
                    if (todoList.get(i).getNo() == updateNo) {
                        task = todoList.get(i);
                    }
                }

                while (true) {
                    try {
                        int priorityIndex = Prompt.inputInt("%s (%d) >>", "애정도 수정",
                            task.getPriorityIndex());
                        task.inputPriorityIndex(priorityIndex);

                        task.inputTodo(
                            Prompt.input("%s (%s) >>", "제목 수정", task.getTodo()));
                        task.inputCategory(
                            Prompt.input("%s (%s) >>", "카테고리 수정", task.getCategory()));
                        task.inputMemo(Prompt.input("%s (%s) >>", "메모 수정", task.getMemo()));

                        System.out.println("수정 완료.");
                        printPendingTasks();
                        break;
                    } catch (NumberFormatException ex) {
                        System.out.println("숫자로 애정도를 입력해주세요.");
                    }
                }

                break;
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력해주세요.");
            }
        }
        return 1;
    }

    @Override
    public void taskCheck() {
        while (true) {
            try {
                printPendingTasks();
                System.out.println();
                int no = Prompt.inputInt("기록 할 목록 번호 (이전: 0)>>");
                Todo[] taskArray = getPendingTasks();
                Todo task;
                if (no == 0) {
                    break;
                }
                if (no < 0 || no > taskArray.length) {
                    System.out.println("유효한 번호가 아닙니다.");
                    continue;
                }
                int checkNo = taskArray[no - 1].getNo();

                String command = null;
                for (int i = 0; i < taskArray.length; i++) {
                    if (checkNo == taskArray[i].getNo()) {
                        task = taskArray[i];
                        if (!task.isCompleted()) {
                            command = Prompt.input("(%s)시청기록을 하시겠습니까?(Y/N)", task.getTodo());
                            if (command.equalsIgnoreCase("Y")) {
                                task.check();
                            } else {
                                System.out.println("시청 기록 취소.");
                                break;
                            }
                        } else {
                            System.out.println("이미 기록된 애니입니다.");
                            continue;
                        }
                        printCompletedTasks();
                        System.out.println("시청 기록 완료.");
                        break;
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("기록할 항목을 숫자로 입력해주세요.");
            }
        }
    }

    @Override
    public void viewTask() {
        printAllTasks();
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

    public void editTask() {
        try {
            printSubMenu(new String[]{"수정", "삭제"});
            int menuNo = Prompt.inputInt("편집 >>");

            switch(menuNo) {
                case 1: updateTask();
                    break;
                case 2: removeTask();
                    break;
                case 9: Menus.execute();
                    break;
                default: System.out.println("유효한 번호를 입력해주세요.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("숫자로 메뉴 번호를 입력해주세요.");
        }

        return;
    }

    void printSubMenu(String[] list) {
        System.out.println();
        for (int i = 0; i < list.length; i++) {
            System.out.printf("%d. %s\n", (i + 1), list[i]);
        }
        System.out.println("9. 이전");
    }

    public void loadDummyData() {
        todoList.add(new Todo("오버로드", "이세계", "띵작", 3, true));
        todoList.add(new Todo("강철의 연금술사", "판타지", "4화 보는 중", 2, false));
        todoList.add(new Todo("암살교실", "액션", "짱 재밌네~", 2, true));
        todoList.add(new Todo("최애의 아이", "미스터리", "2화 보는 중", 2, false));
        todoList.add(new Todo("마슐", "판타지", "짱짱 재밌음", 4, true));
        todoList.add(new Todo("진격의 거인", "판타지", "시청 전", 4, false));
        todoList.add(new Todo("전생슬", "이세계", "마지막 화", 1, false));
        todoList.add(new Todo("귀멸의 칼날", "판타지", "시즌 3 존버 중", 1, false));
        todoList.add(new Todo("주술회전", "판타지", "16화", 1, false));
        todoList.add(new Todo("무직전생", "이세계", "2화 14분 30초", 1, false));
    }

}
