package com.thales.googlehashcode.hash2018;

import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class Vehicle {

    private Long id;

    private Long iteration = null;

    private Point position = new Point(0,0);

    private List<Ride> rideList = new ArrayList<>();

}
