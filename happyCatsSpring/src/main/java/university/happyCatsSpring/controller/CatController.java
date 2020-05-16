package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import university.happyCatsSpring.entity.Cat;
import university.happyCatsSpring.service.iface.CatService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cats")
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

    @GetMapping(value = "/id/{catid}")
    public ResponseEntity<Cat> getCatById(@PathVariable("catid") String catId)
    {
        Long id = Long.parseLong(catId);

        Optional<Cat> catOptional = catService.findById(id);

        if (catOptional.isPresent()) {
            Cat cat = catOptional.get();
            return ResponseEntity.ok(cat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Cat> getCatByName(@PathVariable("name") String name)
    {
        Optional<Cat> catOptional = catService.findByName(name);

        if (catOptional.isPresent()) {
            Cat cat = catOptional.get();
            return ResponseEntity.ok(cat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
