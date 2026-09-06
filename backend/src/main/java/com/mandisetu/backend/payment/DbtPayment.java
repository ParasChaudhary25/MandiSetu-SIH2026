package com.mandisetu.backend.payment;

import com.mandisetu.backend.farmer.Farmer;
import com.mandisetu.backend.procurement.Procurement;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "dbt_payments")
public class DbtPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

    @ManyToOne
    @JoinColumn(name = "procurement_id")
    private Procurement procurement;

    private BigDecimal amount;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "payment_status")
    private String paymentStatus = "PROCESSING";

    @Column(name = "created_at")
    private OffsetDateTime createdAt = OffsetDateTime.now();

    public DbtPayment() {}

    public UUID getId() { return id; }
    public BigDecimal getAmount() { return amount; }
    public String getTransactionId() { return transactionId; }
    public String getPaymentStatus() { return paymentStatus; }

    public void setFarmer(Farmer farmer) { this.farmer = farmer; }
    public void setProcurement(Procurement procurement) { this.procurement = procurement; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}