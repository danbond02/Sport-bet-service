package edu.kpi.iasa.mmsa.SportBetApp.model;

import javax.persistence.*;

@Entity
@Table(name="game_higher_lower")
public class GameHigherLowerRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="higher_ammount")
    private Float higherAmmount;

    @Column(name="lover_ammount")
    private Float lowerAmmount;

    @Column(name="game_id")
    private Long gameId;

    public GameHigherLowerRelation() {}

    public GameHigherLowerRelation(Float higherAmmount, Float lowerAmmount, Long gameId) {
        this.higherAmmount = higherAmmount;
        this.lowerAmmount = lowerAmmount;
        this.gameId = gameId;
    }

    public Long getId() {
        return id;
    }

    public Float getHigherAmmount() {
        return higherAmmount;
    }

    public void setHigherAmmount(Float higherAmmount) {
        this.higherAmmount = higherAmmount;
    }

    public Float getLowerAmmount() {
        return lowerAmmount;
    }

    public void setLowerAmmount(Float lowerAmmount) {
        this.lowerAmmount = lowerAmmount;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
