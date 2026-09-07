package com.mandisetu.backend.rate;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CropRateRepository extends JpaRepository<CropRate, UUID> {
    Optional<CropRate> findFirstByCropNameIgnoreCaseAndActiveTrue(String cropName);
}