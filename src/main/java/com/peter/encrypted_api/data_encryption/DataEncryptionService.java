package com.peter.encrypted_api.data_encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import utils.Base64Utils;

import java.util.List;

@Service
public class DataEncryptionService {
    private final DataEncryptionRepository dataEncryptionRepository;

    @Autowired
    public DataEncryptionService(DataEncryptionRepository dataEncryptionRepository) {
        this.dataEncryptionRepository = dataEncryptionRepository;
    }

    public ResponseEntity<List<DataEncryption>> findAll() {
        return new ResponseEntity<>(dataEncryptionRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> saveEncryptedData(DataEncryption dataEncryption) {
        dataEncryption.setContent(dataEncryption.encryptDataContent());
        dataEncryptionRepository.save(dataEncryption);
        return new ResponseEntity<>(dataEncryption, HttpStatus.CREATED);
    }

    public ResponseEntity<Object> editEncryptedData(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        DataEncryption existingDataEncryption = dataEncryptionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.valueOf(id)));

        if (Base64Utils.isBase64Encoded(existingDataEncryption.getContent())) { // Successful decoding, content was Base64 encoded
            existingDataEncryption.setContent(existingDataEncryption.decryptDataContent());
        } else {
            existingDataEncryption.setContent(existingDataEncryption.encryptDataContent());
        }

        return new ResponseEntity<>(dataEncryptionRepository.save(existingDataEncryption), HttpStatus.OK);
    }

    public ResponseEntity<Object> removeDataEncryption(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        dataEncryptionRepository.deleteById(id);

        return new ResponseEntity<>("Deleted encrypted data with id " + id, HttpStatus.OK);
    }

    public ResponseEntity<Object> saveDecryptedData(DataEncryption dataEncryption) {
        dataEncryptionRepository.save(dataEncryption);
        return new ResponseEntity<>(dataEncryption, HttpStatus.CREATED);
    }
}
