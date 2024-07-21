package com.peter.encrypted_api.secured_hash;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/secured_hash")
public class SecuredHashController {
    private final SecuredHashService securedHashService;

    @Autowired
    public SecuredHashController(SecuredHashService securedHashService) {
        this.securedHashService = securedHashService;
    }

    @GetMapping
    public ResponseEntity<List<SecuredHash>> fetchAllHashedData(){
        return securedHashService.findAllHashedData();
    }

    @PostMapping
    public ResponseEntity<Object> createSecuredHash(@RequestBody SecuredHash securedHash) {
        securedHash.setContent(securedHash.hashDataContent());
        return securedHashService.saveSecuredHash(securedHash);
    }
}
