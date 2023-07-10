package com.dws.challenge.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dws.challenge.exceptions.AccountNotFoundException;
import com.dws.challenge.model.Account;
import com.dws.challenge.model.AccountTranfer;
import com.dws.challenge.service.AccountsService;

@RestController
@RequestMapping("/v1/accounts")
//@Slf4j
public class AccountsController {
	public static Logger log = LoggerFactory.getLogger(AccountsController.class);

	@Autowired
	AccountsService accountsService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createAccount(@RequestBody @Valid Account account) {
		log.info("Creating account {}" + account);
		try {
			if(accountsService.getAccountById(account.getAccountId())==null){
				this.accountsService.saveOrUpdate(account);
			}else {
				return new ResponseEntity<>("Account Id already exists", HttpStatus.UNPROCESSABLE_ENTITY); 
			}
			
		} catch (Exception daie) {
			return new ResponseEntity<>(daie.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping(path = "/{accountId}")
	public Account getAccount(@PathVariable String accountId) {
		log.info("Retrieving account for id {}" + accountId);
		Account account = accountsService.getAccountById(accountId);
		if (account == null) {
			throw new AccountNotFoundException("Accoutn not Found" + accountId);
		} else {
			return account;
		}
	}

	@PostMapping(value = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> transferAmount(@RequestBody @Valid AccountTranfer accountTranfer) {
		log.info("Creating account {}" + accountTranfer);
		try {
			if (!this.accountsService.saveTransferedAmount(accountTranfer)) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception daie) {
			return new ResponseEntity<>(daie.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
