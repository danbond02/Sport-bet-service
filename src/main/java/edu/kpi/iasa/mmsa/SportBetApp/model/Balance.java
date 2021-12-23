package edu.kpi.iasa.mmsa.SportBetApp.model;

import javax.persistence.*;

@Entity
@Table(name="balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="balance_ammount")
    private Float balanceAmount;

    @Column(name="account_id")
    private Long accountId;

    public Balance() {}

    public Balance(Float balanceAmount, Long accountId) {
        this.balanceAmount = balanceAmount;
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public Float getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Float balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
