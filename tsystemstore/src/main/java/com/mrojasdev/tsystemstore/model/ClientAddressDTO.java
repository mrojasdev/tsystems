package com.mrojasdev.tsystemstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientAddressDTO implements Serializable {
    long id;
    private String country;
    private String city;
    private int postalCode;
    private String street;
    private String home;
    private String apartment;

}
