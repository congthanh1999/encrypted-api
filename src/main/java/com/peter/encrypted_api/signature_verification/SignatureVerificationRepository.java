package com.peter.encrypted_api.signature_verification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureVerificationRepository extends JpaRepository<SignatureVerification, Long> {
}
