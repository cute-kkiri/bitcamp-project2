package bitcamp.project2.vo;

public class PendingTask {

    private int no;                                     // 인덱스
    private String todo;                                // 할 일
    private String category;                            // 카테고리
    private boolean isCompleted = false;                // 완료 유무 (기본값 false)
    private String memo;                                // 메모
    private int priorityIndex = 0;

    public PendingTask() {
    }

    public PendingTask(int no, String todo, String category, boolean isCompleted, String memo,
        int priorityIndex) {
        this.no = no;
        this.todo = todo;
        this.category = category;
        this.isCompleted = isCompleted;
        this.memo = memo;
        this.priorityIndex = priorityIndex;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getPriorityIndex() {
        return priorityIndex;
    }

    public void setPriorityIndex(int priorityIndex) {
        this.priorityIndex = priorityIndex;
    }
}
