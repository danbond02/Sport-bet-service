package edu.kpi.iasa.mmsa.SportBetApp.controller;

import edu.kpi.iasa.mmsa.SportBetApp.dto.GameDto;
import edu.kpi.iasa.mmsa.SportBetApp.dto.GameDtoCreate;
import edu.kpi.iasa.mmsa.SportBetApp.model.Game;
import edu.kpi.iasa.mmsa.SportBetApp.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    @Autowired

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(value="/all")
    public ResponseEntity<List<GameDto>> getAll(){
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@Valid @RequestBody GameDtoCreate gameDtoCreate){
        return ResponseEntity.ok(gameService.create(gameDtoCreate));
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<GameDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @PatchMapping(value="/{id}")
    public ResponseEntity<Game> updateById(@PathVariable Long id, @Valid @RequestBody GameDtoCreate gameDtoCreate){
        return ResponseEntity.ok(gameService.updateGameById(id, gameDtoCreate));
    }

    @Transactional
    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(gameService.deleteGameById(id));
    }
}
