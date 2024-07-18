package com.peter.encrypted_api.data_encryption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import utils.RSAUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

@Entity
@Table(name = "encrypted_data")
public class DataEncryption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(columnDefinition = "VARCHAR(10)")
    private String algorithm;
    @Transient
    @JsonIgnore
    private String tripleDesKey = "012345678901234567890123";
    @Transient
    @JsonIgnore
    private String desKey = "12345678";
    @Transient
    @JsonIgnore
    private String key = "1234567812345678";
    @Transient
    @JsonIgnore
    private String desInitVector = "12345678";
    @Transient
    @JsonIgnore
    private String initVector = "1234567812345678";

    public DataEncryption(Long id, String content, String algorithm) throws NoSuchAlgorithmException {
        this.id = id;
        this.content = content;
        this.algorithm = algorithm;
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

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String encryptDataContent() {
        try {
            Cipher cipher;
            byte[] text;
            byte[] textEncrypted = new byte[0];
            switch (this.algorithm) {
                case "DES":
                    SecretKeySpec secretKeySpec = new SecretKeySpec(this.desKey.getBytes(), this.algorithm);
                    IvParameterSpec iv = new IvParameterSpec(this.desInitVector.getBytes());

                    cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
                    text = this.content.getBytes();
                    textEncrypted = cipher.doFinal(text);
                    break;
                case "TripleDES":
                    secretKeySpec = new SecretKeySpec(this.tripleDesKey.getBytes(StandardCharsets.UTF_8), this.algorithm);
                    iv = new IvParameterSpec(this.desInitVector.getBytes());

                    cipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
                    text = this.content.getBytes();
                    textEncrypted = cipher.doFinal(text);
                    break;
                case "AES":
                    secretKeySpec = new SecretKeySpec(this.key.getBytes(), this.algorithm);
                    iv = new IvParameterSpec(this.initVector.getBytes());

                    cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
                    text = this.content.getBytes(StandardCharsets.UTF_8);
                    textEncrypted = cipher.doFinal(text);
                    break;
                case "RSA":
                    RSAUtils.initFromString();
                    textEncrypted = RSAUtils.encrypt(this.content);
                    break;
            }

            return Base64.getEncoder().encodeToString(textEncrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String decryptDataContent() {
        try {
            Cipher cipher;
            byte[] text;
            byte[] textDecrypted = new byte[0];
            switch (this.algorithm) {
                case "DES":
                    SecretKeySpec secretKeySpec = new SecretKeySpec(this.desKey.getBytes(), this.algorithm);
                    IvParameterSpec iv = new IvParameterSpec(this.desInitVector.getBytes());

                    cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
                    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
                    text = Base64.getDecoder().decode(this.content);
                    textDecrypted = cipher.doFinal(text);
                    break;
                case "TripleDES":
                    secretKeySpec = new SecretKeySpec(this.tripleDesKey.getBytes(StandardCharsets.UTF_8), this.algorithm);
                    iv = new IvParameterSpec(this.desInitVector.getBytes());

                    cipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
                    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
                    text = Base64.getDecoder().decode(this.content);
                    textDecrypted = cipher.doFinal(text);
                    break;
                case "AES":
                    secretKeySpec = new SecretKeySpec(this.key.getBytes(), this.algorithm);
                    IvParameterSpec ivSpec = new IvParameterSpec(this.initVector.getBytes());

                    cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
                    text = Base64.getDecoder().decode(this.content);
                    textDecrypted = cipher.doFinal(text);
                    break;
                case "RSA":
                    RSAUtils.initFromString();
                    textDecrypted = RSAUtils.decrypt(this.content);
                    break;
            }

            return new String(textDecrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException | InvalidAlgorithmParameterException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
