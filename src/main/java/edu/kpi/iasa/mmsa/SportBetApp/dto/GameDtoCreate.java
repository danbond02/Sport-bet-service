package edu.kpi.iasa.mmsa.SportBetApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDtoCreate {

    private String place;
    private String type;
    private Date date;
    private Time time;
    private Long team1Id;
    private Long team2Id;
    private Long teamOldId;
    private Long teamNewId;
}
