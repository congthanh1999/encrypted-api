package com.peter.encrypted_api.signature_verification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignatureVerificationService {
    private final SignatureVerificationRepository signatureVerificationRepository;

    @Autowired
    public SignatureVerificationService(SignatureVerificationRepository signatureVerificationRepository) {
        this.signatureVerificationRepository = signatureVerificationRepository;
    }

    public ResponseEntity<Object> compare (SignatureVerification signatureVerification) throws Exception {
        signatureVerification.setResult(signatureVerification.verifySignature());
        signatureVerificationRepository.save(signatureVerification);
        return new ResponseEntity<>(signatureVerification, HttpStatus.CREATED);
    }
}
