package com.enviro.assessment.grad001.tumisangmolapo.Service;

import com.enviro.assessment.grad001.tumisangmolapo.Entity.RecyclingTip;
import com.enviro.assessment.grad001.tumisangmolapo.Repository.RecyclingTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecyclingTipService {
    private final RecyclingTipRepository recyclingTipRepository;

    @Autowired
    public RecyclingTipService(RecyclingTipRepository recyclingTipRepository) {
        this.recyclingTipRepository = recyclingTipRepository;
    }

    public List<RecyclingTip> getAllRecyclingTips() {
        return recyclingTipRepository.findAll();
    }

    public Optional<RecyclingTip> getRecyclingTipById(Long id) {
        return recyclingTipRepository.findById(id);
    }

    public List<RecyclingTip> getRecyclingTipsByWasteCategoryId(Long wasteCategoryId) {
        return recyclingTipRepository.findByWasteCategoryId(wasteCategoryId);
    }

    public RecyclingTip saveRecyclingTip(RecyclingTip recyclingTip) {
        return recyclingTipRepository.save(recyclingTip);
    }

    public void deleteRecyclingTip(Long id) {
        recyclingTipRepository.deleteById(id);
    }
}
