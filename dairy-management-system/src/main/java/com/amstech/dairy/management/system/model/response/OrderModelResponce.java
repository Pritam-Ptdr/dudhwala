package com.amstech.dairy.management.system.model.response;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;


@Data
public class OrderModelResponce {
    private int id;
    private int packetSize;
    private BigDecimal price;  // Changed from DecimalFormat to BigDecimal
    private String productName;
    private int productId;
    private String delivarySchedule;
    private int quantity;
    private Date diliveryDate;
    private String userName;
    private int userId;

    
   
}
