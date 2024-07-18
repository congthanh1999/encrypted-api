package com.peter.encrypted_api.data_encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        dataEncryption.setContent(dataEncryption.encryptDataContent());
        return dataEncryptionService.saveEncryptedData(dataEncryption);
    }

    @PostMapping("/decrypted_data")
    public ResponseEntity<Object> createDataDecryption(@RequestBody DataEncryption dataEncryption) {
        dataEncryption.setContent(dataEncryption.decryptDataContent());
        return dataEncryptionService.saveDecryptedData(dataEncryption);
    }
}
