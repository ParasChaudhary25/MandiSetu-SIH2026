package com.mandisetu.backend.center;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "procurement_centers")
public class ProcurementCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "center_name", nullable = false)
    private String centerName;

    private String address;
    private String district;
    private boolean active = true;

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public ProcurementCenter() {}

    public UUID getId() { return id; }
    public String getCenterName() { return centerName; }
    public String getAddress() { return address; }
    public String getDistrict() { return district; }
    public boolean isActive() { return active; }

    public void setCenterName(String centerName) { this.centerName = centerName; }
    public void setAddress(String address) { this.address = address; }
    public void setDistrict(String district) { this.district = district; }
    public void setActive(boolean active) { this.active = active; }
}
