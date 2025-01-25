package com.enviro.assessment.grad001.tumisangmolapo.Controller;

import com.enviro.assessment.grad001.tumisangmolapo.DTO.DisposalGuidelineDTO;
import com.enviro.assessment.grad001.tumisangmolapo.Entity.DisposalGuideline;
import com.enviro.assessment.grad001.tumisangmolapo.Entity.WasteCategory;
import com.enviro.assessment.grad001.tumisangmolapo.Service.DisposalGuidelineService;
import com.enviro.assessment.grad001.tumisangmolapo.Service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/disposal-guidelines")
public class DisposalGuidelineController {
    private final DisposalGuidelineService disposalGuidelineService;
    private final WasteCategoryService wasteCategoryService;

    @Autowired
    public DisposalGuidelineController(DisposalGuidelineService disposalGuidelineService, WasteCategoryService wasteCategoryService) {
        this.disposalGuidelineService = disposalGuidelineService;
        this.wasteCategoryService = wasteCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<DisposalGuideline>> getAllDisposalGuidelines() {
        return ResponseEntity.ok(disposalGuidelineService.getAllDisposalGuidelines());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisposalGuideline> getDisposalGuidelineById(@PathVariable Long id) {
        return disposalGuidelineService.getDisposalGuidelineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DisposalGuideline> createDisposalGuideline(@Valid @RequestBody DisposalGuidelineDTO dto) {
        Optional<WasteCategory> wasteCategory = wasteCategoryService.getWasteCategoryById(dto.getWasteCategoryId());
        if (wasteCategory.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        DisposalGuideline guideline = new DisposalGuideline();
        guideline.setTitle(dto.getTitle());
        guideline.setGuideline(dto.getGuideline());
        guideline.setWasteCategory(wasteCategory.get());
        return ResponseEntity.ok(disposalGuidelineService.saveDisposalGuideline(guideline));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisposalGuideline> updateDisposalGuideline(@PathVariable Long id, @Valid @RequestBody DisposalGuidelineDTO dto) {
        return (ResponseEntity<DisposalGuideline>) disposalGuidelineService.getDisposalGuidelineById(id)
                .map(existingGuideline -> {
                    Optional<WasteCategory> wasteCategory = wasteCategoryService.getWasteCategoryById(dto.getWasteCategoryId());
                    if (wasteCategory.isEmpty()) {
                        return ResponseEntity.badRequest().build();
                    }
                    existingGuideline.setTitle(dto.getTitle());
                    existingGuideline.setGuideline(dto.getGuideline());
                    existingGuideline.setWasteCategory(wasteCategory.get());
                    return ResponseEntity.ok(disposalGuidelineService.saveDisposalGuideline(existingGuideline));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisposalGuideline(@PathVariable Long id) {
        if (disposalGuidelineService.getDisposalGuidelineById(id).isPresent()) {
            disposalGuidelineService.deleteDisposalGuideline(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
