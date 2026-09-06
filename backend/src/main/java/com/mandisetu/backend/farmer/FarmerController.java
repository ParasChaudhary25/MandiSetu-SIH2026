package com.mandisetu.backend.farmer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/farmers")
@CrossOrigin
public class FarmerController {

    private final FarmerRepository farmerRepository;

    public FarmerController(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    @PostMapping
    public ResponseEntity<?> registerFarmer(@RequestBody Farmer farmer) {
        if (farmerRepository.existsByMobileNumber(farmer.getMobileNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "This mobile number is already registered."));
        }

        Farmer savedFarmer = farmerRepository.save(farmer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFarmer);
    }

    @GetMapping
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }
}
