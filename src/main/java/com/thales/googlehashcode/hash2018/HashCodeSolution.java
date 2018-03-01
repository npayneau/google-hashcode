package com.thales.googlehashcode.hash2018;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
<<<<<<< Updated upstream
import java.util.stream.Collectors;
=======
import java.util.stream.Stream;
>>>>>>> Stashed changes

@Getter
@Setter
@Log4j2
public class HashCodeSolution {

    private final List<String> output = new ArrayList<>();
    private final int score = 0;
    private final int steps = 0;
    private final List<Vehicle> vehicles = null;
    private final List<Ride> rides = null;

    private List<Ride> rides = new ArrayList<>();

    List<Vehicle> vehicles = new ArrayList<>();

    int nrow;
    int ncol;
    int nvehicules;
    int nrides;
    int bonus;
    int steps;


    public void run(final Scanner scanner) throws IOException {
        log.info("============== START INPUT PARSER ==============");
        //Call parser
        inputParser(scanner);
        log.info("============== END INPUT PARSER ==============");



        log.info("============== START SOLUTION ==============");

        //Trie des rides
        for(long ite=0; ite <steps; ite++ ){
            final long iteration = ite;
            vehicles.forEach( v -> {
                //Si il a un timer ou le timer est
                if(v.getIteration()== null || v.getIteration()<= iteration ){
                    // On peut lui attributer un ride
                    setRideToVehicle(iteration,v);
                }
            });
        }
        

        this.vehicles.forEach(vehicle -> {
            StringBuilder sb = new StringBuilder();
            sb.append(this.rides.size());

            vehicle.getRideList().forEach(ride -> {
                sb.append(" " + ride.getId());
            });
            this.output.add(sb.toString());
        });

    }

    private void setRideToVehicle(final long it, Vehicle v){
        List<Ride> rideOut = new ArrayList<>();
        rides.forEach(r -> {
            //On purge les rides non faisable
            if(r.getLastest()<= it){
                rideOut.add(r);
            }else{

                int distToArrive = findDist(v.getPosition(), r.getStart());
                int distRide = findDist(r.getStart(), r.getFinish());
                //Verification que c'est faisable
                if(r.getLastest()<= distRide + distToArrive){
                    v.getRideList().add(r);
                    rideOut.add(r);
                    v.setIteration(it+distRide+distToArrive);
                    return;
                }

            }
        });
        //Suppression des rides perimés
        rides.removeAll(rideOut);
    }

    private int findDist(Point p1, Point p2){
        return (int)(Math.abs(p1.getX()-p2.getX())+ Math.abs(p1.getY()-p2.getY()));
    }

    private void inputParser(final Scanner scanner){
<<<<<<< Updated upstream

        int lineNumber = 0;


        while (scanner.hasNext()) {

            final String[] line = scanner.nextLine().split(" ");

            if (lineNumber == 0) {
                nrow = Integer.valueOf(line[0]);
                ncol = Integer.valueOf(line[1]);
                nvehicules = Integer.valueOf(line[2]);
                nrides = Integer.valueOf(line[3]);
                bonus = Integer.valueOf(line[4]);
                steps = Integer.valueOf(line[5]);
            } else {

                int idRide = lineNumber - 1;
                Point p = new Point(Integer.valueOf(line[0]), Integer.valueOf(line[1]));
                Point p2 = new Point(Integer.valueOf(line[1]), Integer.valueOf(line[2]));
                int earlystart = Integer.valueOf(line[3]);
                int lastest = Integer.valueOf(line[4]);

                rides.add(new Ride((long)idRide, p, p2, earlystart, lastest));
            }


            lineNumber++;

        }

        rides = rides.stream().sorted(Comparator.comparing(Ride::getEarliest)).collect(Collectors.toList());

=======
        while (scanner.hasNext()){
            Stream.of(scanner.nextLine().split(" ")).forEach(x -> System.out.println(x));

        }
>>>>>>> Stashed changes
    }
}
