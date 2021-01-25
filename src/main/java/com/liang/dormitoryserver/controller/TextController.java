package com.liang.dormitoryserver.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName TextController
 * @Description TODO
 * @Author Liang Xi
 * @DATE 2021/1/16 15:34
 * @Version 1.0
 */
@RestController
@RequestMapping("/tasks")
public class TextController {

    @Secured(value = {"ROLE_manager"})
    @GetMapping
    public String listTasks(){
        return "任务列表";
    }
    @Secured(value = {"ROLE_inspector"})
    @PostMapping
    public String newTasks(){
        return "创建了一个新的任务";
    }

    @PutMapping("/{taskId}")
    public String updateTasks(@PathVariable("taskId")Integer id){
        return "更新了一下id为:"+id+"的任务";
    }

    @DeleteMapping("/{taskId}")
    public String deleteTasks(@PathVariable("taskId")Integer id){
        return "删除了id为:"+id+"的任务";
    }

}
