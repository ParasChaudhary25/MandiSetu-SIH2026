package com.mandisetu.backend.qr;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface QrVerificationRepository
        extends JpaRepository<QrVerification, UUID> {
}