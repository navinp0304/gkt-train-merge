package com.example.geektrust;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import lombok.Getter;

public class StationsConfiguration {
	private final Set<String> stationsBeforeHyd = Set.of("CHN", "SLM", "BLR", "KRN", "TVC", "SRR", "MAQ", "MAO", "PNE");

	private final Map<String, Integer> stationsDist = Map.of("ENGINE", 0, "GHY", 1, "NJP", 2, "PTA", 3, "NDL", 4, "AGA",
			5, "BPL", 6, "ITJ", 7, "NGP", 8);
	@Getter
	private final String hydStationCode = "HYB";
	@Getter
	private final String trainAName = "TRAIN_A";
	@Getter
	private final String trainBName = "TRAIN_B";
	@Getter
	private final String trainABName = "TRAIN_AB";

	public final boolean checkBeforeHyd(String stationName) {
		return stationsBeforeHyd.contains(stationName);
	}

	public final int getStationsDist(String stationName) {
		return stationsDist.get(stationName);

	}

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
			throw new IllegalArgumentException("FILE NOT FOUND");
		}
		String trainAInit = input.nextLine();
		String trainBInit = input.nextLine();
		List<String> trainA = parseTrain(trainAInit);
		List<String> trainB = parseTrain(trainBInit);

		ITrainState trainState = new TrainDeparture(trainA, trainB);
		while (trainState != null) {
			trainState.printTrain();
			trainState = trainState.getNextState();
		}

	}
}
