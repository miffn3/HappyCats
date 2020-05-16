package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import university.happyCatsSpring.entity.Breed;
import university.happyCatsSpring.service.iface.BreedService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/breeds")
public class BreedController {

    private BreedService breedService;

    @Autowired
    public BreedController(BreedService breedService) {
        this.breedService = breedService;

    }

    @GetMapping(value = "/allbreeds")
    public ResponseEntity<List<Breed>> listBreed() {
        return ResponseEntity.ok(breedService.findAll());
    }

    @GetMapping(value = "/id/{breedid}")
    public ResponseEntity<Breed> getBreedById(@PathVariable("breedid") String breedId)
    {
        Long id = Long.parseLong(breedId);

        Optional<Breed> breedOptional = breedService.findById(id);

        if (breedOptional.isPresent()) {
            Breed breed = breedOptional.get();
            return ResponseEntity.ok(breed);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
