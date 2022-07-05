package main;

import main.model.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Storage {
    private static volatile int currentId = 1;//начальное значение идентификатора
    private static HashMap<Integer, Task> tasks = new HashMap<Integer, Task>();//набор задач в виде HashMap
    private static int tasksCount;//общее количество задач

    //возвращает общее количество задач
    public static int getTasksCount() {
        return tasksCount;
    }

    //возвращает весь список задач - фактически переделывает HashMap задач в ArrayList
    public static synchronized List<Task> getAllTasks(){
        ArrayList<Task> taskList = new ArrayList<Task>();
        taskList.addAll(tasks.values());
        return taskList;
    }

    //добавление задачи
    public static synchronized int addTask(Task task){//на вход поступает заготовка задачи без id  и date
        int id = currentId++;//переменной назначается значение, потом это значение увеличивается на единицу
        task.setId(id);//назначение задаче идентификатора
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH.mm");
        task.setDate(dateFormat.format(new Date()));//назначение задаче даты и времени создания
        tasks.put(id, task);//внесение задачи в HashMap задач
        tasksCount = tasks.size();//расчет общего количества задач
        return id;
    }

    //возврат отдельной задачи по id
    public static synchronized Task getTask(int taskId){
        if (tasks.containsKey(taskId)){//если id есть в списке
        return tasks.get(taskId);}//возвращается задача
        return null;//если id нет, возвращается null
    }

    //удаление всех задач
    public static synchronized int delAllTasks(){
        tasks.clear();//очистка HashMap задач
        currentId = 1;//сброс текущего идентификатора
        return tasksCount = tasks.size();
    }

    //удаление отдельной задачи
    public static synchronized int deleteOne(int taskId){
        tasks.remove(taskId);
        if (tasks.size() == 0){currentId = 1;}//если задач не осталось, сбрасываем текущий идентификатор в начальное значение
        return tasksCount = tasks.size();
    }

    //изменение одной задачи
    public static synchronized int PutTask(Task task){//на входе нет id, т.к. он есть в задаче
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH.mm");
        task.setDate(dateFormat.format(new Date()));//обновляем дату-время задачи
        tasks.put(task.getId(), task);//обновляем задачу в HashMap задач
        return task.getId();
    }
}
