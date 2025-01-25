package com.enviro.assessment.grad001.tumisangmolapo.Controller;

import com.enviro.assessment.grad001.tumisangmolapo.DTO.RecyclingTipDTO;
import com.enviro.assessment.grad001.tumisangmolapo.Entity.RecyclingTip;
import com.enviro.assessment.grad001.tumisangmolapo.Entity.WasteCategory;
import com.enviro.assessment.grad001.tumisangmolapo.Service.RecyclingTipService;
import com.enviro.assessment.grad001.tumisangmolapo.Service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recycling-tips")
public class RecyclingTipController {
    private final RecyclingTipService recyclingTipService;
    private final WasteCategoryService wasteCategoryService;

    @Autowired
    public RecyclingTipController(RecyclingTipService recyclingTipService, WasteCategoryService wasteCategoryService) {
        this.recyclingTipService = recyclingTipService;
        this.wasteCategoryService = wasteCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<RecyclingTip>> getAllRecyclingTips() {
        return ResponseEntity.ok(recyclingTipService.getAllRecyclingTips());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecyclingTip> getRecyclingTipById(@PathVariable Long id) {
        return recyclingTipService.getRecyclingTipById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RecyclingTip> createRecyclingTip(@Valid @RequestBody RecyclingTipDTO dto) {
        Optional<WasteCategory> wasteCategory = wasteCategoryService.getWasteCategoryById(dto.getWasteCategoryId());
        if (wasteCategory.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        RecyclingTip tip = new RecyclingTip();
        tip.setTitle(dto.getTitle());
        tip.setTip(dto.getTip());
        tip.setWasteCategory(wasteCategory.get());
        return ResponseEntity.ok(recyclingTipService.saveRecyclingTip(tip));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecyclingTip> updateRecyclingTip(@PathVariable Long id, @Valid @RequestBody RecyclingTipDTO dto) {
        return (ResponseEntity<RecyclingTip>) recyclingTipService.getRecyclingTipById(id)
                .map(existingTip -> {
                    Optional<WasteCategory> wasteCategory = wasteCategoryService.getWasteCategoryById(dto.getWasteCategoryId());
                    if (wasteCategory.isEmpty()) {
                        return ResponseEntity.badRequest().build();
                    }
                    existingTip.setTitle(dto.getTitle());
                    existingTip.setTip(dto.getTip());
                    existingTip.setWasteCategory(wasteCategory.get());
                    return ResponseEntity.ok(recyclingTipService.saveRecyclingTip(existingTip));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecyclingTip(@PathVariable Long id) {
        if (recyclingTipService.getRecyclingTipById(id).isPresent()) {
            recyclingTipService.deleteRecyclingTip(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
