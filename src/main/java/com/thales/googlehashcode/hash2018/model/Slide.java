package com.thales.googlehashcode.hash2018.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Slide {
    List<Integer> pictures = new ArrayList<Integer>();
    Set<String> tags = new HashSet<>();
    public void addPicture(int picture,List<String> tags){
        pictures.add(picture);
        this.tags.addAll(tags);
    }


}
