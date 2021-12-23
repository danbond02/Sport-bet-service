package edu.kpi.iasa.mmsa.SportBetApp.repository;

import edu.kpi.iasa.mmsa.SportBetApp.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepo extends JpaRepository<Balance, Long> {

    Optional<Balance> findByAccountId(Long accountId);
}
