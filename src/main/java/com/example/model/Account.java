package com.example.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Account {

	@JsonProperty(value="Id")
	private String id;
	
    @JsonProperty(value="Name")
    private String name;
    
    public String getId() {
    	return id;
    }
    
    public void setId(String id){
    	this.id = id;
    }
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
}