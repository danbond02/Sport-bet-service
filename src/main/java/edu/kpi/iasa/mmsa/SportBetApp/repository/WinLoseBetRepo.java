package edu.kpi.iasa.mmsa.SportBetApp.repository;

import edu.kpi.iasa.mmsa.SportBetApp.model.HigherLowerBet;
import edu.kpi.iasa.mmsa.SportBetApp.model.WinLoseBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WinLoseBetRepo extends JpaRepository<WinLoseBet, Long> {

    WinLoseBet findByBetId(Long betId);
}

