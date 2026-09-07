package com.mandisetu.backend.qr;

import com.mandisetu.backend.center.ProcurementCenter;
import com.mandisetu.backend.center.ProcurementCenterRepository;
import com.mandisetu.backend.farmer.Farmer;
import com.mandisetu.backend.farmer.FarmerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/qr")
@CrossOrigin
public class QrController {

    private final QrVerificationRepository qrRepository;
    private final FarmerRepository farmerRepository;
    private final ProcurementCenterRepository centerRepository;

    public QrController(QrVerificationRepository qrRepository,
                        FarmerRepository farmerRepository,
                        ProcurementCenterRepository centerRepository) {
        this.qrRepository = qrRepository;
        this.farmerRepository = farmerRepository;
        this.centerRepository = centerRepository;
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyFarmer(@RequestBody Map<String, String> request) {
        Farmer farmer = farmerRepository
                .findById(UUID.fromString(request.get("farmerId")))
                .orElse(null);

        ProcurementCenter center = centerRepository
                .findById(UUID.fromString(request.get("centerId")))
                .orElse(null);

        if (farmer == null || center == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Farmer or centre was not found."));
        }

        QrVerification verification = new QrVerification();
        verification.setFarmer(farmer);
        verification.setCenter(center);

        QrVerification saved = qrRepository.save(verification);

        return ResponseEntity.ok(Map.of(
                "message", "QR verification successful.",
                "verificationId", saved.getId().toString(),
                "verifiedAt", saved.getVerifiedAt().toString()
        ));
    }
}