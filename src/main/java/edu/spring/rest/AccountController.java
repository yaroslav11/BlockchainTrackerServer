package edu.spring.rest;

import edu.spring.domain.AccountService;
import edu.spring.domain.NotEnoughFundsException;
import edu.spring.domain.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @DeleteMapping("/account/{id}")
    public void delete(
        @PathVariable("id") long id
    ) {
        service.delete(id);
    }

    @RequestMapping(
        value = "/account/{id}",
        method = RequestMethod.GET
    )
    public AccountDTO get(
        @PathVariable("id") long id
    ) {
        Account account = service.get(id);
        return AccountDTO.toDto(account);
    }

    @RequestMapping(
        value = "/account/",
        method = RequestMethod.POST
    )
    public @ResponseBody AccountDTO create(
        @RequestBody AccountDTO dto
    ) {
        Account account = AccountDTO.toDomainObject(dto);
        Account accountWithId = service.create(account);
        return AccountDTO.toDto(accountWithId);
    }

    @PutMapping("/account/{id}/deposit")
    public void deposit(
        @PathVariable("id") long id,
        @RequestParam("amount") long amount
    ) {
        service.deposit(id, amount);
    }

    @PutMapping("/account/{id}/withdraw")
    public void withdraw(
        @PathVariable("id") long id,
        @RequestParam("amount") long amount
    ) throws NotEnoughFundsException {
        service.withdraw(id, amount);
    }

    @PutMapping("/account/{id}/holder")
    public void changeHolder(
        @PathVariable("id") long id,
        @RequestParam("name") String holder
    ) throws NotEnoughFundsException {
        service.changeHolder(id, holder);
    }

    @ExceptionHandler(NotEnoughFundsException.class)
    public ResponseEntity<String> handleNotEnoughFunds(NotEnoughFundsException ex) {
        return ResponseEntity.badRequest().body(
            "Not enough funds (" + ex.getBalance() + ") on account " + ex.getId()
                + " to withdraw " + ex.getAmount()
        );
    }
}
