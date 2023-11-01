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
public class Agency implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String details;
//    private Long owner;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @OneToMany
    private List<Bus> busList;

    @OneToMany
    private List<Trip> tripList;

}
