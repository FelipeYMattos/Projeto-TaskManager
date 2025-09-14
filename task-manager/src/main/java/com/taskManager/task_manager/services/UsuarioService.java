package com.taskManager.task_manager.services;

import com.taskManager.task_manager.resources.entitys.UsuarioEntity;
import com.taskManager.task_manager.resources.repository.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private PessoaRepository repository;

    public UsuarioEntity getUsuarioPorId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Erro")
        );
    }

    public void postUsuario(UsuarioEntity novoUsuario){
        repository.saveAndFlush(novoUsuario);
    }

    public void deleteUsuarioPorId(Integer id){
        repository.deleteById(id);
    }

    public void putById(Integer id, UsuarioEntity usuario){

        UsuarioEntity usuarioReserva = getUsuarioPorId(id);

        UsuarioEntity novoUsuario = UsuarioEntity.builder()
                .nome(usuario.getNome() != null ? usuario.getNome() : usuarioReserva.getNome())
                .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioReserva.getEmail())
                .senha(usuario.getSenha() != null ? usuario.getSenha() : usuarioReserva.getSenha())
                .cargo(usuario.getCargo() != null ? usuario.getCargo() : usuarioReserva.getCargo())
                .cpf(usuario.getCpf() != null ? usuario.getCpf() : usuarioReserva.getCpf())
                .build();

                repository.saveAndFlush(novoUsuario);
    }

}
