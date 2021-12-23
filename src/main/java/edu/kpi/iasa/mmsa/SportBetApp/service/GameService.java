package edu.kpi.iasa.mmsa.SportBetApp.service;

import edu.kpi.iasa.mmsa.SportBetApp.dto.GameDto;
import edu.kpi.iasa.mmsa.SportBetApp.dto.GameDtoCreate;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.GameHigherLowerRelationNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.GameNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.TeamNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.model.Game;
import edu.kpi.iasa.mmsa.SportBetApp.model.GameHigherLowerRelation;
import edu.kpi.iasa.mmsa.SportBetApp.model.GameTeamRelation;
import edu.kpi.iasa.mmsa.SportBetApp.model.Team;
import edu.kpi.iasa.mmsa.SportBetApp.repository.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    private GameRepo gameRepo;
    private GameTeamRelationRepo gameTeamRelationRepo;
    private TeamRepo teamRepo;
    private GameHigherLowerRepo gameHigherLowerRepo;

    public GameService(GameRepo gameRepo, GameTeamRelationRepo gameTeamRelationRepo, TeamRepo teamRepo,
                       GameHigherLowerRepo gameHigherLowerRepo) {
        this.gameRepo = gameRepo;
        this.gameTeamRelationRepo = gameTeamRelationRepo;
        this.teamRepo= teamRepo;
        this.gameHigherLowerRepo=gameHigherLowerRepo;
    }


    public List<GameDto> getAllGames() {
        List<Game> games = gameRepo.findAll();
        List<GameDto> gameDtos = null;
        for (int i = 0; i < games.size(); i++){
            gameDtos.add(getGameInformation(games.get(i)));
        }
        return gameDtos;
    }

    public Game create(GameDtoCreate gameDtoCreate) {
        Game game = new Game(gameDtoCreate.getPlace(), gameDtoCreate.getType(), gameDtoCreate.getDate(), gameDtoCreate.getTime());
        gameRepo.save(game);
        GameTeamRelation team1 = new GameTeamRelation(gameDtoCreate.getTeam1Id(), game.getId(),
                null, 0.01f);
        GameTeamRelation team2 = new GameTeamRelation(gameDtoCreate.getTeam2Id(), game.getId(),
                null, 0.01f);
        GameTeamRelation draw = new GameTeamRelation(null, game.getId() ,
                null, 0.01f);
        List<GameTeamRelation> gameTeamRelationList = Arrays.asList(new GameTeamRelation[] {team1, team2, draw});
        gameTeamRelationRepo.saveAll(gameTeamRelationList);
        return game;
    }




    public String getTeamName(Optional<Team> team) {
        if (team.isPresent()){
            String teamName = team.get().getName();
            return teamName;
        }
        else
            throw new TeamNotFoundException();
    }


    public GameDto getGameInformation(Game game) {
        List<GameTeamRelation> gameTeamRelation = gameTeamRelationRepo.findByGameId(game.getId());
        int i = 0;
        Float drawAmount = 0.f;
        for (i = 0; i < 2; i++) {
            if (gameTeamRelation.get(i).getTeamId() == null) {
                drawAmount = gameTeamRelation.get(i).getGeneralBetAmount();
                gameTeamRelation.remove(i);
                break;
            }
        }


        Optional<Team> team1 = teamRepo.findById(gameTeamRelation.get(0).getTeamId());
        Optional<Team> team2 = teamRepo.findById(gameTeamRelation.get(1).getTeamId());

        String team1Name = getTeamName(team1);
        String team2Name = getTeamName(team2);

        Float sum = gameTeamRelation.get(0).getGeneralBetAmount() + gameTeamRelation.get(1).getGeneralBetAmount()
                + drawAmount;
        Float team1Coefficient = sum / gameTeamRelation.get(0).getGeneralBetAmount();
        Float team2Coefficient = sum / gameTeamRelation.get(1).getGeneralBetAmount();
        Float drawCoefficient = sum / drawAmount;

        Long Total = null;
        if (team1.isPresent() && team2.isPresent()) {
            Total = (team1.get().getScoredGoals() + team2.get().getScoredGoals()) / (team1.get().getPlayedGames()
                    + team2.get().getPlayedGames());
        }

        Optional<GameHigherLowerRelation> gameHigherLowerRelation = gameHigherLowerRepo.findByGameId(game.getId());
        Float lowerCoefficient = 0.01f;
        Float higherCoefficient = 0.01f;

        if(gameHigherLowerRelation.isPresent()){
            Float sum_1 = gameHigherLowerRelation.get().getLowerAmmount() + gameHigherLowerRelation.get().getHigherAmmount();
            higherCoefficient = sum_1/gameHigherLowerRelation.get().getHigherAmmount();
            lowerCoefficient = sum_1 / gameHigherLowerRelation.get().getLowerAmmount();
        }
        else
            throw new GameHigherLowerRelationNotFoundException();


        GameDto gameDto = new GameDto(game.getId(), game.getPlace(), game.getType(), game.getDate(), game.getTime(),
                team1Name, team1Coefficient, team2Name, team2Coefficient, drawCoefficient, Total, higherCoefficient,
                lowerCoefficient);
        return gameDto;
    }


    public GameDto getGameById(Long id) {
        Optional<Game> game = gameRepo.findById(id);
        GameDto gameDto = new GameDto();
        if (game.isPresent()){
            gameDto = getGameInformation(game.get());
        }
        else throw new GameNotFoundException();
        return gameDto;
    }


    public String deleteGameById(Long id) {
        gameRepo.deleteById(id);
        return "Game was successfully deleted";
    }

    public Game updateGameById(Long id, GameDtoCreate gameDtoCreate) {
        Optional<Game> game = gameRepo.findById(id);
        List<GameTeamRelation> gameTeamRelation = gameTeamRelationRepo.findByGameId(id);
        Optional<GameHigherLowerRelation> gameHigherLowerRelation = gameHigherLowerRepo.findByGameId(id);

        if (game.isPresent() && !gameTeamRelation.isEmpty() && gameHigherLowerRelation.isPresent()){
            Game oldGame = game.get();
            updateGame(oldGame, gameDtoCreate);
            return gameRepo.save(oldGame);
        }
        throw new GameNotFoundException();
    }

    private void updateGame(Game game, GameDtoCreate gameDtoCreate) {
        if (gameDtoCreate.getPlace() != null)
            game.setPlace(gameDtoCreate.getPlace());
        if (gameDtoCreate.getType() != null)
            game.setType(gameDtoCreate.getType());
        if (gameDtoCreate.getDate() != null)
            game.setDate(gameDtoCreate.getDate());
        if (gameDtoCreate.getPlace() != null)
            game.setPlace(gameDtoCreate.getPlace());
        if (gameDtoCreate.getTeamOldId() != null){
            GameTeamRelation oldTeamRelation = gameTeamRelationRepo.findByGameIdAndTeamId(game.getId(),
                    gameDtoCreate.getTeamOldId());
            oldTeamRelation.setTeamId(gameDtoCreate.getTeamNewId());
            gameTeamRelationRepo.save(oldTeamRelation);
        }
    }
}
