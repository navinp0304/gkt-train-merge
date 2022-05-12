package com.example.geektrust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class Utility {
	private List<String> parseTrain(String train) {
		List<String> completeTrainNoEngine = Arrays.asList(train.split(" "));
		return completeTrainNoEngine;
	}

	void run(String inputFileName) {
		Scanner input = null;
		try {
			input = new Scanner(new File(inputFileName));
		} catch (FileNotFoundException e) {
			System.err.println("FILE NOT FOUND");
		}
		String trainAInit = input.nextLine();
		String trainBInit = input.nextLine();
		List<String> trainA = parseTrain(trainAInit);
		List<String> trainB = parseTrain(trainBInit);
		TrainsState trains = new TrainsState(trainA, trainB);
		IState currentState = new HydArrivalState(trains);
		while (currentState != null) {
			currentState = currentState.getNextState();
		}
		if (input != null)
			input.close();
	}
}
