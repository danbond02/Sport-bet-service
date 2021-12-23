package edu.kpi.iasa.mmsa.SportBetApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {

    private Long id;
    private String place;
    private String type;
    private Date date;
    private Time time;
    private String team1Name;
    private Float team1Coefficient;
    private String team2Name;
    private Float team2Coefficient;
    private Float drawCoefficient;
    private Long total;
    private Float higherCoefficient;
    private Float lowerCoefficient;
}
