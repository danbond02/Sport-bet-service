package edu.kpi.iasa.mmsa.SportBetApp.model;

import javax.persistence.*;

@Entity
@Table(name="higherlowerbet")
public class HigherLowerBet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="higher_lower")
    private String higherLower;

    @Column(name="bet_id")
    private Long betId;

    public HigherLowerBet(String higherLower, Long betId) {
        this.higherLower = higherLower;
        this.betId = betId;
    }

    public Long getId() {
        return id;
    }

    public String getHigherLower() {
        return higherLower;
    }

    public void setHigherLower(String higherLower) {
        this.higherLower = higherLower;
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }
}
