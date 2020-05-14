package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import university.happyCatsSpring.entity.Breed;
import university.happyCatsSpring.service.iface.BreedService;

import java.util.List;

@RestController("/breeds")
public class BreedController {

    private BreedService breedService;

    @Autowired
    public BreedController(BreedService breedService) {
        this.breedService = breedService;

    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Breed>> listBreed() {
        return ResponseEntity.ok(breedService.findAll());
    }
}
