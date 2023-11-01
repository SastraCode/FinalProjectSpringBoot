package com.banksultra.finalProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(schema = "FINAL")
public class Bus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private Long capacity;
    private String make;
//    private int agency;

    @ManyToOne
    @JoinColumn(name = "agency")
    private Agency agency;

    @OneToMany
    private List<Trip> tripList;
}
