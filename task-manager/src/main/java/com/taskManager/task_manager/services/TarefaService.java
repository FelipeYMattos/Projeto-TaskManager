package com.taskManager.task_manager.services;

import com.taskManager.task_manager.resources.entitys.TarefaEntity;
import com.taskManager.task_manager.resources.repository.TarefaRepository;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public TarefaEntity getTarefaPorId(Integer id){
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Tarefa não encontrada")
        );
    }

    public void postTarefa(TarefaEntity tarefa){
        repository.saveAndFlush(tarefa);
    }

    public void deleteTarefaPorId(Integer id){
        repository.deleteById(id);
    }

    public void updateTarefaPorId(Integer id, TarefaEntity novosDados){
         TarefaEntity tarefaOriginal = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Tarefa não encontrada, não é possível continuar o update.")
        );

         TarefaEntity novaTarefa = TarefaEntity.builder()
                 .id(id)
                 .idUsuario(novosDados.getIdUsuario() != null ? novosDados.getIdUsuario() : tarefaOriginal.getIdUsuario())
                 .titulo(novosDados.getTitulo() != null ? novosDados.getTitulo() : tarefaOriginal.getTitulo())
                 .descricao(novosDados.getDescricao() != null ? novosDados.getDescricao() : tarefaOriginal.getDescricao())
                 .dataCriacao(novosDados.getDataCriacao() != null ? novosDados.getDataCriacao() : tarefaOriginal.getDataCriacao())
                 .prioridade(novosDados.getPrioridade() != null ? novosDados.getPrioridade() : tarefaOriginal.getPrioridade())
                 .build();

         repository.save(novaTarefa);

    }

}
