package com.betrybe.service;

import com.betrybe.model.EntityCarro;
import com.betrybe.repository.RepositoryCarro;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

/**
 * No quarkus usamos escopos de aplicacao(ApplicationScoped) tanto no repository como no service
 * tornando ambos injetaveis
 */
@ApplicationScoped
public class ServiceCarro {

    @Inject
    private RepositoryCarro repository;

    public List<EntityCarro> getAllCars() {
        return repository.listAll();
    }

    public EntityCarro getById(Long id) {
        return repository.findById(id);
    }

    // todos os dados que forem salvos, atualizados ou excluidos usam a annotation transactional
    @Transactional
    public void salvar(EntityCarro carro) {
        repository.persist(carro);
    }

    @Transactional
    public EntityCarro update(Long id, EntityCarro carro) {
        var findCarro = repository.findById(id);
        if (findCarro == null) {
            return null;
        }
        findCarro.setAno(carro.getAno());
        findCarro.setNome(carro.getNome());
        findCarro.setPreco(carro.getPreco());
        repository.persist(findCarro);
        return findCarro;
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
