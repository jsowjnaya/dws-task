package com.dws.challenge.service;

//import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dws.challenge.model.Account;

//@Slf4j
@Service
public class EmailNotificationService implements NotificationService {
	public static Logger log =LoggerFactory.getLogger(EmailNotificationService.class);

  @Override
  public void notifyAboutTransfer(Account account, String transferDescription) {
    //THIS METHOD SHOULD NOT BE CHANGED - ASSUME YOUR COLLEAGUE WILL IMPLEMENT IT
	
	  log .info("Sending notification to owner of {}: {}", account.getAccountId(),
	  transferDescription);
	 
  }

}
