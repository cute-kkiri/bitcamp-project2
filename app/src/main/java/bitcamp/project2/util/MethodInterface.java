package bitcamp.project2.util;

import bitcamp.project2.vo.Todo;
import java.util.List;

public interface MethodInterface {


    int addTask();                                      // 리스트 추가

    int removeTask();                             // 리스트 삭제

    int removeAllTask();                                // 리스트 전체 삭제

    int updateTask();                            // 리스트 편집

    void viewTask();                              // 리스트 상세 조회

    void taskCheck();                             // 리스트 완료 체크

    List<Todo> viewTasks();                             // 리스트 조회

    List<Todo> viewCompletedTasks();                    // 완료된 목록 리스트 조회

    List<Todo> viewPendingTasks();                      // 미완료된 목록 리스트 조회


}
