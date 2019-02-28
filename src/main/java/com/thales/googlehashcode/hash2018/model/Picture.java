package com.thales.googlehashcode.hash2018.model;

import lombok.Data;
import java.util.List;

@Data
public class Picture {

    private int id;

    private int orientation;

    private List<String> tags;

    public Picture(int id, int orientation, List<String> tags) {
        this.orientation = orientation;
        this.tags = tags;
        this.id = id;
    }
}

