package com.banksultra.finalProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(schema = "FINAL")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long seatNumber;
    private boolean cancelLable;
    private String journeyDate;
//    private String passenger;     //join user

    @OneToOne
    @JoinColumn(name = "passenger")
    private User passenger;

//    private String tripSchedule;  //join tripschedule

    @ManyToOne
    @JoinColumn(name = "tripSchedule")
    private TripSchedule tripSchedule;
}
