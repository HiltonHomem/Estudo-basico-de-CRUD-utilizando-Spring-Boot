package com.gerenciamento_tarefas.gentarefas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gentarefas")
public class GentarefasController {
    //Deve ser criado um objeto do tipo do repository que vai ser de onde as informações vão sair
    @Autowired
    private GentarefasRepository gentarefasRepository;

    //Get feito para retornar todos os dados
    @GetMapping
    public List<GentarefasEntity> getAllGentarefas(){
        return gentarefasRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GentarefasEntity> getGentarefasById (@PathVariable Long id){

        Optional<GentarefasEntity> gentarefa = gentarefasRepository.findById(id);
        //Retorna o HTTP200 caso o metodo map localize, "orElseGet()" vai indicar a ação
        //a ser tomada caso não ache o id, controi a reposta de erro HTTP404
        return gentarefa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GentarefasEntity> updateGentarefasById (@PathVariable Long id,@RequestBody GentarefasEntity gentarefasEntity){
        //verificando se o id existe, o entity me da os campos necessários e no repository é onde eu guardo
        if (!gentarefasRepository.existsById(id)){
         return ResponseEntity.notFound().build();
        }else{
            gentarefasEntity.setId(id); // Garantir que o ID da tarefa seja atualizado corretamente
            return ResponseEntity.ok(gentarefasRepository.save(gentarefasEntity));
        }
    }

    @PostMapping
    public ResponseEntity<GentarefasEntity> createGentarefas(@RequestBody GentarefasEntity gentarefasEntity){
        GentarefasEntity savedEntity = gentarefasRepository.save(gentarefasEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntity);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllGentarefas(){
        gentarefasRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.CREATED).body("VOCê DELETOU TODO O BANCO DE DADOS!!!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGentarefasById(@PathVariable Long id){
        gentarefasRepository.deleteById(id);
        String menssagem = "VocÊ deletou a tarefa de ID igual a " +id;
        return ResponseEntity.status(HttpStatus.CREATED).body(menssagem);
    }
}
