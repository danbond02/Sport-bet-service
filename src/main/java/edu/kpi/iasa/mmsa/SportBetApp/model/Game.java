package edu.kpi.iasa.mmsa.SportBetApp.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "place")
    private String place;

    @Column(name = "type")
    private String type;

    @Column(name="date")
    private Date date;

    @Column(name="time")
    private Time time;

    public Game() {}

    public Game(String place, String type, Date date, Time time) {
        this.place = place;
        this.type = type;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
