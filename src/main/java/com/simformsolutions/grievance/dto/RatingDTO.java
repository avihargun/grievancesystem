package com.simformsolutions.grievance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {

    private long id;
    @Min(0)
    @Max(10)
    private int rate;
    private String feedback;
}
