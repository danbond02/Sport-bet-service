package edu.kpi.iasa.mmsa.SportBetApp.model;

import javax.persistence.*;

@Entity
@Table(name="Bet")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="bet_ammount")
    private Float betAm;

    @Column(name="type")
    private String type;

    @Column(name="Account_id")
    private Long accId;

    @Column(name="Game_id")
    private Long gameId;

    public Bet(){};

    public Bet(Float betAm, String type, Long accId, Long gameId) {
        this.betAm = betAm;
        this.type=type;
        this.gameId=gameId;
        this.accId=accId;
    }

    public Long getId() {
        return id;
    }

    public Float getBetAm() {
        return betAm;
    }

    public void setBetAm(Float betAm) {
        this.betAm = betAm;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getAccId() {
        return accId;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
