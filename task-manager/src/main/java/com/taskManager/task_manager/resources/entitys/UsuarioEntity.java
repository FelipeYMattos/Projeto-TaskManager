package com.taskManager.task_manager.resources.entitys;

import jakarta.persistence.*;
import lombok.*;

@Table (name = "Usuario")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column (unique = true)
    private String email;

    private String senha;

    private String cargo;

    @Column (unique = true)
    private String cpf;

}
