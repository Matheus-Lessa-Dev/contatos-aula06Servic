package com.sistema.contatos.aula_06.controller;

import com.sistema.contatos.aula_06.model.Contato;
import com.sistema.contatos.aula_06.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
@Tag(name = "Contatos", description = "Gerenciamento de contatos pessoais") // Documentação Swagger
public class ContatoController {

    @Autowired
    private ContatoService service;

    @GetMapping
    @Operation(summary = "Listar todos os contatos")
    public List<Contato> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contato por ID")
    public ResponseEntity<Contato> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/pesquisa")
    @Operation(summary = "Pesquisar contato por parte do nome")
    public List<Contato> pesquisarPorNome(@RequestParam String nome) {
        return service.pesquisarPorNome(nome);
    }

    @PostMapping
    @Operation(summary = "Criar um novo contato")
    public ResponseEntity<Contato> criar(@RequestBody Contato contato) {
        return new ResponseEntity<>(service.criar(contato), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um contato existente")
    public ResponseEntity<Contato> atualizar(@PathVariable Long id, @RequestBody Contato contato) {
        return ResponseEntity.ok(service.atualizar(id, contato));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um contato")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
