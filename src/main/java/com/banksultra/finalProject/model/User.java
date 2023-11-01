package com.banksultra.finalProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "email",length = 100)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "FINAL",
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    @JsonIgnore
    private List<Role> role;

//    @OneToMany
//    private List<Agency> agencies;

}
