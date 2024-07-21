package com.peter.encrypted_api.secured_hash;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecuredHashService {
    private final SecuredHashRepository securedHashRepository;

    @Autowired
    public SecuredHashService(SecuredHashRepository securedHashRepository) {
        this.securedHashRepository = securedHashRepository;
    }

    @Transactional
    public ResponseEntity<Object> saveSecuredHash(SecuredHash securedHash) {
        securedHashRepository.save(securedHash);
        return new ResponseEntity<>(securedHash, HttpStatus.CREATED);
    }

    public ResponseEntity<List<SecuredHash>> findAllHashedData() {
        return new ResponseEntity<>(securedHashRepository.findAll(), HttpStatus.OK);
    }
}