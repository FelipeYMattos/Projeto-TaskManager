package com.taskManager.task_manager.resources.entitys;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table (name = "Tarefa")
@Getter
@Setter
@Builder
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

    private Integer prioridade;
}
