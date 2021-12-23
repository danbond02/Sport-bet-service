package edu.kpi.iasa.mmsa.SportBetApp.repository;

import edu.kpi.iasa.mmsa.SportBetApp.model.HigherLowerBet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HigherLowerBetRepo extends JpaRepository<HigherLowerBet, Long> {

    List<HigherLowerBet> findByBetIdAndHigherLower(Long betId, String higherLower);
}
