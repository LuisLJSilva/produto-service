package br.com.infnet.produtoservice.controller;

import br.com.infnet.produtoservice.exception.ProdutoNotFoundException;
import br.com.infnet.produtoservice.model.Produto;
import br.com.infnet.produtoservice.payload.ResponsePayload;
import br.com.infnet.produtoservice.service.ProdutoService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/")
public class ProdutoController {
    @Autowired
    ProdutoService produtoService;

    Logger LOGGER = LoggerFactory.getLogger(ProdutoController.class);
    @GetMapping
    public List<Produto> getAll(){
        List<Produto> all = produtoService.getAll();
        LOGGER.info("GET ALL:" + all);
        return all;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        try {
            Produto produto = produtoService.getById(id);
            LOGGER.info("GET BY ID" + produto);
            return ResponseEntity.ok(produto);
        }catch (ProdutoNotFoundException exception){
            ResponsePayload notFound = ResponsePayload.builder().message("Not Found").dateTime(LocalDateTime.now()).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFound);
        }


    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        LOGGER.info("DELETE" + id);
        produtoService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Produto produto){
       Produto created = produtoService.create(produto);
        LOGGER.info("CREATE" + created);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody Produto produto){
        Produto updated = produtoService.update(id, produto);
        LOGGER.info("UPDATE: " + updated);

    }
}
