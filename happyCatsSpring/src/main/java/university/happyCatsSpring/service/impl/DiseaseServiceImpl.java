package university.happyCatsSpring.service.impl;

import university.happyCatsSpring.entity.Disease;
import university.happyCatsSpring.repo.DiseaseRepository;
import university.happyCatsSpring.service.iface.DiseaseService;

import java.util.List;
import java.util.Optional;

public class DiseaseServiceImpl implements DiseaseService {

    private DiseaseRepository diseaseRepository;

    public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public List<Disease> findAll() {
        return diseaseRepository.findAll();
    }

    @Override
    public Optional<Disease> findById(Long id) {
        return diseaseRepository.findById(id);
    }
}
