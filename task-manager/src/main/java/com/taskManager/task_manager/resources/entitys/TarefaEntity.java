package com.taskManager.task_manager.resources.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "Tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer idUsuario;

    @Column (unique = true)
    private String titulo;

    private String descricao;

    private String dataCriacao;
}
