package com.peter.encrypted_api.data_encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DataEncryptionController {
    private final DataEncryptionService dataEncryptionService;

    @Autowired
    public DataEncryptionController(DataEncryptionService dataEncryptionService) {
        this.dataEncryptionService = dataEncryptionService;
    }

    @PostMapping("/encrypted_data")
    public ResponseEntity<Object> createDataEncryption(@RequestBody DataEncryption dataEncryption) {
        return dataEncryptionService.saveEncryptedData(dataEncryption);
    }

    @GetMapping("/encrypted_data")
    public ResponseEntity<List<DataEncryption>> fetchDataEncryption() {
        return dataEncryptionService.findAll();
    }

    @PatchMapping("/encrypted_data/{id}")
    public ResponseEntity<Object> updateDataEncryption(@PathVariable("id") Long id) {
        return dataEncryptionService.editEncryptedData(id);
    }

    @DeleteMapping("/encrypted_data/{id}")
    public ResponseEntity<Object> deleteDataEncryption(@PathVariable("id") Long id) {
        return dataEncryptionService.removeDataEncryption(id);
    }

    @PostMapping("/decrypted_data")
    public ResponseEntity<Object> createDataDecryption(@RequestBody DataEncryption dataEncryption) {
        dataEncryption.setContent(dataEncryption.decryptDataContent());
        return dataEncryptionService.saveDecryptedData(dataEncryption);
    }
}
