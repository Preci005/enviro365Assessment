package com.enviro.assessment.grad001.tumisangmolapo.Repository;

import com.enviro.assessment.grad001.tumisangmolapo.Entity.DisposalGuideline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DisposalGuidelineRepository extends JpaRepository<DisposalGuideline, Long> {
    List<DisposalGuideline> findByWasteCategoryId(Long wasteCategoryId);
}
