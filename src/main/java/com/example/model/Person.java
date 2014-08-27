package com.example.model;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Person {

    @JsonProperty(value="Id")
    private String id;

    @JsonProperty(value="FirstName")
    private String firstName;

    @JsonProperty(value="LastName")
    private String lastName;

    @JsonProperty(value="Email")
    private String email;
    
    @JsonProperty(value="AccountId")
    private String accountId;
    
    // Without @JsonIgnore the field is still sent to the sObject.
    @JsonIgnore
    private String accountName;
        
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }

    public String getAccountId() {
    	return accountId;
    }
    
    public void setAccountId(String accountId) {
    	this.accountId = accountId;
    }
    
    public String getAccountName() {
    	return accountName;
    }
    
    public void setAccountName(String accountName) {
    	this.accountName = accountName;
    }
}
