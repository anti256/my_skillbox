package response;

public class Task {
    private int id;//идентификатор задачи
    private String name;//название задачи
    private String tasktext = "nothing";//текст задачи с начальным значением
    private String date;//дата и время создания задачи

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTasktext() {
        return tasktext;
    }

    public void setTasktext(String tasktext) {
        this.tasktext = tasktext;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toStr(){
        String fg = "id= " + id + "/ name= " + name + "/ date= " + date + "/ tasktext=" + tasktext;
        return fg;
    }
}
