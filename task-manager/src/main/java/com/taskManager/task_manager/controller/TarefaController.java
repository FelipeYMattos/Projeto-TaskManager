package com.taskManager.task_manager.controller;

import com.taskManager.task_manager.resources.entitys.TarefaEntity;
import com.taskManager.task_manager.services.TarefaService;
import org.apache.catalina.connector.Response;
import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @CrossOrigin
    @PostMapping
    private ResponseEntity<Void> metodoPost(@RequestBody TarefaEntity tarefa){
        service.postTarefa(tarefa);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    private ResponseEntity<TarefaEntity> metodoGetPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.getTarefaPorId(id));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> metodoDeletePorId(Integer id){
        service.deleteTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    private ResponseEntity<Void> metodoUpdatePorId(@PathVariable Integer id, @RequestBody TarefaEntity tarefa){
        service.updateTarefaPorId(id, tarefa);
        return ResponseEntity.ok().build();
    }

}
