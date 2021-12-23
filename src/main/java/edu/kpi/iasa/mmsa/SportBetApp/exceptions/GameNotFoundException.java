package edu.kpi.iasa.mmsa.SportBetApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "game not found")
public class GameNotFoundException extends RuntimeException {
}
