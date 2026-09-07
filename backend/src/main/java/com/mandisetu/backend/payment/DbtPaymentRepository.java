package com.mandisetu.backend.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface DbtPaymentRepository extends JpaRepository<DbtPayment, UUID> {
    List<DbtPayment> findByFarmer_IdOrderByCreatedAtDesc(UUID farmerId);
}