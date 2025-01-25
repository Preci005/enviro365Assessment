package com.enviro.assessment.grad001.tumisangmolapo.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisposalGuidelineDTO {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Guideline is required")
    @Size(max = 1000, message = "Guideline must not exceed 1000 characters")
    private String guideline;

    @NotNull(message = "Waste Category ID is required")
    private Long wasteCategoryId;
}