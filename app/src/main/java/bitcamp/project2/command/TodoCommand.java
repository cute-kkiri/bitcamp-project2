package bitcamp.project2.command;

import bitcamp.project2.util.MethodInterface;
import bitcamp.project2.vo.Todo;
import java.util.ArrayList;
import java.util.List;

public class TodoCommand implements MethodInterface {

    List<Todo> todoList = new ArrayList<>();


    @Override
    public int addTask() {
        return 0;
    }

    @Override
    public int removeTask() {
        return 0;
    }

    @Override
    public int allRemoveTask() {
        return 0;
    }

    @Override
    public Object updateTask() {
        return null;
    }

    @Override
    public Object taskCheck() {
        return null;
    }

    @Override
    public Object viewTask() {
        return null;
    }

    @Override
    public List<Object> viewTasks() {
        return List.of();
    }

    @Override
    public List<Object> viewCompletedTasks() {
        return List.of();
    }

    @Override
    public List<Object> viewPendingTasks() {
        return List.of();
    }
}
