package university.happyCatsSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import university.happyCatsSpring.dto.CreateCatDto;
import university.happyCatsSpring.dto.UpdateCatDto;
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

    @PostMapping(value = "/")
    public ResponseEntity addCat(@RequestBody CreateCatDto body) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();
        try {
            Cat cat = catService.addCat(username, body);
            return ResponseEntity.ok(cat);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping(value = "/catid/{id}")
    public ResponseEntity updateCat(@PathVariable("catid") String catId,
                                         @RequestBody UpdateCatDto body)
    {
        Long id = Long.parseLong(catId);
        Optional<Cat> catOptional = catService.findById(id);

        if (catOptional.isPresent()) {
            Cat cat = catOptional.get();
            try {
                Cat updated = catService.updateCat(cat, body);
                return ResponseEntity.ok(updated);
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
