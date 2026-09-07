package com.mandisetu.backend.notification;

import com.mandisetu.backend.farmer.Farmer;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

    private String channel;
    private String message;

    @Column(name = "notification_status")
    private String notificationStatus = "SENT";

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public Notification() {}

    public UUID getId() { return id; }
    public String getChannel() { return channel; }
    public String getMessage() { return message; }
    public String getNotificationStatus() { return notificationStatus; }

    public void setFarmer(Farmer farmer) { this.farmer = farmer; }
    public void setChannel(String channel) { this.channel = channel; }
    public void setMessage(String message) { this.message = message; }
}
