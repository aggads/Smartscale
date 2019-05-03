package com.example.smartscale.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Table
@Entity
public class Plates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="plate")
    private String plate;

    @Column(name="type")
    private String type;

    @Column(name="quantity")
    private String quantity;

    @Column(name="dateOrder")
    private String dateOrder;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "client")
    private Clients client;

    public Plates() {

    }
}