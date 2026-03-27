package com.sistema.contatos.aula_06.service;

import com.sistema.contatos.aula_06.exception.ResourceNotFoundException;
import com.sistema.contatos.aula_06.model.Contato;
import com.sistema.contatos.aula_06.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public List<Contato> listarTodos() {
        return repository.findAll();
    }

    public Contato buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Erro: Contato com ID " + id + " não encontrado."));
    }

    public Contato criar(Contato contato) {
        if (repository.findByEmail(contato.getEmail()).isPresent()) {
            throw new RuntimeException("Erro: Este e-mail já está cadastrado.");
        }
        return repository.save(contato);
    }

    public Contato atualizar(Long id, Contato dadosAtualizados) {
        Contato contatoExistente = buscarPorId(id);

        contatoExistente.setNome(dadosAtualizados.getNome());
        contatoExistente.setEmail(dadosAtualizados.getEmail());
        contatoExistente.setTelefone(dadosAtualizados.getTelefone());

        return repository.save(contatoExistente);
    }

    public void deletar(Long id) {
        Contato contato = buscarPorId(id);
        repository.delete(contato);
    }

    public List<Contato> pesquisarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }
}