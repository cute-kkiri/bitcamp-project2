package bitcamp.project2.util;

import bitcamp.project2.vo.Todo;

public class TaskValidator {

    public boolean isValidatePriorityIndex(int priorityIndex) {
        return priorityIndex >= 1 && priorityIndex <= 4;
    }

    public boolean isValidateArraySize(int no, int length) {
        return no >= 1 && no <= length;
    }

    public boolean taskArrayisNull(Todo[] taskArray) {
        return taskArray == null;
    }
    
}
