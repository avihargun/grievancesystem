package com.simformsolutions.grievance.dto;

import com.simformsolutions.grievance.entity.Complain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long userId;
    private String name;
    private String address;
    private String contact;
    private String email;
    private String password;
    private List<Complain> complains;
}
