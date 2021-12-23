package edu.kpi.iasa.mmsa.SportBetApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDtoRequest {

    private String team1Name;
    private String team2Name;
    private Integer team1ScoredGoals;
    private Integer team2ScoredGoals;
}
