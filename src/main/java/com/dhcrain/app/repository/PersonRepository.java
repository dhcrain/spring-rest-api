package com.dhcrain.app.repository;

import com.dhcrain.app.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {
    public List<Person> findAll();
}
