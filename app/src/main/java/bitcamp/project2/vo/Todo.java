package bitcamp.project2.vo;


import java.util.Objects;

public class Todo {

    private static int seqNo;                   // 시퀀스

    private int no;                             // 인덱스
    private String todo;                        // 할 일
    private String category;                    // 카테고리
    private boolean completedOrNot;             // 완료 유무
    private String memo;                        // 메모
    private int priorityIndex;                  // 우선 순위 인덱스

    public Todo() {
    }

    public Todo(String todo, String category, boolean completedOrNot, String memo,
        int priorityIndex) {
        this.todo = todo;
        this.category = category;
        this.completedOrNot = completedOrNot;
        this.memo = memo;
        this.priorityIndex = priorityIndex;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Todo todo = (Todo) object;
        return no == todo.no;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(no);
    }

    public void nextNo() {
        no = seqNo++;
    }

    public int getNo() {
        return no;
    }

    public String getTodo() {
        return todo;
    }

    public String getCategory() {
        return category;
    }

    public boolean isCompletedOrNot() {
        return completedOrNot;
    }

    public String getMemo() {
        return memo;
    }

    public int getPriorityIndex() {
        return priorityIndex;
    }
}
