package university.happyCatsSpring.service.impl;

import university.happyCatsSpring.entity.Cat;
import university.happyCatsSpring.repo.CatRepository;
import university.happyCatsSpring.service.iface.CatService;

import java.util.List;

public class CatServiceImpl implements CatService {
    
    private CatRepository catRepository;

    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }
    
    @Override
    public List<Cat> findAll() {
        return catRepository.findAll();
    }
}
