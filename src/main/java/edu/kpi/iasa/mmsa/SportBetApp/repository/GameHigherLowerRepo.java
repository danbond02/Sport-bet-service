package edu.kpi.iasa.mmsa.SportBetApp.repository;

import edu.kpi.iasa.mmsa.SportBetApp.model.GameHigherLowerRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameHigherLowerRepo extends JpaRepository<GameHigherLowerRelation, Long> {

    Optional<GameHigherLowerRelation> findByGameId(Long gameId);
}
