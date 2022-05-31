package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import response.Task;

import java.util.List;

@RestController
public class TaskController {

    @GetMapping("/todos")//ссылка относительно главной страницы
    public List<Task> list(){
        return Storage.getAllTasks();//возвращает список задач
    }

    @PostMapping("/todos")
    public int add(Task task){//добавление задачи
        return Storage.addTask(task);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity get(@PathVariable int id){//раскрытие отдельной задачи
        Task task = Storage.getTask(id);
        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @DeleteMapping("/todos")//удаление всех задач
    public int delete(){
        return Storage.delAllTasks();
    }

    @DeleteMapping("/todos/{id}")//удаление отдельной задачи
    public int deleteOne(@PathVariable int id){
        return Storage.deleteOne(id);
    }

    @PutMapping("/todos/{id}")//обновление отдельной задачи
    public int Put(@PathVariable int id, @RequestBody Task t){
        Task task = new Task();
        task.setId(t.getId());
        task.setName(t.getName());
        task.setTasktext(t.getTasktext());
    return Storage.PutTask(task);
    }

    @GetMapping("/todos/count")//общее количество задач
    public int get(){
        return Storage.getTasksCount();
    }
}
