package com.peter.encrypted_api.signature_verification;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JsonViewUtils;

@RestController
@RequestMapping("/api/v1")
public class SignatureVerificationController {
    private final SignatureVerificationService signatureVerificationService;

    public SignatureVerificationController(SignatureVerificationService signatureVerificationService) {
        this.signatureVerificationService = signatureVerificationService;
    }

    @JsonView(JsonViewUtils.Public.class)
    @PostMapping("/verify-signature")
    public ResponseEntity<Object> verifySignature (@RequestBody SignatureVerification signatureVerification) throws Exception {
        return signatureVerificationService.compare(signatureVerification);
    }
}
