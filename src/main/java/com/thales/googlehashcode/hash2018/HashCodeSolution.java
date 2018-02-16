package com.thales.googlehashcode.hash2018;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@Log4j2
public class HashCodeSolution {

    private final List<String> output = new ArrayList<>();
    private final int score = 0;

    public void run(final Scanner scanner) throws IOException {
        log.info("============== START INPUT PARSER ==============");
        //Call parser
        inputParser(scanner);
        log.info("============== END INPUT PARSER ==============");

        log.info("============== START SOLUTION ==============");

        //TODO implements solution
    }

    private void inputParser(final Scanner scanner){
        final String[] firstLine = scanner.nextLine().split(" ");
        //TODO implements parserInput
    }
}
