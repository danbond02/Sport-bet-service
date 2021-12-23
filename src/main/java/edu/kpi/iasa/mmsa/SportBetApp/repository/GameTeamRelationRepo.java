package edu.kpi.iasa.mmsa.SportBetApp.repository;

import edu.kpi.iasa.mmsa.SportBetApp.model.GameTeamRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameTeamRelationRepo extends JpaRepository<GameTeamRelation, Long> {

    List<GameTeamRelation> findByGameId(Long gameId);

    GameTeamRelation findByGameIdAndTeamId(Long gameId, Long teamId);

    GameTeamRelation findFirstByGameIdOrderByScoredGoals(Long gameId);

}
