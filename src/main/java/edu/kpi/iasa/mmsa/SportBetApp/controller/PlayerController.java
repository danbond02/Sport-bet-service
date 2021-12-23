package edu.kpi.iasa.mmsa.SportBetApp.controller;

import edu.kpi.iasa.mmsa.SportBetApp.dto.BetDto;
import edu.kpi.iasa.mmsa.SportBetApp.dto.CreditCardDto;
import edu.kpi.iasa.mmsa.SportBetApp.dto.GameDto;
import edu.kpi.iasa.mmsa.SportBetApp.model.Bet;
import edu.kpi.iasa.mmsa.SportBetApp.model.CreditCard;
import edu.kpi.iasa.mmsa.SportBetApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/player")
public class PlayerController {

    private GameService gameService;
    private ResultService resultService;
    private BalanceService balanceService;
    private CreditCardService creditCardService;
    private BetService betService;

    @Autowired

    public PlayerController(GameService gameService, ResultService resultService, BalanceService balanceService,
                            CreditCardService creditCardService, BetService betService) {
        this.gameService = gameService;
        this.resultService = resultService;
        this.balanceService = balanceService;
        this.creditCardService = creditCardService;
        this.betService = betService;
    }

    @GetMapping(value="/game/all")
    public ResponseEntity<List<GameDto>> getAll(){
        return ResponseEntity.ok(gameService.getAllGames());
    }


    @GetMapping(value="/game/{id}")
    public ResponseEntity<GameDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(gameService.getGameById(id));
    }

    @GetMapping(value="/balance/{id}")
    public ResponseEntity<String> getBalanceById(@PathVariable Long id) {
        return ResponseEntity.ok(balanceService.getBalanceByAccId(id));
    }

    @PatchMapping(value="/balance/{id}")
    public ResponseEntity<String> topUpBalanceById(@PathVariable Long id,
                                                   @Valid @RequestBody CreditCardDto creditCardDto){
        return ResponseEntity.ok(balanceService.topUpBalanceByAccId(id, creditCardDto));
    }

    @GetMapping(value="/creditCard/{id}")
    public ResponseEntity<CreditCard> getCreditCardById(@PathVariable Long id) {
        return ResponseEntity.ok(creditCardService.getCreditCardByAccId(id));
    }

    @PostMapping(value="/creditCard/{id}")
    public ResponseEntity<CreditCard> addCreditCardById(@PathVariable Long id,
                                                    @Valid @RequestBody CreditCardDto creditCardDto){
        return ResponseEntity.ok(creditCardService.addCreditCardByAccId(id, creditCardDto));
    }

    @PatchMapping(value="/creditCard/{id}")
    public ResponseEntity<CreditCard> updateCreditCardById(@PathVariable Long id,
                                                           @Valid @RequestBody CreditCardDto creditCardDto){
        return ResponseEntity.ok(creditCardService.updateCreditCardByAccId(id, creditCardDto));
    }

    @DeleteMapping(value="/creditCard/{id}")
    public ResponseEntity<String> deleteCreditCardById(@PathVariable Long id){
        return ResponseEntity.ok(creditCardService.deleteCreditCardByAccId(id));
    }

    @GetMapping(value="/result/{id}")
    public ResponseEntity<String> getBetResultById(@PathVariable Long id){
        return ResponseEntity.ok(resultService.getBetResultByBetId(id));
    }

    @GetMapping(value = "/bet/all/{id}")
    public ResponseEntity<List<Bet>> getAllBetsById(@PathVariable Long id) {
        return ResponseEntity.ok(betService.getAllById(id));
    }

    @PostMapping(value="/bet")
    public ResponseEntity<Bet> createBet(@Valid @RequestBody BetDto betDto){
        return ResponseEntity.ok(betService.create(betDto));
    }

    @PatchMapping(value="/bet/update/{id}")
    public ResponseEntity<Bet> updateBet(@PathVariable Long id, @Valid @RequestBody BetDto betDto){
        return ResponseEntity.ok(betService.update(id, betDto));
    }

    @DeleteMapping(value="/bet/{id}")
    public ResponseEntity<String> deleteBet(@PathVariable Long id) {
        return ResponseEntity.ok(betService.delete(id));
    }

}
