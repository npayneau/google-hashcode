package com.thales.googlehashcode.hash2018.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class Slide {
    List<Integer> pictures = new ArrayList<Integer>();

    public void addPicture(int picture){
        pictures.add(picture);
    }
}
