package com.mrojasdev.tsystemstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Client")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "surname")
    private String surname;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "passwd")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "user_role")
    private String role;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientAddress> addresses;

}
