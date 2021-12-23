package edu.kpi.iasa.mmsa.SportBetApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "credit card not found")
public class CreditCardNotFoundException extends RuntimeException {
}
