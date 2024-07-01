package bitcamp.project2.util;

import java.util.List;

public interface MethodInterface {


    int addTask();                          // 리스트 추가

    int removeTask();                       // 리스트 삭제

    int allRemoveTask();                    // 리스트 전체 삭제

    Object updateTask();                    // 리스트 수정

    Object viewTask();                      // 리스트 상세 조회

    Object taskCheck();                     // 리스트 완료 체크

    List<Object> viewTasks();               // 리스트 조회

    List<Object> viewCompletedTasks();      // 완료된 목록 리스트 조회

    List<Object> viewPendingTasks();        // 미완료된 목록 리스트 조회


}
