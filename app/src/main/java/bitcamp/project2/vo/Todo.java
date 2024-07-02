package bitcamp.project2.vo;


import java.util.Objects;

public class Todo {

    private static int seqNo;                           // 시퀀스

    private int no;                                     // 인덱스
    private String todo;                                // 할 일
    private String category;                            // 카테고리
    private boolean isCompleted = false;                // 완료 유무 (기본값 false)
    private String memo;                                // 메모
    private int priorityIndex = 0;                      // 우선 순위 인덱스 (기본값 false)

    public Todo(String todo, String category, String memo, int priorityIndex) {
        this.no = nextSeqNo();
        this.todo = todo;
        this.category = category;
        this.memo = memo;
        this.priorityIndex = priorityIndex;
    }


    @Override
    public String toString() {
        return (no + 1) + " \t \t " + priorityIndex + " \t \t " + todo + " \t \t ";
    }

    public String toAllString() {
        return (no + 1) + " \t \t " + priorityIndex + " \t \t " + todo + " \t \t " + category
            + " \t \t " +
            memo;
    }

    public Todo() {
    }

    public Todo(String todo, String category, String memo) {
        this.no = nextSeqNo();
        this.todo = todo;
        this.category = category;
        this.memo = memo;
    }

    public Todo(String todo, String category, String memo, int priorityIndex, boolean isCompleted) {
        this.no = nextSeqNo();
        this.todo = todo;
        this.category = category;
        this.memo = memo;
        this.priorityIndex = priorityIndex;
        this.isCompleted = isCompleted;
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

    public int nextSeqNo() {
        return seqNo++;
    }

    public int getNo() {
        return no;
    }

    public String getTodo() {
        return todo;
    }

    public void inputTodo(String todo) {
        this.todo = todo;
    }

    public void inputCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void check() {
        this.isCompleted = true;
    }

    public String getMemo() {
        return memo;
    }

    public void inputMemo(String memo) {
        this.memo = memo;
    }

    public int getPriorityIndex() {
        return priorityIndex;
    }

    public void inputPriorityIndex(int priorityIndex) {
        this.priorityIndex = priorityIndex;
    }
}
