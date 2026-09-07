package com.mandisetu.backend.procurement;

import com.mandisetu.backend.center.ProcurementCenter;
import com.mandisetu.backend.center.ProcurementCenterRepository;
import com.mandisetu.backend.farmer.Farmer;
import com.mandisetu.backend.farmer.FarmerRepository;
import com.mandisetu.backend.rate.CropRate;
import com.mandisetu.backend.rate.CropRateRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/procurements")
@CrossOrigin
public class ProcurementController {

    private final ProcurementRepository procurementRepository;
    private final FarmerRepository farmerRepository;
    private final ProcurementCenterRepository centerRepository;
    private final CropRateRepository cropRateRepository;

    public ProcurementController(
            ProcurementRepository procurementRepository,
            FarmerRepository farmerRepository,
            ProcurementCenterRepository centerRepository,
            CropRateRepository cropRateRepository) {
        this.procurementRepository = procurementRepository;
        this.farmerRepository = farmerRepository;
        this.centerRepository = centerRepository;
        this.cropRateRepository = cropRateRepository;
    }

    @PostMapping
    public ResponseEntity<?> createProcurement(@RequestBody Map<String, String> request) {
        Farmer farmer = farmerRepository
                .findById(UUID.fromString(request.get("farmerId")))
                .orElse(null);

        ProcurementCenter center = centerRepository
                .findById(UUID.fromString(request.get("centerId")))
                .orElse(null);

        CropRate cropRate = cropRateRepository
                .findFirstByCropNameIgnoreCaseAndActiveTrue(request.get("cropName"))
                .orElse(null);

        if (farmer == null || center == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Farmer or centre was not found."));
        }

        if (cropRate == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "No active official MSP rate exists for this crop."));
        }

        Procurement procurement = new Procurement();
        procurement.setFarmer(farmer);
        procurement.setCenter(center);
        procurement.setCropName(cropRate.getCropName());
        procurement.setQuantityKg(new BigDecimal(request.get("quantityKg")));
        procurement.setRatePerKg(cropRate.getRatePerKg());
        procurement.setQualityStatus(request.getOrDefault("qualityStatus", "APPROVED"));
        procurement.setProcurementStatus("APPROVED");

        Procurement saved = procurementRepository.save(procurement);

        return ResponseEntity.ok(Map.of(
                "message", "Procurement created using official MSP rate.",
                "procurementId", saved.getId().toString(),
                "crop", cropRate.getCropName(),
                "season", cropRate.getSeason(),
                "mspPerQuintal", cropRate.getMspPerQuintal(),
                "ratePerKg", cropRate.getRatePerKg(),
                "quantityKg", saved.getQuantityKg()
        ));
    }
}