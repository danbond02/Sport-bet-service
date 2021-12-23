package edu.kpi.iasa.mmsa.SportBetApp.repository;

import edu.kpi.iasa.mmsa.SportBetApp.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCard, Long> {

    Optional<CreditCard> findByBalanceId(Long balanceId);
}
