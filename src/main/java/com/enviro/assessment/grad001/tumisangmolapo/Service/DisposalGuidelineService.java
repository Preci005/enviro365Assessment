package com.enviro.assessment.grad001.tumisangmolapo.Service;

import com.enviro.assessment.grad001.tumisangmolapo.Entity.DisposalGuideline;
import com.enviro.assessment.grad001.tumisangmolapo.Repository.DisposalGuidelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisposalGuidelineService {
    private final DisposalGuidelineRepository disposalGuidelineRepository;

    @Autowired
    public DisposalGuidelineService(DisposalGuidelineRepository disposalGuidelineRepository) {
        this.disposalGuidelineRepository = disposalGuidelineRepository;
    }

    public List<DisposalGuideline> getAllDisposalGuidelines() {
        return disposalGuidelineRepository.findAll();
    }

    public Optional<DisposalGuideline> getDisposalGuidelineById(Long id) {
        return disposalGuidelineRepository.findById(id);
    }

    public List<DisposalGuideline> getDisposalGuidelinesByWasteCategoryId(Long wasteCategoryId) {
        return disposalGuidelineRepository.findByWasteCategoryId(wasteCategoryId);
    }

    public DisposalGuideline saveDisposalGuideline(DisposalGuideline disposalGuideline) {
        return disposalGuidelineRepository.save(disposalGuideline);
    }

    public void deleteDisposalGuideline(Long id) {
        disposalGuidelineRepository.deleteById(id);
    }
}
