package com.fss.aeps.util;


public class AccountIFSC {

	public final String account;
	public final String ifsc;
	public String accountType;
	
	public AccountIFSC(String account, String ifsc) {
		this.account = account;
		this.ifsc    = ifsc;
	}

	public AccountIFSC(String address) throws RuntimeException {
		if(!address.endsWith("ifsc.npci")) throw new RuntimeException("invalid upi addess : "+address);
		final String[] accountifsc = address.replace(".ifsc.npci", "").split("@");
		this.account = accountifsc[0];
		this.ifsc    = accountifsc[1];
	}
	
	public String build() throws RuntimeException {
		if(account == null || ifsc == null) throw new RuntimeException("invalid account ifsc : "+account+"@"+ifsc);
		return account+"@"+ifsc+".ifsc.npci";
	}
	
	public AccountIFSC setAccountType(String accountType) {
		this.accountType = accountType;
		return this;
	}

	public final String getAccount12() {
		if(account != null && account.length() > 12) return account.substring(account.length()-13, account.length()-1);
		return account;
	}
	
	@Override
	public String toString() {
		return account+"@"+ifsc+".ifsc.npci";
	}

	
}
