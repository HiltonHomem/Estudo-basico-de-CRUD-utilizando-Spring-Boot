package com.gerenciamento_tarefas.gentarefas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GentarefasResponse {
    private Long id;
    private String title;
    private String description;
    private Boolean status;
}
