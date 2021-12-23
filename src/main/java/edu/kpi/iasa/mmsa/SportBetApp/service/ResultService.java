package edu.kpi.iasa.mmsa.SportBetApp.service;

import edu.kpi.iasa.mmsa.SportBetApp.dto.ResultDtoRequest;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.BetNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.GameNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.TeamNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.model.*;
import edu.kpi.iasa.mmsa.SportBetApp.repository.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    private GameTeamRelationRepo gameTeamRelationRepo;
    private TeamRepo teamRepo;
    private BetRepo betRepo;
    private GameRepo gameRepo;
    private WinLoseBetRepo winLoseBetRepo;
    private BalanceRepo balanceRepo;

    public ResultService(GameTeamRelationRepo gameTeamRelationRepo, TeamRepo teamRepo, BetRepo betRepo,
                         GameRepo gameRepo, WinLoseBetRepo winLoseBetRepo, BalanceRepo balanceRepo) {
        this.gameTeamRelationRepo = gameTeamRelationRepo;
        this.teamRepo = teamRepo;
        this.betRepo = betRepo;
        this.gameRepo = gameRepo;
        this.winLoseBetRepo = winLoseBetRepo;
        this.balanceRepo=balanceRepo;
    }

    public GameTeamRelation updateGameTeamRelation(Long gameId, Long teamId, Integer scoredGoals) {
        GameTeamRelation oldGameTeamRelation = gameTeamRelationRepo.findByGameIdAndTeamId(gameId, teamId);
        oldGameTeamRelation.setScoredGoals(scoredGoals);
        gameTeamRelationRepo.save(oldGameTeamRelation);
        return oldGameTeamRelation;
    }

    public List<GameTeamRelation> postResults(Long id, ResultDtoRequest resultDtoRequest) {
        Optional<Team> team1 = teamRepo.findByName(resultDtoRequest.getTeam1Name());
        Optional<Team> team2 = teamRepo.findByName(resultDtoRequest.getTeam2Name());
        Long team1Id;
        Long team2Id;

        if (team1.isPresent())
            team1Id = team1.get().getId();
        else throw new TeamNotFoundException();

        if (team2.isPresent())
            team2Id = team2.get().getId();
        else throw new TeamNotFoundException();

        GameTeamRelation team1UpdatedGameRelation = updateGameTeamRelation(id, team1Id,
                resultDtoRequest.getTeam1ScoredGoals());
        GameTeamRelation team2UpdatedGameRelation = updateGameTeamRelation(id, team2Id,
                resultDtoRequest.getTeam2ScoredGoals());

        return Arrays.asList(new GameTeamRelation[] {team1UpdatedGameRelation, team2UpdatedGameRelation});
    }

    public String getBetResultByBetId(Long id) {
        Optional<Bet> bet = betRepo.findById(id);
        if (bet.isPresent()){
            Bet getBet = bet.get();
            Optional<Game> game = gameRepo.findById(getBet.getGameId());
            if (game.isPresent()){
                Game getGame = game.get();
                if (getGame.getType().equals("Win/Lose")){
                    Long teamId = winLoseBetRepo.findByBetId(getBet.getId()).getTeamId();
                    Long winTeamId = gameTeamRelationRepo.findFirstByGameIdOrderByScoredGoals(getGame.getId()).getTeamId();
                    getBetResult(teamId, winTeamId, getGame, getBet);
                }
                /*else {
                    String higherLower =
                }*/
                return "Bet result";
            } else throw new GameNotFoundException();
        } else throw new BetNotFoundException();
    }

    private void getBetResult(Long teamId, Long winTeamId, Game game, Bet bet) {
        if (teamId.equals(winTeamId)){
            List<GameTeamRelation> gameTeamRelation = gameTeamRelationRepo.findByGameId(game.getId());

            Float sum = gameTeamRelation.get(0).getGeneralBetAmount() + gameTeamRelation.get(1).getGeneralBetAmount()
                    + gameTeamRelation.get(2).getGeneralBetAmount();

            int i = 0;
            for (i = 0; i <= 2; i++){
                if (gameTeamRelation.get(i).getTeamId().equals(teamId))
                    break;
            }
            Float teamCoefficient = sum / gameTeamRelation.get(i).getGeneralBetAmount();

            Optional<Balance> balance = balanceRepo.findByAccountId(bet.getAccId());
            if (balance.isPresent()){
                Balance oldBalance = balance.get();
                Float oldBalanceAmount = oldBalance.getBalanceAmount();
                oldBalance.setBalanceAmount(oldBalanceAmount + (bet.getBetAm()*teamCoefficient) - bet.getBetAm());
                balanceRepo.save(oldBalance);
            } else throw new BetNotFoundException();
        }
        else {
            Optional<Balance> balance = balanceRepo.findByAccountId(bet.getAccId());
            if (balance.isPresent()){
                Balance oldBalance = balance.get();
                Float oldBalanceAmount = oldBalance.getBalanceAmount();
                oldBalance.setBalanceAmount(oldBalanceAmount - bet.getBetAm());
                balanceRepo.save(oldBalance);
            } else throw new BetNotFoundException();
        }
    }
}
