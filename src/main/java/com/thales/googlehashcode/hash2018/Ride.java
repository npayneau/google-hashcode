package com.thales.googlehashcode.hash2018;

import lombok.Data;

import java.awt.*;

@Data
public class Ride {

    private Long id;

    private Point start;

    private Point finish;

    private Integer earliest;

    private Integer lastest;

    
}
