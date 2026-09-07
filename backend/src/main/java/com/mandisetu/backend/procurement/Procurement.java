package com.mandisetu.backend.procurement;

import com.mandisetu.backend.center.ProcurementCenter;
import com.mandisetu.backend.farmer.Farmer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "procurements")
public class Procurement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private ProcurementCenter center;

    @Column(name = "crop_name", nullable = false)
    private String cropName;

    @Column(name = "quantity_kg", nullable = false)
    private BigDecimal quantityKg;

    @Column(name = "rate_per_kg")
    private BigDecimal ratePerKg;

    @Column(name = "quality_status")
    private String qualityStatus = "PENDING";

    @Column(name = "procurement_status")
    private String procurementStatus = "PENDING";

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Procurement() {}

    public UUID getId() { return id; }
    public String getCropName() { return cropName; }
    public BigDecimal getQuantityKg() { return quantityKg; }
    public BigDecimal getRatePerKg() { return ratePerKg; }
    public String getQualityStatus() { return qualityStatus; }
    public String getProcurementStatus() { return procurementStatus; }

    public void setFarmer(Farmer farmer) { this.farmer = farmer; }
    public void setCenter(ProcurementCenter center) { this.center = center; }
    public void setCropName(String cropName) { this.cropName = cropName; }
    public void setQuantityKg(BigDecimal quantityKg) { this.quantityKg = quantityKg; }
    public void setRatePerKg(BigDecimal ratePerKg) { this.ratePerKg = ratePerKg; }
    public void setQualityStatus(String qualityStatus) { this.qualityStatus = qualityStatus; }
    public void setProcurementStatus(String procurementStatus) { this.procurementStatus = procurementStatus; }
    public Farmer getFarmer() { return farmer; }
}