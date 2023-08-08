package com.api.crudoperations.controllers;

import com.api.crudoperations.dtos.CrudDtos;
import com.api.crudoperations.models.CrudModel;
import com.api.crudoperations.services.CrudService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class CrudController {

    final CrudService crudService;

    public CrudController(CrudService crudService) {
        this.crudService = crudService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid CrudDtos crudDtos) {

        if(crudService.existsByUserName(crudDtos.getUserName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: user is already in use!");
        }

        var crudModel = new CrudModel();
        BeanUtils.copyProperties(crudDtos, crudModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(crudService.save(crudModel));
    }

    @GetMapping
    public ResponseEntity<List<CrudModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(crudService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value = "id") UUID id) {
        Optional<CrudModel> crudModelOptional = crudService.findById(id);
        if(!crudModelOptional.isPresent()) {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("User not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(crudModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
        Optional<CrudModel> crudModelOptional = crudService.findById(id);
        if(!crudModelOptional.isPresent()) {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("User not found.");
        }

        crudService.delete(crudModelOptional.get());
        return ResponseEntity.status((HttpStatus.OK)).body("User deleted successifully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid CrudDtos crudDtos) {
        Optional<CrudModel> crudModelOptional = crudService.findById(id);
        if(!crudModelOptional.isPresent()) {
            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("User not found.");
        }
        var crudModel = new CrudModel();
        BeanUtils.copyProperties(crudDtos, crudModel);
        crudModel.setId(crudModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(crudService.save(crudModel));
    }


}
