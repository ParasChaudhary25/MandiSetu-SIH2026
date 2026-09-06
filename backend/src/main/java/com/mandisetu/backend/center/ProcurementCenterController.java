package com.mandisetu.backend.center;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
@CrossOrigin
public class ProcurementCenterController {

    private final ProcurementCenterRepository centerRepository;

    public ProcurementCenterController(ProcurementCenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @PostMapping
    public ResponseEntity<ProcurementCenter> addCenter(
            @RequestBody ProcurementCenter center) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(centerRepository.save(center));
    }

    @GetMapping
    public List<ProcurementCenter> getAllCenters() {
        return centerRepository.findAll();
    }
}
