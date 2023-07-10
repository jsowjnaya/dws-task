package com.dws.challenge.model;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
public class AccountTranfer {
	@NotNull
	@NotEmpty
	 private  String fromAcoountId;
	@NotNull
	@NotEmpty
	 private  String toAcoountId;
	 
	 @NotNull
	 @Min(value = 0, message = "Initial balance must be positive.")
	 private BigDecimal amount;
	public String getFromAcoountId() {
		return fromAcoountId;
	}
	public void setFromAcoountId(String fromAcoountId) {
		this.fromAcoountId = fromAcoountId;
	}
	public String getToAcoountId() {
		return toAcoountId;
	}
	public void setToAcoountId(String toAcoountId) {
		this.toAcoountId = toAcoountId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	 
	 
}
