package com.peter.encrypted_api.digital_signature;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import utils.DigitalSignatureUtils;
import utils.JsonViewUtils;
import utils.RSAUtils;

@Entity
@Table(name = "digital_signature")
public class DigitalSignature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(JsonViewUtils.Public.class)
    private Long id;

    @Transient
    @JsonView(JsonViewUtils.Internal.class)
    @Column(columnDefinition = "TEXT")
    private String content;

    @JsonView(JsonViewUtils.Public.class)
    @Column(columnDefinition = "TEXT")
    private String signature;

    public DigitalSignature() {
        RSAUtils.initFromString();
    }

    public DigitalSignature(Long id, String content, String signature) {
        this.id = id;
        this.content = content;
        this.signature = signature;
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

    public String signData() throws Exception {
        return RSAUtils.signData(this.content);
    }
}
