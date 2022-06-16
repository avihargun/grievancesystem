package com.simformsolutions.grievance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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
    private long categoryId;
    private int status;

    @JsonIgnore
    @Transient
    private String category;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ratingId")
    private Rating rating;
}
