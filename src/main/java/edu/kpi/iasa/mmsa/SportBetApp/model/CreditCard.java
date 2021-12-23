package edu.kpi.iasa.mmsa.SportBetApp.model;

import javax.persistence.*;

@Entity
@Table(name="credit_card")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="number")
    private String number;

    @Column(name="cvv_code")
    private String cvvCode;

    @Column(name="date")
    private String date;

    @Column(name="balance_id")
    private Long balanceId;

    public CreditCard() {}

    public CreditCard(String number, String cvvCode, String date, Long balanceId) {
        this.number = number;
        this.cvvCode = cvvCode;
        this.date = date;
        this.balanceId = balanceId;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }
}
