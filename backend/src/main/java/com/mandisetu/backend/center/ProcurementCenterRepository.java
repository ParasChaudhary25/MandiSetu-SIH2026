package com.mandisetu.backend.center;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProcurementCenterRepository
        extends JpaRepository<ProcurementCenter, UUID> {
}