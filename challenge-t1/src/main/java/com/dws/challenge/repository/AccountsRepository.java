package com.dws.challenge.repository;


import org.springframework.data.repository.CrudRepository;

import com.dws.challenge.model.Account;
public interface AccountsRepository extends CrudRepository<Account, String>
	{
	public Account findByAccountId(String id);
	}