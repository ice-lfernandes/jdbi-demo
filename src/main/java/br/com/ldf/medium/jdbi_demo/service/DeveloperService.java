package br.com.ldf.medium.jdbi_demo.service;

import br.com.ldf.medium.jdbi_demo.domain.Developer;
import br.com.ldf.medium.jdbi_demo.persistence.DeveloperRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DeveloperService {

    private final DeveloperRepository developerRepository;

    @Transactional
    public Long saveNewDeveloper(Developer developer) {
        return developerRepository.saveNewDeveloper(developer);
    }

    @Transactional
    public void updateDeveloper(Developer developer) {
        developerRepository.updateDeveloper(developer);
    }

    public Developer findDeveloperById(Long developerId) {
        return developerRepository.findDeveloperById(developerId)
                .orElseThrow(() -> new RuntimeException("Developer not found"));
    }

    public void removeDeveloperById(Long developerId) {
        developerRepository.removeDeveloperById(developerId);
    }

}