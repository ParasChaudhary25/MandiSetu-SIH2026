package com.mandisetu.backend.queue;

import com.mandisetu.backend.center.ProcurementCenter;
import com.mandisetu.backend.farmer.Farmer;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "queue_entries")
public class QueueEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "center_id", nullable = false)
    private ProcurementCenter center;

    @Column(name = "token_number", nullable = false)
    private Integer tokenNumber;

    @Column(name = "queue_status")
    private String queueStatus = "WAITING";

    @Column(name = "expected_time")
    private OffsetDateTime expectedTime;

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public QueueEntry() {}

    public UUID getId() { return id; }
    public Integer getTokenNumber() { return tokenNumber; }
    public String getQueueStatus() { return queueStatus; }
    public OffsetDateTime getExpectedTime() { return expectedTime; }

    public void setFarmer(Farmer farmer) { this.farmer = farmer; }
    public void setCenter(ProcurementCenter center) { this.center = center; }
    public void setTokenNumber(Integer tokenNumber) { this.tokenNumber = tokenNumber; }
    public void setExpectedTime(OffsetDateTime expectedTime) { this.expectedTime = expectedTime; }
}