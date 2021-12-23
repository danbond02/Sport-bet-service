package edu.kpi.iasa.mmsa.SportBetApp.model;

import javax.persistence.*;

@Entity
@Table(name="game_team")
public class GameTeamRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="team_id")
    private Long teamId;

    @Column(name="game_id")
    private Long gameId;

    @Column(name="scored_goals")
    private Integer scoredGoals;

    @Column(name="general_bet_ammount")
    private Float generalBetAmount;

    public GameTeamRelation() {}

    public GameTeamRelation(Long teamId, Long gameId, Integer scoredGoals, Float generalBetAmount) {
        this.teamId = teamId;
        this.gameId = gameId;
        this.scoredGoals = scoredGoals;
        this.generalBetAmount = generalBetAmount;
    }

    public Long getId() {
        return id;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Integer getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(Integer scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public Float getGeneralBetAmount() {
        return generalBetAmount;
    }

    public void setGeneralBetAmount(Float generalBetAmount) {
        this.generalBetAmount = generalBetAmount;
    }
}
