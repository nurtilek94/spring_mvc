package peaksoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Task;
import peaksoft.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

     @GetMapping("/allTask/{lessonId}")
    private String getAllTask(@PathVariable("lessonId")Long id, Model model){
        model.addAttribute("allTask",taskService.getAllTasks(id));
        model.addAttribute("lessonId",id);
        return "task/mainTask";
    }

    @GetMapping("/{lessonId}/newTask")
    private String newTask(@PathVariable("lessonId")Long lessonId, Model model) {
        model.addAttribute("newTask", new Task());
        model.addAttribute("lessonId",lessonId);
        return "task/newTask";
    }

    @PostMapping("{lessonId}/saveTask")
    private String saveTask(@PathVariable("lessonId")Long id, @ModelAttribute("newTask") Task task) {
        taskService.addTask(id,task);
        return "redirect:/tasks/allTask/ "+ id;
    }
    @GetMapping("/getTask/{taskId}")
    private String getLessonById(@PathVariable("taskId")Long id,Model model) {
        model.addAttribute("task",taskService.getTaskById(id));
        return "task/mainTask";
    }
    @GetMapping("/updateTask/{taskId}")
    private String updateTask(@PathVariable("taskId")Long taskId,Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task",task);
        model.addAttribute("lessonId",task.getLesson().getId());
        return "task/updateTask";
    }
    @PostMapping("/{lessonId}/{taskId}/saveUpdateTask")
    private String saveUpdateTask(@PathVariable("lessonId")Long lessonId,
                                  @PathVariable("taskId")Long taskId,
                                  @ModelAttribute("task")Task task) {
        taskService.updateTask(taskId,task);
        return "redirect:/tasks/allTask/ " + lessonId;

    }
    @PostMapping("/{lessonId}/{taskId}/deleteTask")
    private String deleteTask(@PathVariable("lessonId")Long id,
                              @PathVariable("taskId")Long taskId) {
        taskService.delete(taskId);
        return "redirect:/tasks/allTask/ " + id;
    }
}
