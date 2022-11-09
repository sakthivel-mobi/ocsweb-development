package com.mobi.ocs.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "stageMovement")
public class StageMovement {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int orderId;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private String fromStage;

    private String toStage;

    private String userName;

    public StageMovement(){

    }

    public StageMovement(int orderId, Date createdAt, String fromStage, String toStage, String userName) {
        this.orderId = orderId;
        this.createdAt = createdAt;
        this.fromStage = fromStage;
        this.toStage = toStage;
        this.userName = userName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getFromStage() {
        return fromStage;
    }

    public void setFromStage(String fromStage) {
        this.fromStage = fromStage;
    }

    public String getToStage() {
        return toStage;
    }

    public void setToStage(String toStage) {
        this.toStage = toStage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "StageMovement [createdAt=" + createdAt + ", fromStage=" + fromStage + ", id=" + id + ", orderId="
                + orderId + ", toStage=" + toStage + ", userName=" + userName + "]";
    }

    

    
    
}
