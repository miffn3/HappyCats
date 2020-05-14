package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import university.happyCatsSpring.entity.Cat;
import university.happyCatsSpring.service.iface.CatService;

import java.util.List;

@RestController
public class CatController {
    private CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;

    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Cat>> listCats() {
        return ResponseEntity.ok(catService.findAll());
    }
}
