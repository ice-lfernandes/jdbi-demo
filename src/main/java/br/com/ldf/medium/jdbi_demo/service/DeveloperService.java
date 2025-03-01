package br.com.ldf.medium.jdbi_demo.service;

import br.com.ldf.medium.jdbi_demo.domain.Developer;
import br.com.ldf.medium.jdbi_demo.persistence.DeveloperRepository;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeveloperService {

    private final Jdbi jdbi;
    private final DeveloperRepository repository;

    @Transactional
    public Long saveNewDeveloper(Developer developer) {
        return jdbi.withHandle(handle ->
                (long) handle.createUpdate("INSERT INTO developer (name) VALUES (:name)")
                        .bindBean(developer) // bind Bean as parameter
                        .execute());
    }

    @Transactional
    public void updateDeveloper(Developer developer) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE developer SET name = :name WHERE id_developer = :id")
                        .bindBean(developer) // bind Bean as parameter
                        .execute());
    }

    public Developer findDeveloperById(Long developerId) {
        return repository.findById(developerId)
                .orElseThrow(() -> new RuntimeException("Developer not found"));
    }

    public void removeDeveloperById(Long developerId) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE from developer WHERE id_developer = :id")
                        .bind("id", developerId) // bind primitive as parameter
                        .execute());
    }

}