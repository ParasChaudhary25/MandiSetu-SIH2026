package com.mandisetu.backend.payment;

import com.mandisetu.backend.procurement.Procurement;
import com.mandisetu.backend.procurement.ProcurementRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin
public class DbtPaymentController {

    private final DbtPaymentRepository paymentRepository;
    private final ProcurementRepository procurementRepository;

    public DbtPaymentController(DbtPaymentRepository paymentRepository,
                                ProcurementRepository procurementRepository) {
        this.paymentRepository = paymentRepository;
        this.procurementRepository = procurementRepository;
    }
    @GetMapping("/farmer/{farmerId}")
    public List<DbtPayment> getFarmerPayments(@PathVariable UUID farmerId) {
        return paymentRepository.findByFarmer_IdOrderByCreatedAtDesc(farmerId);
    }

    @PatchMapping("/{paymentId}/status")
    public ResponseEntity<?> updatePaymentStatus(
            @PathVariable UUID paymentId,
            @RequestBody Map<String, String> request) {

        DbtPayment payment = paymentRepository.findById(paymentId).orElse(null);

        if (payment == null) {
            return ResponseEntity.notFound().build();
        }

        String status = request.get("paymentStatus");

        if (!List.of("PENDING", "PROCESSING", "PAID", "FAILED").contains(status)) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Invalid payment status."));
        }

        payment.setPaymentStatus(status);
        return ResponseEntity.ok(paymentRepository.save(payment));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPayment(@RequestBody Map<String, String> request) {
        Procurement procurement = procurementRepository
                .findById(UUID.fromString(request.get("procurementId")))
                .orElse(null);

        if (procurement == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Procurement record was not found."));
        }

        BigDecimal amount = procurement.getQuantityKg()
                .multiply(procurement.getRatePerKg());

        DbtPayment payment = new DbtPayment();
        payment.setFarmer(procurement.getFarmer());
        payment.setProcurement(procurement);
        payment.setAmount(amount);
        payment.setTransactionId("DBT-" + UUID.randomUUID().toString().substring(0, 8));

        DbtPayment saved = paymentRepository.save(payment);

        return ResponseEntity.ok(Map.of(
                "message", "DBT payment created successfully.",
                "amount", saved.getAmount(),
                "transactionId", saved.getTransactionId(),
                "paymentStatus", saved.getPaymentStatus()
        ));
    }
}