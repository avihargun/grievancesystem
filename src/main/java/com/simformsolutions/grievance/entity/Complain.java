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
public class Complain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long complainId;
    private String title;
    private String address;
    private String ward;
    private String description;
    private String photo;
    private String categoryId;
    private int status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ratingId")
    private Rating rating;
}
