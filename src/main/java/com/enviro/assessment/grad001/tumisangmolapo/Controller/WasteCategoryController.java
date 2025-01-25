package com.enviro.assessment.grad001.tumisangmolapo.Controller;

import com.enviro.assessment.grad001.tumisangmolapo.DTO.WasteCategoryDTO;
import com.enviro.assessment.grad001.tumisangmolapo.Entity.WasteCategory;
import com.enviro.assessment.grad001.tumisangmolapo.Service.WasteCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waste-categories")
public class WasteCategoryController {
    private final WasteCategoryService wasteCategoryService;

    @Autowired
    public WasteCategoryController(WasteCategoryService wasteCategoryService) {
        this.wasteCategoryService = wasteCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<WasteCategory>> getAllWasteCategories() {
        return ResponseEntity.ok(wasteCategoryService.getAllWasteCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategory> getWasteCategoryById(@PathVariable Long id) {
        return wasteCategoryService.getWasteCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<WasteCategory> createWasteCategory(@Valid @RequestBody WasteCategoryDTO wasteCategoryDTO) {
        WasteCategory wasteCategory = new WasteCategory();
        wasteCategory.setName(wasteCategoryDTO.getName());
        wasteCategory.setDescription(wasteCategoryDTO.getDescription());
        return ResponseEntity.ok(wasteCategoryService.saveWasteCategory(wasteCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategory> updateWasteCategory(@PathVariable Long id, @Valid @RequestBody WasteCategoryDTO wasteCategoryDTO) {
        return wasteCategoryService.getWasteCategoryById(id)
                .map(existingCategory -> {
                    existingCategory.setName(wasteCategoryDTO.getName());
                    existingCategory.setDescription(wasteCategoryDTO.getDescription());
                    return ResponseEntity.ok(wasteCategoryService.saveWasteCategory(existingCategory));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWasteCategory(@PathVariable Long id) {
        if (wasteCategoryService.getWasteCategoryById(id).isPresent()) {
            wasteCategoryService.deleteWasteCategory(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
