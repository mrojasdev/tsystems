package com.mrojasdev.tsystemstore.model;

import jakarta.persistence.*;

import java.io.Serializable;

public class ClientAddressDTO implements Serializable {
    private String country;
    private String city;
    private int postalCode;
    private String street;
    private String home;
    private String apartment;

}
