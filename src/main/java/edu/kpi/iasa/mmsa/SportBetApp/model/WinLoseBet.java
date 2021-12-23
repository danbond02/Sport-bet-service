package edu.kpi.iasa.mmsa.SportBetApp.model;

import javax.persistence.*;

@Entity
@Table(name="winlosebet")
public class WinLoseBet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="team_bet_id")
    private Long TeamId;

    @Column(name = "bet_id")
    private Long betId;

    public WinLoseBet(Long teamId, Long betId) {
        this.TeamId = teamId;
        this.betId=betId;
    }

    public Long getId() {
        return id;
    }

    public Long getTeamId() {
        return TeamId;
    }

    public void setTeamId(Long teamId) {
        TeamId = teamId;
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }
}
