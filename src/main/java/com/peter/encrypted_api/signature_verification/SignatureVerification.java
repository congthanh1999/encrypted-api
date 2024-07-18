package com.peter.encrypted_api.signature_verification;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import utils.JsonViewUtils;
import utils.RSAUtils;

@Entity
@Table(name = "signature_verification")
public class SignatureVerification {
    @JsonView(JsonViewUtils.Public.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(JsonViewUtils.Internal.class)
    @Column(columnDefinition = "TEXT")
    private String content;

    @JsonView(JsonViewUtils.Internal.class)
    @Column(columnDefinition = "TEXT")
    private String signature;

    @JsonView(JsonViewUtils.Public.class)
    @Column(columnDefinition = "BOOLEAN")
    private Boolean result;

    public SignatureVerification(Long id, String content, String signature, Boolean result) {
        this.id = id;
        this.content = content;
        this.signature = signature;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Boolean verifySignature() throws Exception {
        return RSAUtils.verifySignature(this.signature, this.content);
    }
}
