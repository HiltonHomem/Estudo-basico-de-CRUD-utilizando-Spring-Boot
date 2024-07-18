package com.gerenciamento_tarefas.gentarefas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GentarefasRepository extends JpaRepository<GentarefasEntity, Long> {

}
