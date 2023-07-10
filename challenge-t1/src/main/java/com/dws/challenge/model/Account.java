package com.dws.challenge.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table
public class Account {

	@NotNull
	@NotEmpty
	@Id
	@Column
	private String accountId;

	@NotNull
	@Min(value = 0, message = "Initial balance must be positive.")
	@Column
	private BigDecimal balance;

	public Account() {

	}

	public Account(String accountId) {
		this.accountId = accountId;
		this.balance = BigDecimal.ZERO;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
