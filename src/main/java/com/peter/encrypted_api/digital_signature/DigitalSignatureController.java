package com.peter.encrypted_api.digital_signature;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.JsonViewUtils;

@RestController
@RequestMapping("/api/v1")
public class DigitalSignatureController {
    private final DigitalSignatureService digitalSignatureService;

    public DigitalSignatureController(DigitalSignatureService digitalSignatureService) {
        this.digitalSignatureService = digitalSignatureService;
    }

    @JsonView(JsonViewUtils.Public.class)
    @PostMapping("/sign-data")
    public ResponseEntity<Object> createSignature(@RequestBody DigitalSignature digitalSignature) throws Exception {
        return digitalSignatureService.newSignature(digitalSignature);
    }
}
