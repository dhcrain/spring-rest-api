package com.dhcrain.app.controller;

import com.dhcrain.app.entity.Person;
import com.dhcrain.app.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public List<Person> home() {
        return personRepository.findAll();
    }

    @RequestMapping(value = "new/{name}", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseBody
    public Person populate(@PathVariable String name) {
        Person newPerson = new Person();
        newPerson.setName(name);
        personRepository.save(newPerson);
        return newPerson;

    }

}
