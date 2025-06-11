package com.RESTapp.restapp.entity;

import jakarta.persistence.*;

//Java POJO class
@Entity
@Table(name="user")
public class User {

    // define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;


}
