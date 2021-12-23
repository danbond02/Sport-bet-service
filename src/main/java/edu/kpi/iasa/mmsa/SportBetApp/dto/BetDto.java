package edu.kpi.iasa.mmsa.SportBetApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BetDto {

    private Float betAm;
    private String type;
    private Long AccId;
    private Long GameId;
    private String HigherLower;
    private Long TeamBetId;
}

