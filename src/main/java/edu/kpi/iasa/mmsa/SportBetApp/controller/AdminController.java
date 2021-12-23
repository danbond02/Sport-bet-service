package edu.kpi.iasa.mmsa.SportBetApp.controller;

import edu.kpi.iasa.mmsa.SportBetApp.dto.GameDto;
import edu.kpi.iasa.mmsa.SportBetApp.dto.GameDtoCreate;
import edu.kpi.iasa.mmsa.SportBetApp.dto.ResultDtoRequest;
import edu.kpi.iasa.mmsa.SportBetApp.model.Game;
import edu.kpi.iasa.mmsa.SportBetApp.model.GameTeamRelation;
import edu.kpi.iasa.mmsa.SportBetApp.service.GameService;
import edu.kpi.iasa.mmsa.SportBetApp.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private GameService gameService;
    private ResultService resultService;

    @Autowired

    public AdminController(GameService gameService, ResultService resultService) {

        this.gameService = gameService;
        this.resultService = resultService;
    }

    @GetMapping(value="/game/all")
    public ResponseEntity<List<GameDto>> getAll(){
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @PostMapping(value="/game")
    public ResponseEntity<Game> createGame(@Valid @RequestBody GameDtoCreate gameDtoCreate){
        return ResponseEntity.ok(gameService.create(gameDtoCreate));
    }

    @GetMapping(value="/game/{id}")
    public ResponseEntity<GameDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @PatchMapping(value="/game/{id}")
    public ResponseEntity<Game> updateById(@PathVariable Long id, @Valid @RequestBody GameDtoCreate gameDtoCreate){
        return ResponseEntity.ok(gameService.updateGameById(id, gameDtoCreate));
    }

    @Transactional
    @DeleteMapping(value="/game/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(gameService.deleteGameById(id));
    }

    @PatchMapping(value="/result/{id}")
    public ResponseEntity<List<GameTeamRelation>> postResult(@PathVariable Long id,
                                                             @Valid @RequestBody ResultDtoRequest resultDtoRequest){
        return ResponseEntity.ok(resultService.postResults(id, resultDtoRequest));
    }
}
