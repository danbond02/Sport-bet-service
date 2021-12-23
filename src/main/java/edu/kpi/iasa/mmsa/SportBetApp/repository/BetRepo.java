package edu.kpi.iasa.mmsa.SportBetApp.repository;

import edu.kpi.iasa.mmsa.SportBetApp.model.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BetRepo extends JpaRepository<Bet, Long> {

    List<Bet> findByAccId(Long accId);
    void deleteByGameId(Long gameId);
}
