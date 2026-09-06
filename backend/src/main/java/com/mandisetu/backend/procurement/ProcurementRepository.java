package com.mandisetu.backend.procurement;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProcurementRepository
        extends JpaRepository<Procurement, UUID> {
}
