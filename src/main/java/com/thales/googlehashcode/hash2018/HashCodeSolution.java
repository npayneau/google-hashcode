package com.thales.googlehashcode.hash2018;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@Log4j2
public class HashCodeSolution {

    private final List<String> output = new ArrayList<>();
    private final int score = 0;
    private int steps = 0;

    private List<Ride> rides = new ArrayList<>();

    List<Vehicle> vehicles = new ArrayList<>();

    int nrow;
    int ncol;
    int nvehicules;
    int nrides;
    int bonus;


    public void run(final Scanner scanner) throws IOException {
        //Call parser
        inputParser(scanner);

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
            sb.append(vehicle.getRideList().size());

            vehicle.getRideList().forEach(ride -> {
                sb.append(" " + ride.getId());
            });
            this.output.add(sb.toString());
        });

        log.info("");
    }

    private void setRideToVehicle(final long it, Vehicle v){
        List<Ride> rideOut = new ArrayList<>();
        int index = 0;
        int limit = rides.size() < 100 ? rides.size() : rides.size();
        for(Ride r : rides){
            //On purge les rides non faisable
            if(r.getLastest()<= it){
                rideOut.add(r);
            }else{
                // On prend le ride le plus proche pour les 10% de la ride list
                int minDist = Integer.MAX_VALUE;
                int rideIndexSel = -1;
                long newIt = 0;
                for(int i = index; i< limit; i++){
                    int distToArrive = findDist(v.getPosition(), rides.get(i).getStart());
                    int distRide = findDist(r.getStart(), r.getFinish());

                    //Verification que c'est faisable
                    if(r.getLastest()>= distRide + distToArrive){
                        if(distToArrive < minDist){
                            minDist = distToArrive;
                            rideIndexSel = i;
                            newIt = distRide+distToArrive;
                        }
                    }
                }
                if(rideIndexSel!=-1){
                    v.getRideList().add(rides.get(rideIndexSel));
                    rideOut.add(rides.get(rideIndexSel));
                    v.setIteration(newIt);
                }
                break;
            }
            index++;
        }
        rides.removeAll(rideOut);
    }

    private int findDist(Point p1, Point p2){
        return (int)(Math.abs(p1.getX()-p2.getX())+ Math.abs(p1.getY()-p2.getY()));
    }

    private void inputParser(final Scanner scanner){

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
                Point p2 = new Point(Integer.valueOf(line[2]), Integer.valueOf(line[3]));
                int earlystart = Integer.valueOf(line[4]);
                int lastest = Integer.valueOf(line[5]);

                rides.add(new Ride((long)idRide, p, p2, earlystart, lastest));
            }


            lineNumber++;

        }

        rides = rides.stream().sorted(Comparator.comparing(Ride::getEarliest)).collect(Collectors.toList());
        for(int i=0; i<nvehicules; i++){
            vehicles.add(new Vehicle());
        }
    }
}
