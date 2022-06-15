package com.simformsolutions.grievance.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String password;

    @OneToMany(targetEntity = Complain.class , cascade = CascadeType.ALL)
    @JoinColumn(name="complainerId",referencedColumnName = "userId")
    private List<Complain> complains;

}
