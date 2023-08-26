package com.betrybe.repository;

import com.betrybe.model.EntityCarro;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RepositoryCarro implements PanacheRepositoryBase<EntityCarro, Long> {
}
