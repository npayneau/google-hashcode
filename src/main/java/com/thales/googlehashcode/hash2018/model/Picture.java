package com.thales.googlehashcode.hash2018.model;

import lombok.Data;
import java.util.List;

@Data
public class Picture {

    private Integer orientation;

    private List<String> tags;

    Picture(Integer orientation, List<String> tags) {
        this.orientation = orientation;
        this.tags = tags;
    }
}

