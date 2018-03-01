package com.thales.googlehashcode.hash2018;

import lombok.Data;

import java.awt.*;
import java.util.List;

@Data
public class Vehicle {

    private Long id;

    private Long iteration;

    private Point position;

    private List<Ride> rideList;

}
