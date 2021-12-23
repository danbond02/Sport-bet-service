package edu.kpi.iasa.mmsa.SportBetApp.service;

import edu.kpi.iasa.mmsa.SportBetApp.dto.BetDto;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.BetNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.PlayerHaveNoBetsYetException;
import edu.kpi.iasa.mmsa.SportBetApp.model.*;
import edu.kpi.iasa.mmsa.SportBetApp.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BetService {

    private BetRepo betRepo;
    private HigherLowerBetRepo higherLowerBetRepo;
    private WinLoseBetRepo winLoseBetRepo;
    private GameTeamRelationRepo gameTeamRelationRepo;
    private GameHigherLowerRepo gameHigherLowerRepo;

    public BetService(BetRepo betRepo, HigherLowerBetRepo higherLowerBetRepo, WinLoseBetRepo winLoseBetRepo,
                      GameTeamRelationRepo gameTeamRelationRepo, GameHigherLowerRepo gameHigherLowerRepo) {
        this.betRepo=betRepo;
        this.higherLowerBetRepo = higherLowerBetRepo;
        this.winLoseBetRepo = winLoseBetRepo;
        this.gameTeamRelationRepo = gameTeamRelationRepo;
        this.gameHigherLowerRepo = gameHigherLowerRepo;
    }

    public List<Bet> getAllById(Long id) {
        List<Bet> listBet = betRepo.findByAccId(id);
        if (listBet.isEmpty())
            throw new PlayerHaveNoBetsYetException();
        return listBet;
    }

    public Bet create(BetDto betDto) {
        Bet bet = new Bet(betDto.getBetAm(), betDto.getType(), betDto.getAccId(), betDto.getGameId());
        betRepo.save(bet);
        switch (betDto.getType()) {
            case "Higher/Lower":
                HigherLowerBet higherLowerBet = new HigherLowerBet(betDto.getHigherLower(), bet.getId());
                higherLowerBetRepo.save(higherLowerBet);
                GameHigherLowerRelation gameHigherLowerRelation = gameHigherLowerRepo.findByGameId(bet.getGameId()).get();
                Float oldHigherAmount = gameHigherLowerRelation.getHigherAmmount();
                Float oldLowerAmount = gameHigherLowerRelation.getLowerAmmount();
                if(betDto.getHigherLower().equals("Higher"))
                    gameHigherLowerRelation.setHigherAmmount(oldHigherAmount+bet.getBetAm());
                else
                    gameHigherLowerRelation.setLowerAmmount(oldLowerAmount+bet.getBetAm());
                gameHigherLowerRepo.save(gameHigherLowerRelation);
                break;
            case "Win/Lose":
                WinLoseBet winLoseBet = new WinLoseBet(betDto.getTeamBetId(), bet.getId());
                winLoseBetRepo.save(winLoseBet);
                GameTeamRelation gameTeamRelation = gameTeamRelationRepo.findByGameIdAndTeamId(bet.getGameId(),
                        betDto.getTeamBetId());
                Float oldGeneralAmount = gameTeamRelation.getGeneralBetAmount();
                gameTeamRelation.setGeneralBetAmount(oldGeneralAmount + bet.getBetAm());
                gameTeamRelationRepo.save(gameTeamRelation);
                break;
        }
        return bet;
}

    public Bet update(Long id, BetDto betDto) {
        Optional<Bet> bet = betRepo.findById(id);
        if (bet.isPresent()) {
            Bet oldBet = bet.get();
            updateBet(oldBet, betDto);
            return betRepo.save(oldBet);
        }
        throw new BetNotFoundException();
    }

    private void updateBet(Bet oldBet, BetDto betDto) {
        if (betDto.getBetAm()!= null)
            oldBet.setBetAm(betDto.getBetAm());
        if (betDto.getType()!=null)
            oldBet.setType(betDto.getType());
        if (betDto.getAccId()!=null)
            oldBet.setAccId(betDto.getAccId());
        if (betDto.getGameId()!=null)
            oldBet.setGameId(betDto.getGameId());
    }

    public String delete(Long id) {
        betRepo.deleteById(id);
        return "Bet was successfully deleted";
    }
}
