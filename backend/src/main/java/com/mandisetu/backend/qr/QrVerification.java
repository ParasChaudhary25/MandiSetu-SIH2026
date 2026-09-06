package com.mandisetu.backend.qr;

import com.mandisetu.backend.center.ProcurementCenter;
import com.mandisetu.backend.farmer.Farmer;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "qr_verifications")
public class QrVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private ProcurementCenter center;

    @Column(name = "verified_at")
    private OffsetDateTime verifiedAt = OffsetDateTime.now();

    public QrVerification() {}

    public UUID getId() { return id; }
    public OffsetDateTime getVerifiedAt() { return verifiedAt; }

    public void setFarmer(Farmer farmer) { this.farmer = farmer; }
    public void setCenter(ProcurementCenter center) { this.center = center; }
}
