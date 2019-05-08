package com.flinkexample.flinkapplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {
//    private Long positionId;
    private Integer tierAccountId;
    private Integer valuableitem_id;
    private Double startLong;
    private Double startShort;

    public void setTierAccountId(Integer tierAccountId) {
        this.tierAccountId = tierAccountId;
    }

    public void setValuableitem_id(Integer valuableitem_id) {
        this.valuableitem_id = valuableitem_id;
    }

    public void setStartLong(Double startLong) {
        this.startLong = startLong;
    }

    public void setStartShort(Double startShort) {
        this.startShort = startShort;
    }

    public void setCurrentLong(Double currentLong) {
        this.currentLong = currentLong;
    }

    public void setCurrentShort(Double currentShort) {
        this.currentShort = currentShort;
    }

    public void setBusinessdate(Timestamp businessdate) {
        this.businessdate = businessdate;
    }

    private Double currentLong;
    private Double currentShort;

    public Integer getTierAccountId() {
        return tierAccountId;
    }

    public Integer getValuableitem_id() {
        return valuableitem_id;
    }

    public Double getStartLong() {
        return startLong;
    }

    public Double getStartShort() {
        return startShort;
    }

    public Double getCurrentLong() {
        return currentLong;
    }

    public Double getCurrentShort() {
        return currentShort;
    }

    public Timestamp getBusinessdate() {
        return businessdate;
    }

    private Timestamp businessdate;

}
