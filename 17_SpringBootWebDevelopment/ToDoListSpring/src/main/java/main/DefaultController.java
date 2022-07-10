package main;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@Controller
public class DefaultController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")
    public String index(Model model){
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> taskS = new ArrayList<>();
        for (Task task : taskIterable){
            taskS.add(task);
        }
        model.addAttribute("tasks", taskS);
        model.addAttribute("tasksCount", taskS.size());
        return "index";
        //return (new Date()).toString();
    }
}
