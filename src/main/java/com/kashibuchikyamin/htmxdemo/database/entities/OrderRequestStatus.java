package com.kashibuchikyamin.htmxdemo.database.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderRequestStatus {
    private String ulid;
    private String orderNumber;
    private String branchNumber;
    private String receptionNumber;
    private String correctionNumber;
    private String projectName;
    private String customerName;
    private LocalDate submissionDate;
    private LocalDate deliveryDate;
    private String status;
    private String createUser;
    private LocalDateTime createDatetime;
    private String updateUser;
    private LocalDateTime updateDatetime;
    
    public String getOrderNumberForView() {
    	return orderNumber + "-" + branchNumber;
    }
    public String getReceptionNumberForView() {
    	return receptionNumber + "-" + correctionNumber;
    }
}
