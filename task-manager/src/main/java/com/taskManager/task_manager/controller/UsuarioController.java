package com.taskManager.task_manager.controller;

import com.taskManager.task_manager.resources.entitys.TarefaEntity;
import com.taskManager.task_manager.resources.entitys.UsuarioEntity;
import com.taskManager.task_manager.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getUsuarioPorId(@PathVariable Integer id){
        return ResponseEntity.ok(service.getUsuarioPorId(id));
    }

    @CrossOrigin
    @GetMapping("/{email}")
    private ResponseEntity<UsuarioEntity> metodoGetPorEmail(@PathVariable String email){
        return ResponseEntity.ok(service.getUsuarioPorEmail(email));
    };

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<List<UsuarioEntity>> getTodosUsuarios(){
        return ResponseEntity.ok(service.getTodosUsuarios());
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Void> postUsuario(@RequestBody UsuarioEntity usuario){
        service.postUsuario(usuario);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarioPorId(@PathVariable Integer id){
        service.deleteUsuarioPorId(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Void> putUsuario(@PathVariable Integer id, @RequestBody UsuarioEntity usuario){
        service.putById(id, usuario);
        return ResponseEntity.ok().build();
    }
}
