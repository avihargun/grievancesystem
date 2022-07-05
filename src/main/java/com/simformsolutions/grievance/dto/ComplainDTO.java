package com.simformsolutions.grievance.dto;

import com.simformsolutions.grievance.dto.enums.Status;
import com.simformsolutions.grievance.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplainDTO {

    private long complainId;
    private String title;
    private String address;
    private String ward;
    private String description;
    private String photo;
    private long categoryId;
    private Status status;
    private String category;
    private Rating rating;


}
