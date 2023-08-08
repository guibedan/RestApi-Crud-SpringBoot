package com.api.crudoperations.services;

import com.api.crudoperations.models.CrudModel;
import com.api.crudoperations.repositories.CrudRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CrudService {

    final CrudRepository crudRepository;

    public CrudService(CrudRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Transactional
    public CrudModel save(CrudModel crudModel) {
        return crudRepository.save(crudModel);
    }

    public boolean existsByUserName(String userName) {
        return crudRepository.existsByUserName(userName);
    }


    public List<CrudModel> findAll() {
        return crudRepository.findAll();
    }

    public Optional<CrudModel> findById(UUID id) {
        return crudRepository.findById(id);
    }

    public void delete(CrudModel crudModel) {
        crudRepository.delete(crudModel);
    }
}
