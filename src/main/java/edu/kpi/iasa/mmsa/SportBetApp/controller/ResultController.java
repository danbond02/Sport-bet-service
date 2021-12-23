package edu.kpi.iasa.mmsa.SportBetApp.controller;

import edu.kpi.iasa.mmsa.SportBetApp.dto.ResultDtoRequest;
import edu.kpi.iasa.mmsa.SportBetApp.model.GameTeamRelation;
import edu.kpi.iasa.mmsa.SportBetApp.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/result")
public class ResultController {

    private ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService){ this.resultService = resultService; }

    @PatchMapping(value="/{id}")
    public ResponseEntity<List<GameTeamRelation>> postResult(@PathVariable Long id,
                                                             @Valid @RequestBody ResultDtoRequest resultDtoRequest){
        return ResponseEntity.ok(resultService.postResults(id, resultDtoRequest));
    }
}
