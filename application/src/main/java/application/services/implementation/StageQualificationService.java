package application.services.implementation;

import application.repositories.StageQualificationRepository;
import application.services.IStageQualificationService;
import db.models.StageQualification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StageQualificationService implements IStageQualificationService {
    private final StageQualificationRepository stageQualificationRepository;
    public StageQualificationService(StageQualificationRepository stageQualificationRepository){
        this.stageQualificationRepository = stageQualificationRepository;
    }

    @Override
    public List<StageQualification> getAll() {
        return StreamSupport.stream(stageQualificationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public StageQualification getStageQualification(Integer id) {
        return stageQualificationRepository.findById(id).orElseThrow(); //NoSuchElementException
    }

    @Override
    public StageQualification addStageQualification(StageQualification stageQualification) {
        if(stageQualification.getId() != null) throw new IllegalArgumentException("New entity can not have id");
        return stageQualificationRepository.save(stageQualification);
    }

    @Override
    public StageQualification updateStageQualification(StageQualification stageQualification) {
        if(!stageQualificationRepository.existsById(stageQualification.getId())) throw new NoSuchElementException("No value present");
        return stageQualificationRepository.save(stageQualification);
    }

    @Override
    public void deleteStageQualification(Integer id) {
        stageQualificationRepository.deleteById(id);  //EmptyResultDataAccessException
    }
}
