package edu.kpi.iasa.mmsa.SportBetApp.model;


import javax.persistence.*;

@Entity
@Table(name="team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="Country")
    private String country;

    @Column(name="nuber_of_members")
    private Integer numberOfMembers;

    @Column(name="scored_goals")
    private Long scoredGoals;

    @Column(name="played_games")
    private Long playedGames;

    public Team() {}

    public Team(String name, String country, Integer numberOfMembers,
                Long scoredGoals, Long playedGames) {
        this.name = name;
        this.country = country;
        this.numberOfMembers = numberOfMembers;
        this.scoredGoals = scoredGoals;
        this.playedGames = playedGames;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(Integer numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public Long getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(Long scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public Long getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(Long playedGames) {
        this.playedGames = playedGames;
    }
}
