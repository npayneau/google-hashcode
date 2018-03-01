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
import java.util.stream.Collectors;

@Getter
@Setter
@Log4j2
public class HashCodeSolution {

    private final List<String> output = new ArrayList<>();
    private final int score = 0;

    private List<Ride> rides = new ArrayList<>();

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

        //TODO implements solution
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
                Point p2 = new Point(Integer.valueOf(line[1]), Integer.valueOf(line[2]));
                int earlystart = Integer.valueOf(line[3]);
                int lastest = Integer.valueOf(line[4]);

                rides.add(new Ride((long)idRide, p, p2, earlystart, lastest));
            }


            lineNumber++;

        }

        rides = rides.stream().sorted(Comparator.comparing(Ride::getEarliest)).collect(Collectors.toList());

    }
}
