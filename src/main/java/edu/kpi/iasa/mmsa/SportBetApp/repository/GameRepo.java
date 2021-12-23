package edu.kpi.iasa.mmsa.SportBetApp.repository;

import edu.kpi.iasa.mmsa.SportBetApp.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GameRepo extends JpaRepository<Game, Long> {

}
