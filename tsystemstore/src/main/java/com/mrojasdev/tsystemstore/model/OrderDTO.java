package com.mrojasdev.tsystemstore.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderDTO implements Serializable {
    private Client client;
    private ClientAddress clientAddress;
    private String paymentMethod;
    private String deliveryMethod;
    private String paymentStatus;
    private String orderStatus;
    private LocalDate orderDate;

}
