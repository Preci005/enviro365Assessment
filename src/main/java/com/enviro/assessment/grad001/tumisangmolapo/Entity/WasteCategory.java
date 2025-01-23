package com.enviro.assessment.grad001.tumisangmolapo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class WasteCategory {
    @Id
    @GeneratedValue
    private int id;
}
