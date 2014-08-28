package com.example.service;

import com.force.api.ApiSession;
import com.force.api.ForceApi;
import com.force.api.QueryResult;
import com.force.sdk.oauth.context.ForceSecurityContextHolder;
import com.force.sdk.oauth.context.SecurityContext;

import org.springframework.stereotype.Service;

import com.example.model.Account;
import com.example.model.Person;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    
    private ForceApi getForceApi() {
        SecurityContext sc = ForceSecurityContextHolder.get();

        ApiSession s = new ApiSession();
        s.setAccessToken(sc.getSessionId());
        s.setApiEndpoint(sc.getEndPointHost());

        return new ForceApi(s);
    }
    
    public void addPerson(Person person) {
    	AccountServiceImpl accountService = new AccountServiceImpl();
    	Account account = accountService.findAccountByName(person.getAccountName());
    	
    	if(account == null){
    		account = new Account();
    		account.setName(person.getAccountName());
    		accountService.addAccount(account);
    	}
    	// Retrieve the system-generated ID for the account and associate it with the contact.
		person.setAccountId(accountService.findAccountByName(person.getAccountName()).getId());
		System.err.println("Set person object account ID: " + person.getAccountId());
		System.err.println("This corresponds to account name: " + person.getAccountName());
        getForceApi().createSObject("contact", person);
    }

    public List<Person> listPeople() {
    	AccountServiceImpl accountService = new AccountServiceImpl();
    	
        QueryResult<Person> res = getForceApi().query("SELECT Id, FirstName, LastName, Email, AccountId FROM contact", Person.class);
        List<Person> people = res.getRecords();
        for (Person p : people){
        	p.setAccountName(accountService.findAccountName(p.getAccountId()));
        }
        return people;
    }

    public void removePerson(String id) {
        getForceApi().deleteSObject("contact", id);
    }
    
    public String existingEmail(String email) {
    	QueryResult<Person> res = getForceApi().query("SELECT Id, FirstName, LastName FROM contact WHERE(Email='" + email + "')", Person.class);
    	List<Person> records = res.getRecords();
    	if(records.isEmpty()) {
    		return "";
    	}
    	else {
    		// If there are any matches, there should only be one.
    		Person match = records.get(0);
    		return match.getLastName() + ", " + match.getFirstName();
    	}
    }
    
}
