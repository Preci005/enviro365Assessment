package com.enviro.assessment.grad001.tumisangmolapo.Repository;

import com.enviro.assessment.grad001.tumisangmolapo.Entity.RecyclingTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecyclingTipRepository extends JpaRepository<RecyclingTip, Long> {
    List<RecyclingTip> findByWasteCategoryId(Long wasteCategoryId);
}
