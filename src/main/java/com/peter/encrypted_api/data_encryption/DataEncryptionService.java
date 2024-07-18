package com.peter.encrypted_api.data_encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DataEncryptionService {
    private final DataEncryptionRepository dataEncryptionRepository;

    @Autowired
    public DataEncryptionService(DataEncryptionRepository dataEncryptionRepository) {
        this.dataEncryptionRepository = dataEncryptionRepository;
    }

    public ResponseEntity<Object> saveEncryptedData(DataEncryption dataEncryption) {
        dataEncryptionRepository.save(dataEncryption);
        return new ResponseEntity<>(dataEncryption, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> saveDecryptedData(DataEncryption dataEncryption) {
        dataEncryptionRepository.save(dataEncryption);
        return new ResponseEntity<>(dataEncryption, HttpStatus.CREATED);
    }
}
