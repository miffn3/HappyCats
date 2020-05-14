package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import university.happyCatsSpring.entity.Disease;
import university.happyCatsSpring.service.iface.DiseaseService;

import java.util.List;

@RestController
public class DiseaseController {

    private DiseaseService diseaseService;

    @Autowired
    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;

    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Disease>> listDisease() {
        return ResponseEntity.ok(diseaseService.findAll());
    }
}
