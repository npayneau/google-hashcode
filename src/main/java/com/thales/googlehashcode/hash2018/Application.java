package com.thales.googlehashcode.hash2018;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

@Log4j2
public class Application {

	public static void main(String[] args) throws Exception{
		// Create if not exist or purge output folder
		final File outputFolder = new File(String.format("%s/hashcode/output/", System.getProperty("user.home")));
		outputFolder.mkdirs();
		FileUtils.cleanDirectory(outputFolder);

		// Get input folder
		final URL resource = Application.class.getClassLoader().getResource("input/");
		final File inputFolder = new File(Objects.requireNonNull(resource).getFile());

		for (File inputFile : Objects.requireNonNull(inputFolder.listFiles())) {
			final Scanner scanner = new Scanner(inputFile);

			System.out.println();
			log.info("============== START {}==============",inputFile.getName());
			final HashCodeSolution solution = new HashCodeSolution();
			solution.run(scanner);
			new OutputWriter(inputFile.getName(),outputFolder).write(solution.getOutput());

			log.info("============== END ==============");
		}
	}
}
