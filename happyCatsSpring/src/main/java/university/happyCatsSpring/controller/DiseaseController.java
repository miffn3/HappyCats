package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import university.happyCatsSpring.entity.Disease;
import university.happyCatsSpring.service.iface.DiseaseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diseases")
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

    @GetMapping(value = "/id/{diseaseid}")
    public ResponseEntity<Disease> getDiseaseById(@PathVariable("diseaseid") String diseaseId)
    {
        Long id = Long.parseLong(diseaseId);

        Optional<Disease> diseaseOptional = diseaseService.findById(id);

        if (diseaseOptional.isPresent()) {
            Disease disease = diseaseOptional.get();
            return ResponseEntity.ok(disease);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
