package com.mandisetu.backend.rate;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "crop_rates")
public class CropRate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "crop_name")
    private String cropName;

    private String season;

    @Column(name = "msp_per_quintal")
    private BigDecimal mspPerQuintal;

    @Column(name = "rate_per_kg")
    private BigDecimal ratePerKg;

    private boolean active;

    public CropRate() {}

    public String getCropName() { return cropName; }
    public String getSeason() { return season; }
    public BigDecimal getMspPerQuintal() { return mspPerQuintal; }
    public BigDecimal getRatePerKg() { return ratePerKg; }
    public boolean isActive() { return active; }
}
