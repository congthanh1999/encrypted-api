package com.peter.encrypted_api.secured_hash;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "hashed_data")
public class SecuredHash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(10)")
    private String algorithm;
    @Column(columnDefinition = "TEXT")
    private String content;

    public SecuredHash() {
    }

    public SecuredHash(Long id, String algorithm, String content) {
        this.id = id;
        this.algorithm = algorithm;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String hashDataContent() {
        try {
            MessageDigest md = MessageDigest.getInstance(this.algorithm);

            byte[] messageDigest = md.digest(this.content.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashText = no.toString(16);

            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }

            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}
