package main;

import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import main.model.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired//аннотация - автоматическое создание репозитория
    private TaskRepository taskRepository;

    @GetMapping("/todos")//ссылка относительно главной страницы
    public List<Task> list(){
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable){
            tasks.add(task);
        }
        return tasks;//возвращает список задач
    }

    @PostMapping("/todos")
    public int add(Task task){//добавление задачи
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH.mm");
        task.setDate(dateFormat.format(new Date()));//назначение задаче даты и времени создания
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity get(@PathVariable int id){//раскрытие отдельной задачи
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }

    @DeleteMapping("/todos")//удаление всех задач
    public int delete(){
        taskRepository.deleteAll();
        return (int) taskRepository.count();
    }

    @DeleteMapping("/todos/{id}")//удаление отдельной задачи
    public int deleteOne(@PathVariable int id){
        taskRepository.deleteById(id);
        return (int) taskRepository.count();
    }

    @PutMapping("/todos/{id}")//обновление отдельной задачи
    public int Put(@PathVariable int id, @RequestBody Task t){
        Task task = new Task();
        task.setId(t.getId());
        task.setName(t.getName());
        task.setTasktext(t.getTasktext());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy HH.mm");
        task.setDate(dateFormat.format(new Date()));//обновляем дату-время задачи
        taskRepository.save(task);
        return task.getId();
    }

    @GetMapping("/todos/count")//общее количество задач
    public int get(){
        return (int) taskRepository.count();
    }
}
/*
taskRepository.save(task)- Сохраняет данный объект
taskRepository.save(tasks)- Сохраняет все заданные объекты
taskRepository.findOne(yaskId)- Извлекает объект по его идентификатору
taskRepository.exists(taskId)- Возвращает, существует ли объект с заданным идентификатором. истина, если объект с данным идентификатором существует
taskRepository.findAll()- Возвращает все объекты. Используйте осторожно, если вы работаете с большим объемом данных
taskRepository.findAll(TaskIds)- Возвращает все экземпляры типа с заданными идентификаторами
taskRepository.count()- Возвращает количество доступных сущностей
taskRepository.delete(taskId)- Удаляет объект с заданным идентификатором
taskRepository.delete(task)- Удаляет указанный объект
taskRepository.delete(tasks)- Удаляет указанные объекты
taskRepository.deleteAll()- Удаляет все объекты, управляемые репозиторием
 */