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
public class BalanceService {

    BalanceRepo balanceRepo;
    CreditCardRepo creditCardRepo;

    public BalanceService(BalanceRepo balanceRepo, CreditCardRepo creditCardRepo) {
        this.balanceRepo = balanceRepo;
        this.creditCardRepo = creditCardRepo;
    }

    public String getBalanceByAccId(Long id) {
        Optional<Balance> balance = balanceRepo.findByAccountId(id);
        if (balance.isPresent())
            return "Balance amount:" + balance.get().getBalanceAmount();
        else throw new BalanceNotFoundException();
    }

    public String topUpBalanceByAccId(Long id, CreditCardDto creditCardDto) {

        Optional<Balance> balance = balanceRepo.findByAccountId(id);

        if (balance.isPresent()){
            Balance oldBalance = balance.get();
            Optional<CreditCard> creditCard = creditCardRepo.findByBalanceId(oldBalance.getId());
            CreditCard creditCard1 = new CreditCard(creditCardDto.getCardNumber(), creditCardDto.getCvvCode(),
                    creditCardDto.getDate(), oldBalance.getId());

            if (creditCard.isPresent() && creditCard.get().equals(creditCard1)) {
                updatebalance(oldBalance, creditCardDto.getTopUpAmount());
                balanceRepo.save(oldBalance);
                return "Balance was successfully updated";
            } else throw new CreditCardNotFoundException();
        }else throw new BalanceNotFoundException();
    }

    private void updatebalance(Balance oldBalance, Float topUpAmount) {
        Float oldAmount = oldBalance.getBalanceAmount();
        oldBalance.setBalanceAmount(oldAmount+topUpAmount);
    }
}
