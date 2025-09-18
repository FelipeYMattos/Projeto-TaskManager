package com.taskManager.task_manager.resources.repository;

import com.taskManager.task_manager.resources.entitys.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<TarefaEntity, Integer> {
}
