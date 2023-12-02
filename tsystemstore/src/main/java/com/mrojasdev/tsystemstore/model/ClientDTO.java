package com.mrojasdev.tsystemstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ClientDTO implements Serializable {

    private String firstname;
    private String surname;
    private LocalDate birthdate;
    private String email;

}
