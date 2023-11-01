package com.banksultra.finalProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(schema = "FINAL")
public class TripSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tripDate;
    private Long availableSeats;

    @OneToOne
    @JoinColumn(name = "tripDetail")
    private Trip tripDetail;    //join trip

    private String ticketsSold;

//    @OneToMany
////    @JoinColumn(name = "ticketsSold")
//    private List<Ticket> ticketsSold;   //join ticket
}
