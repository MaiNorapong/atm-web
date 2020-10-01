package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Money;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("allBankAccounts", bankAccountService.getBankAccounts());
        return "bankaccount";
    }

    @PostMapping
    public String openBankAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.openAccount(bankAccount);
        model.addAttribute("allBankAccounts", bankAccountService.getBankAccounts());
        return "redirect:bankaccount";

    }

    @GetMapping("/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-edit";
    }

    @PostMapping("/edit/{id}")
    public String editAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model) {
        bankAccountService.editBankAccount(bankAccount);
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccount(@PathVariable int id,
                                Model model) {
        bankAccountService.deleteBankAccount(id);
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping("/deposit/{id}")
    public String getDepositPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-deposit";
    }

    @GetMapping("/withdraw/{id}")
    public String getWithdrawPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-withdraw";
    }

    @PostMapping("/deposit/{id}")
    public String depositMoney(@PathVariable int id,
                               @ModelAttribute Money money,
                               Model model) {
        bankAccountService.deposit(id, money);
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }

    @PostMapping("/withdraw/{id}")
    public String withdrawMoney(@PathVariable int id,
                                @ModelAttribute Money money,
                                Model model) {
        bankAccountService.withdraw(id, money);
        model.addAttribute("bankaccounts", bankAccountService.getBankAccounts());
        return "redirect:/bankaccount";
    }
}
