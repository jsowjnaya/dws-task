package com.dws.challenge.service;

import com.dws.challenge.controller.AccountsController;
import com.dws.challenge.model.Account;
import com.dws.challenge.model.AccountTranfer;
import com.dws.challenge.repository.AccountsRepository;

//import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

	public static Logger log =LoggerFactory.getLogger(AccountsService.class);

  @Autowired
  AccountsRepository accountsRepository;
  @Autowired(required=true)
  EmailNotificationService notificationService;
  private ExecutorService notifierEcecutor=Executors.newSingleThreadExecutor();
  
  public Account getAccountById(String id) 
  {
  return accountsRepository.findByAccountId(id);
  }
  public void saveOrUpdate(Account account) 
  {
	  accountsRepository.save(account);
  }
  
  public boolean saveTransferedAmount(AccountTranfer accountTranf) 
  {
	  
	  Account fromAcc=accountsRepository.findByAccountId(accountTranf.getFromAcoountId());
	  Account toAcc=accountsRepository.findByAccountId(accountTranf.getToAcoountId());
	  if(fromAcc.getBalance().compareTo(accountTranf.getAmount())>=0) {
		  
		  fromAcc.setBalance(fromAcc.getBalance().subtract(accountTranf.getAmount()));
		  toAcc.setBalance(toAcc.getBalance().subtract(accountTranf.getAmount()));
		  log.info("fromaccc:"+fromAcc.toString());
		  log.info("toAcc:"+toAcc.toString());
		  accountsRepository.save(fromAcc);
		  accountsRepository.save(toAcc);
		  notifierEcecutor.submit(()->notificationService.notifyAboutTransfer(fromAcc, String.format("Account Id:%s Tranfered Amount :%d", fromAcc.getAccountId(),accountTranf.getAmount())));
		  notifierEcecutor.submit(()->notificationService.notifyAboutTransfer(toAcc, String.format("Account Id:%s Tranfered Amount Received :%d", toAcc.getAccountId(),accountTranf.getAmount())));
		  return true;
	  }else {
		  log.info("Insufficient Balance in :");
	  }
	  
	  return false;
  }
  //deleting a specific record
  public void delete(String id) 
  {
	  accountsRepository.deleteById(id);
  }
}
