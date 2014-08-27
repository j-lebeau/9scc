package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Person;
import com.example.service.PersonService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PersonController{

    @Autowired
    private PersonService personService;

    @RequestMapping("/")
    
    public String listPeople(Map<String, Object> map) {

        map.put("person", new Person());
        map.put("peopleList", personService.listPeople());

        return "people";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person person, BindingResult result, RedirectAttributes redirect ) {
    	String flashMsg = "";
    	String existingEmailPersonName = personService.existingEmail(person.getEmail());
    	if(!existingEmailPersonName.isEmpty() && !person.getEmail().equals("")) {
    		flashMsg += "The email address " + person.getEmail() + " is already in use by " + existingEmailPersonName;
    		redirect.addFlashAttribute("flashMsg", flashMsg);
    	}
    	else {
	        personService.addPerson(person);
    	}
        return "redirect:/people/";

    }

    @RequestMapping("/delete/{personId}")
    public String deletePerson(@PathVariable("personId") String personId) {

        personService.removePerson(personId);

        return "redirect:/people/";
    }
}
