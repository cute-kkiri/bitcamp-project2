package bitcamp.project2.util;

import static bitcamp.project2.util.Tasks.printPendingTasks;
import static bitcamp.project2.util.Tasks.todoCommand;

public class Menus {
    static String[] menus = new String[]{"리스트 추가", "리스트 조회", "리스트 편집", "체크 하기"};

    public static void execute() {
        printPendingTasks();
        printMenu();

        String command;
        while (true) {
            try {
                command = Prompt.input(">> ");
                int menuNo = Integer.parseInt(command);
                if (menuNo == 0) {
                    System.out.println("종료.");
                    break;
                } else {
                    String menuTitle = getMenuTitle(menuNo, menus);
                    if (menuTitle == null) {
                        System.out.println("유효한 메뉴 번호를 입력해 주세요.");
                        continue;
                    }

                    processMenu(menuTitle);
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자로 메뉴 번호를 입력해주세요.");
            }
        }
    }

    public static void processMenu(String menuTitle) {
        switch (menuTitle) {
            case "리스트 추가":
                todoCommand.addTask();
                printPendingTasks();
                printMenu();
                break;
            case "리스트 조회":
                todoCommand.viewTask();
                printMenu();
                break;
            case "리스트 편집":
                while(true) {
                    todoCommand.editTask();
                    break;
                }
                // todoCommand.updateTask();
                printMenu();
                break;
            case "체크 하기":
                todoCommand.taskCheck();
                printMenu();
                break;
            default:
                System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
        }

    }

    public static void printMenu() {
        System.out.println();
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("%d.%s\t\t", (i + 1), menus[i]);
        }
        System.out.println("0.종료");
    }

    static boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    static String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
    }
}
