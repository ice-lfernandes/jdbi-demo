package br.com.ldf.medium.jdbi_demo.api;

import br.com.ldf.medium.jdbi_demo.domain.Developer;
import br.com.ldf.medium.jdbi_demo.service.DeveloperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/developer")
@AllArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping
    public Long saveOrUpdateDeveloper(@RequestBody Developer developer) {
        return developerService.saveNewDeveloper(developer);
    }

    @PutMapping
    public void updateDeveloper(@RequestBody Developer developer) {
        developerService.updateDeveloper(developer);
    }

    @GetMapping
    public Developer findDeveloperById(@RequestParam Long developerId) {
        return developerService.findDeveloperById(developerId);
    }

    @DeleteMapping
    public void removeDeveloperById(@RequestParam Long developerId) {
        developerService.removeDeveloperById(developerId);
    }
    
}