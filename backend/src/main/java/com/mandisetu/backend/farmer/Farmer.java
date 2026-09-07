package com.mandisetu.backend.farmer;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "app_users")
public class Farmer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "mobile_number", nullable = false, unique = true)
    private String mobileNumber;

    private String village;
    private String district;
    private String state;

    @Column(nullable = false)
    private String role = "FARMER";

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Farmer() {
    }

    public UUID getId() { return id; }
    public String getFullName() { return fullName; }
    public String getMobileNumber() { return mobileNumber; }
    public String getVillage() { return village; }
    public String getDistrict() { return district; }
    public String getState() { return state; }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }
    public void setVillage(String village) { this.village = village; }
    public void setDistrict(String district) { this.district = district; }
    public void setState(String state) { this.state = state; }
}
