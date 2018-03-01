package com.thales.googlehashcode.hash2018;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;

@Data
public class Ride {

    private Long id;

    private Point start;

    private Point finish;

    private Integer earliest;

    private Integer lastest;

    public Ride(Long id, Point start, Point finish, Integer earliest, Integer lastest) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.earliest = earliest;
        this.lastest = lastest;
    }
}
