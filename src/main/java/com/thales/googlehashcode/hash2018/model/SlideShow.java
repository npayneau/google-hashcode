package com.thales.googlehashcode.hash2018.model;

import lombok.Data;
import java.util.List;

@Data
public class SlideShow {
    List<Slide> slides;

    public SlideShow(List<Slide> slides) {
        this.slides = slides;
    }
}
