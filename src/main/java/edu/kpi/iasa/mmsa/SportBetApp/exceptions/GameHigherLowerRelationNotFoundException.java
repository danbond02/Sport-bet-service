package edu.kpi.iasa.mmsa.SportBetApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Higher/Lower game not found")
public class GameHigherLowerRelationNotFoundException extends RuntimeException{
}
