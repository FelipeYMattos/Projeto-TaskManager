package com.taskManager.task_manager.resources.repository;

import com.taskManager.task_manager.resources.entitys.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
}
