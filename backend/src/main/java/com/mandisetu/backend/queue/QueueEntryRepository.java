package com.mandisetu.backend.queue;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface QueueEntryRepository extends JpaRepository<QueueEntry, UUID> {
    List<QueueEntry> findByFarmer_IdOrderByCreatedAtDesc(UUID farmerId);
    Optional<QueueEntry> findTopByCenter_IdOrderByTokenNumberDesc(UUID centerId);
}