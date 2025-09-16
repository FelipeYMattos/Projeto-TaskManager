package com.taskManager.task_manager.controller;

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
    @GetMapping
    public ResponseEntity<UsuarioEntity> getUsuarioPorId(@RequestParam Integer id){
        return ResponseEntity.ok(service.getUsuarioPorId(id));
    }

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
    @DeleteMapping
    public ResponseEntity<Void> deleteUsuarioPorId(@RequestParam Integer id){
        service.deleteUsuarioPorId(id);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @PutMapping
    public ResponseEntity<Void> putUsuario(@RequestParam Integer id, @RequestBody UsuarioEntity usuario){
        service.putById(id, usuario);
        return ResponseEntity.ok().build();
    }
}
