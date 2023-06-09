package com.example.KourseJWT.service;

import com.example.KourseJWT.Encoder.PasswordEncoder;
import com.example.KourseJWT.interfaces.PersonService;
import com.example.KourseJWT.model.Person;
import com.example.KourseJWT.model.Role;
import com.example.KourseJWT.repository.PersonRepos;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    public final PersonRepos personRepos;

    public PersonServiceImpl(PersonRepos personRepos) {
        this.personRepos = personRepos;
    }
    @Transactional
    @Override
    public Person oneClientById(Integer id) {
        return personRepos.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void createClient(Person person) {
        person.setPassword(PasswordEncoder.HashPassword(person.getPassword()));
        person.setRole(Role.User);
        personRepos.save(person);
    }
    @Transactional
    @Override
    public void updateClient(Person person) {
        personRepos.update(person.getName(), person.getSurname(), person.getPatronymic(), person.getCity(),person.getPassportNumber(),person.getIdentNumber(),person.getId());
    }
    @Transactional
    @Override
    public void deleteClient(Integer id) {
        personRepos.deleteById(id);
    }
    @Transactional
    @Override
    public List<Person> allClients() {
        return personRepos.findAll();
    }
}
