package com.integrator.service.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "audit_events")
public class AuditEventsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_Id")
    private Integer audit_Id;

    @Column(name = "api_Name")
    private String api_Name;

    //    @Column(name = "Param_Value")
//    @ElementCollection
//    @Column(name = "Param_Value")
//    @ElementCollection(targetClass=String.class)
    @Column(name = "Param_Value")
    private String paramsWithValue;

    @Column(name = "date_Time")
    private Instant dateTime;

    public Integer getAudit_Id() {
        return audit_Id;
    }

    public void setAudit_Id(Integer audit_Id) {
        this.audit_Id = audit_Id;
    }

    public String getApi_Name() {
        return api_Name;
    }

    public void setApi_Name(String api_Name) {
        this.api_Name = api_Name;
    }

    public String getParamsWithValue() {
        return paramsWithValue;
    }

    public void setParamsWithValue(String paramsWithValue) {
        this.paramsWithValue = paramsWithValue;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }
}
