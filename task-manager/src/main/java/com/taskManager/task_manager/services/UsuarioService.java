package com.taskManager.task_manager.services;

import com.taskManager.task_manager.resources.entitys.UsuarioEntity;
import com.taskManager.task_manager.resources.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioEntity getUsuarioPorId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Erro")
        );
    }

    public List<UsuarioEntity> getTodosUsuarios(){
        return repository.findAll();
    }

    public void postUsuario(UsuarioEntity novoUsuario){
        repository.saveAndFlush(novoUsuario);
    }

    public void deleteUsuarioPorId(Integer id){
        repository.deleteById(id);
    }

    public void putById(Integer id, UsuarioEntity usuario){

        UsuarioEntity usuarioReserva = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Erro")
        );

        UsuarioEntity novoUsuario = UsuarioEntity.builder()
                .id(id)
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioReserva.getNome())
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioReserva.getEmail())
                .senha(usuario.getSenha() != null ? usuario.getSenha() : usuarioReserva.getSenha())
                .cargo(usuario.getCargo() != null ? usuario.getCargo() : usuarioReserva.getCargo())
                .cpf(usuario.getCpf() != null ? usuario.getCpf() : usuarioReserva.getCpf())
                .build();

                repository.save(novoUsuario);
    }

}
