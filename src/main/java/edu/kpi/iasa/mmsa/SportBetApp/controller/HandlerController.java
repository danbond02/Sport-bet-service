package edu.kpi.iasa.mmsa.SportBetApp.controller;

import edu.kpi.iasa.mmsa.SportBetApp.exceptions.BalanceNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.CreditCardNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.PlayerHaveNoBetsYetException;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.UserNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.model.CreditCard;
import edu.kpi.iasa.mmsa.SportBetApp.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class HandlerController extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public HandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = {PlayerHaveNoBetsYetException.class})
    protected ResponseEntity<Error> handleConflict(){
        Error error = Error.builder().code("BAD_REQUEST").description("Player Have No Bets Yet").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({UserNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<Void> handleUserNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<Error>> validationExceptionHandler(ConstraintViolationException ex, WebRequest request) {
        log.info("ex:", ex.getConstraintViolations().toArray());
        List<Error> errors = ex.getConstraintViolations().stream().map(violation ->
                Error.builder().description(violation.getPropertyPath() + " invalid. " +
                                messageSource.getMessage(violation.getMessage(), null, request.getLocale()))
                        .code("Bad Request").build()
        ).collect(Collectors.toList());

        return ResponseEntity.badRequest().body(errors);

    }

    @ExceptionHandler(value = {BalanceNotFoundException.class})
    protected ResponseEntity<Error> handleBalanceNotFoundException(){
        Error error = Error.builder().code("BAD_REQUEST").description("Balance was not found. Check account id.").build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(value = {CreditCardNotFoundException.class})
    protected ResponseEntity<Error> handleCreditCardNotFoundException(){
        Error error = Error.builder().code("BAD_REQUEST").description("Credit Card was not found.").build();
        return ResponseEntity.badRequest().body(error);
    }
}
