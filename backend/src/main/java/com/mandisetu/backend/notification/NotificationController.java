package com.mandisetu.backend.notification;

import com.mandisetu.backend.farmer.Farmer;
import com.mandisetu.backend.farmer.FarmerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final FarmerRepository farmerRepository;

    public NotificationController(NotificationRepository notificationRepository,
                                  FarmerRepository farmerRepository) {
        this.notificationRepository = notificationRepository;
        this.farmerRepository = farmerRepository;
    }

    @PostMapping("/sms")
    public ResponseEntity<?> sendSms(@RequestBody Map<String, String> request) {
        Farmer farmer = farmerRepository
                .findById(UUID.fromString(request.get("farmerId")))
                .orElse(null);

        if (farmer == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Farmer was not found."));
        }

        Notification notification = new Notification();
        notification.setFarmer(farmer);
        notification.setChannel("SMS");
        notification.setMessage(request.get("message"));

        return ResponseEntity.ok(notificationRepository.save(notification));
    }
    @PostMapping("/ivr")
    public ResponseEntity<?> createIvrCall(@RequestBody Map<String, String> request) {
        Farmer farmer = farmerRepository
                .findById(UUID.fromString(request.get("farmerId")))
                .orElse(null);

        if (farmer == null) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Farmer was not found."));
        }

        Notification notification = new Notification();
        notification.setFarmer(farmer);
        notification.setChannel("IVR");
        notification.setMessage(request.get("message"));

        return ResponseEntity.ok(notificationRepository.save(notification));
    }
}