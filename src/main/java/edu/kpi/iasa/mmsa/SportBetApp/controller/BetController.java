package edu.kpi.iasa.mmsa.SportBetApp.controller;

import edu.kpi.iasa.mmsa.SportBetApp.dto.BetDto;
import edu.kpi.iasa.mmsa.SportBetApp.model.Bet;
import edu.kpi.iasa.mmsa.SportBetApp.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/bet")
public class BetController {

    private BetService betService;

    @Autowired
    public BetController(BetService betService) {this.betService = betService;}

    @GetMapping(value = "/all/{id}")
    public ResponseEntity<List<Bet>> getAllBetsById(@PathVariable Long id) {
        return ResponseEntity.ok(betService.getAllById(id));
    }

    @PostMapping
    public ResponseEntity<Bet> createBet(@Valid @RequestBody BetDto betDto){
        return ResponseEntity.ok(betService.create(betDto));
    }

    @PatchMapping(value="/update/{id}")
    public ResponseEntity<Bet> updateBet(@PathVariable Long id, @Valid @RequestBody BetDto betDto){
        return ResponseEntity.ok(betService.update(id, betDto));
    }

    @DeleteMapping(value="{id}")
    public ResponseEntity<String> deleteBet(@PathVariable Long id) {
        return ResponseEntity.ok(betService.delete(id));
    }

}
