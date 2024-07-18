package com.peter.encrypted_api.digital_signature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

@Service
public class DigitalSignatureService {
    private final DigitalSignatureRepository digitalSignatureRepository;

    @Autowired
    public DigitalSignatureService(DigitalSignatureRepository digitalSignatureRepository) {
        this.digitalSignatureRepository = digitalSignatureRepository;
    }

    public ResponseEntity<Object> newSignature(DigitalSignature digitalSignature) throws Exception {
        digitalSignature.setSignature(digitalSignature.signData());
        digitalSignatureRepository.save(digitalSignature);
        return new ResponseEntity<>(digitalSignature, HttpStatus.CREATED);
    }
}
