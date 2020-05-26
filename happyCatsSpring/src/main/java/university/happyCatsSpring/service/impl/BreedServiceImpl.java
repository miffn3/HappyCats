package university.happyCatsSpring.service.impl;

import university.happyCatsSpring.entity.Breed;
import university.happyCatsSpring.repo.BreedRepository;
import university.happyCatsSpring.service.iface.BreedService;

import java.util.List;
import java.util.Optional;

public class BreedServiceImpl implements BreedService {

    private BreedRepository breedRepository;

    public BreedServiceImpl(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    @Override
    public List<Breed> findAll() {
        return breedRepository.findAll();
    }

    @Override
    public Optional<Breed> findById(Long id) {
        return breedRepository.findById(id);
    }
}
