package com.banksultra.finalProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(schema = "FINAL")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long fare;
    private String journeyTime;

    @ManyToOne
    @JoinColumn(name = "sourceStop", referencedColumnName = "id")
    private Stop sourceStop;  //join stop

    @ManyToOne
    @JoinColumn(name = "destStop", referencedColumnName = "id")
    private Stop destStop;    //join stop

    @ManyToOne
    @JoinColumn(name = "bus")
    private Bus bus;         //join bus

    @ManyToOne
    @JoinColumn(name = "agency")
    private Agency agency;      //join agency

}
