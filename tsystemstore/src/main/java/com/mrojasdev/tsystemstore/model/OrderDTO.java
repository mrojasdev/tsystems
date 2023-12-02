package com.mrojasdev.tsystemstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDTO implements Serializable {

    private ClientDTO client;
    private ClientAddressDTO clientAddress;
    private String paymentMethod;
    private String deliveryMethod;
    private String paymentStatus;
    private String orderStatus;
    private LocalDate orderDate;

}
