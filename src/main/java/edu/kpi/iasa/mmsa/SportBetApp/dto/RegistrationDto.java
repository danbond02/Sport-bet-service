package edu.kpi.iasa.mmsa.SportBetApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class RegistrationDto {
    private String username;
    private String password;
    private String email;
    private String status;
}
