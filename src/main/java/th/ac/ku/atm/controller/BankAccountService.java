package th.ac.ku.atm.controller;

import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {

    private List<BankAccount> bankAccounts = new ArrayList();

    public List<BankAccount> getBankAccounts() {
        return new ArrayList<>(bankAccounts);
    }

    public void createBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

}
