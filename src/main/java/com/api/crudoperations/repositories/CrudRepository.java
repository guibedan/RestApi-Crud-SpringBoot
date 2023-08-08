package com.api.crudoperations.repositories;

import com.api.crudoperations.models.CrudModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CrudRepository extends JpaRepository<CrudModel, UUID> {
    boolean existsByUserName(String userName);
}
