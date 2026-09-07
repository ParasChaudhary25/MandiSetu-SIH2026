package com.mandisetu.backend.queue;

import com.mandisetu.backend.center.ProcurementCenter;
import com.mandisetu.backend.center.ProcurementCenterRepository;
import com.mandisetu.backend.farmer.Farmer;
import com.mandisetu.backend.farmer.FarmerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/queue")
@CrossOrigin
public class QueueController {
    @GetMapping("/farmer/{farmerId}")
    public List<QueueEntry> getFarmerQueue(@PathVariable UUID farmerId) {
        return queueRepository.findByFarmer_IdOrderByCreatedAtDesc(farmerId);
    }
    private final QueueEntryRepository queueRepository;
    private final FarmerRepository farmerRepository;
    private final ProcurementCenterRepository centerRepository;

    public QueueController(QueueEntryRepository queueRepository,
                           FarmerRepository farmerRepository,
                           ProcurementCenterRepository centerRepository) {
        this.queueRepository = queueRepository;
        this.farmerRepository = farmerRepository;
        this.centerRepository = centerRepository;
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinQueue(@RequestBody Map<String, String> request) {
        UUID farmerId = UUID.fromString(request.get("farmerId"));
        UUID centerId = UUID.fromString(request.get("centerId"));

        Farmer farmer = farmerRepository.findById(farmerId).orElse(null);
        ProcurementCenter center = centerRepository.findById(centerId).orElse(null);

        if (farmer == null || center == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Farmer or procurement centre was not found."));
        }

        int nextToken = queueRepository
                .findTopByCenter_IdOrderByTokenNumberDesc(centerId)
                .map(entry -> entry.getTokenNumber() + 1)
                .orElse(1);

        QueueEntry entry = new QueueEntry();
        entry.setFarmer(farmer);
        entry.setCenter(center);
        entry.setTokenNumber(nextToken);
        entry.setExpectedTime(OffsetDateTime.now().plusMinutes((long) (nextToken - 1) * 10));

        QueueEntry savedEntry = queueRepository.save(entry);

        return ResponseEntity.ok(Map.of(
                "message", "Queue token created successfully.",
                "tokenNumber", savedEntry.getTokenNumber(),
                "status", savedEntry.getQueueStatus(),
                "expectedTime", savedEntry.getExpectedTime().toString()
        ));
    }
}