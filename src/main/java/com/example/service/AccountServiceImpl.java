package com.example.service;

import com.example.model.Account;
import com.force.api.ApiSession;
import com.force.api.ForceApi;
import com.force.api.QueryResult;
import com.force.sdk.oauth.context.ForceSecurityContextHolder;
import com.force.sdk.oauth.context.SecurityContext;

public class AccountServiceImpl implements AccountService {
	
	private ForceApi getForceApi() {
        SecurityContext sc = ForceSecurityContextHolder.get();

        ApiSession s = new ApiSession();
        s.setAccessToken(sc.getSessionId());
        s.setApiEndpoint(sc.getEndPointHost());

        return new ForceApi(s);
    }
	
	public void addAccount(Account account) {
		getForceApi().createSObject("account", account);
	}
	
	public Account findAccountByName(String accountName) {
		if(accountName == null || accountName.equals(""))
			return null;
    	QueryResult<Account> res = getForceApi().query("SELECT Id FROM account WHERE(Name='" + accountName + "')", Account.class);
    	return res.getRecords().isEmpty() ? null : res.getRecords().get(0);
	}

	public String findAccountName(String accountId) {
		if(accountId == null)
			return null;
		QueryResult<Account> res = getForceApi().query("SELECT Name FROM account WHERE(Id='" + accountId + "')", Account.class);
		return res.getRecords().isEmpty() ? null : res.getRecords().get(0).getName();
	}
}
