package edu.kpi.iasa.mmsa.SportBetApp.service;

import edu.kpi.iasa.mmsa.SportBetApp.dto.CreditCardDto;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.BalanceNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.exceptions.CreditCardNotFoundException;
import edu.kpi.iasa.mmsa.SportBetApp.model.Balance;
import edu.kpi.iasa.mmsa.SportBetApp.model.CreditCard;
import edu.kpi.iasa.mmsa.SportBetApp.repository.BalanceRepo;
import edu.kpi.iasa.mmsa.SportBetApp.repository.CreditCardRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreditCardService {

    private CreditCardRepo creditCardRepo;
    private BalanceRepo balanceRepo;

    public CreditCardService(CreditCardRepo creditCardRepo, BalanceRepo balanceRepo) {
        this.creditCardRepo = creditCardRepo;
        this.balanceRepo=balanceRepo;
    }

    public CreditCard getCreditCardByAccId(Long id) {
        Optional<Balance> balance = balanceRepo.findByAccountId(id);
        if (balance.isPresent()){
            Optional<CreditCard> creditCard = creditCardRepo.findByBalanceId(balance.get().getId());
            if (creditCard.isPresent()) {
                return creditCard.get();
            } else throw new CreditCardNotFoundException();
        } else throw new BalanceNotFoundException();
    }

    public CreditCard addCreditCardByAccId(Long id, CreditCardDto creditCardDto) {
            Optional<Balance> balance = balanceRepo.findByAccountId(id);
            if (balance.isPresent()){
                CreditCard creditCard = new CreditCard(creditCardDto.getCardNumber(), creditCardDto.getCvvCode(),
                        creditCardDto.getDate(), balance.get().getId());
                return creditCardRepo.save(creditCard);
            } else throw new BalanceNotFoundException();
    }

    public CreditCard updateCreditCardByAccId(Long id, CreditCardDto creditCardDto) {
        Optional<Balance> balance = balanceRepo.findByAccountId(id);
        if (balance.isPresent()){
            Optional<CreditCard> creditCard = creditCardRepo.findByBalanceId(balance.get().getId());
            if (creditCard.isPresent()) {
                CreditCard oldCreditCard = creditCard.get();
                updateCreditCard(oldCreditCard, creditCardDto);
                return creditCardRepo.save(oldCreditCard);
            } else throw new CreditCardNotFoundException();
        } else throw new BalanceNotFoundException();
    }

    private void updateCreditCard(CreditCard oldCreditCard, CreditCardDto creditCardDto) {
        if (creditCardDto.getCardNumber() != null)
            oldCreditCard.setNumber(creditCardDto.getCardNumber());
        if (creditCardDto.getCvvCode() != null)
            oldCreditCard.setCvvCode(creditCardDto.getCvvCode());
        if (creditCardDto.getDate() != null)
            oldCreditCard.setDate(creditCardDto.getDate());
    }

    public String deleteCreditCardByAccId(Long id) {
        Optional<Balance> balance = balanceRepo.findByAccountId(id);
        if (balance.isPresent()){
            Optional<CreditCard> creditCard = creditCardRepo.findByBalanceId(balance.get().getId());
            if (creditCard.isPresent()) {
                creditCardRepo.deleteById(creditCard.get().getId());
                return "Card was successfully deleted";
            } else throw new CreditCardNotFoundException();
        } else throw new BalanceNotFoundException();
    }
}
