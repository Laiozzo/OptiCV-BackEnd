package com.generation.opticvbeckend.model.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity
{
    @Id
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
