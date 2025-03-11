package com.amstech.dairy.management.system.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;

/**
 * The persistent class for the subscription_history database table.
 */
@Entity
@Table(name = "subscription_history")
@NamedQuery(name = "SubscriptionHistory.findAll", query = "SELECT s FROM SubscriptionHistory s")
public class SubscriptionHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = true)
    private Date endDate;

     @ManyToOne
    @JoinColumn(name = "subscription_id", nullable = false) 
    private Subscription subscription;

     @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private UserRole userRole;

    public SubscriptionHistory() {
    }

    // Getters and Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Subscription getSubscription() {
        return this.subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public UserRole getUserRole() {
        return this.userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

   
}
