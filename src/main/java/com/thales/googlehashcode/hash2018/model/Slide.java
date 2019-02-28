package com.thales.googlehashcode.hash2018.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Slide {
    List<Picture> pictures = new ArrayList<Picture>();
}
