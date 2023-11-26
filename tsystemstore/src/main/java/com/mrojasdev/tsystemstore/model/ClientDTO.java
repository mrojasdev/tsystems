package com.mrojasdev.tsystemstore.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClientDTO implements Serializable {

    private String firstname;
    private String surname;
    private LocalDate birthdate;
    private String email;
    private List<Order> orders;
    private List<ClientAddress> addresses;
}
