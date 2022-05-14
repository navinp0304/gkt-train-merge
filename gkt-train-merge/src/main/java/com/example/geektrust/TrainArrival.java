package com.example.geektrust;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;

public class TrainArrival implements ITrainState {
	@Getter
	private List<String> trainAB;
	@Getter
	ITrainState nextState = null;
	private final String message = "DEPARTURE ";
	private final String journeyEnd = "JOURNEY_ENDED";

	TrainArrival(List<String> trainA, List<String> trainB) {

		List<String> afterHydTrainA = trainA.stream()
				.filter(station -> !station.equals(stationsConfig.getHydStationCode())
						&& !station.equals(stationsConfig.getTrainAName()))
				.collect(Collectors.toList());
		List<String> afterHydTrainB = trainB.stream()
				.filter(station -> !station.equals(stationsConfig.getHydStationCode())
						&& !station.equals(stationsConfig.getTrainBName()))
				.collect(Collectors.toList());

		List<String> mergedTrainAB = Stream.concat(afterHydTrainA.stream(), afterHydTrainB.stream())
				.collect(Collectors.toList());

		Collections.sort(mergedTrainAB, new Comparator<String>() {
			@Override
			public int compare(String bogie1, String bogie2) {
				int compare = stationsConfig.getStationsDist(bogie1) - stationsConfig.getStationsDist(bogie2);
				/*
				 * if (compare == 0) { return (bogie1.compareTo(bogie2)); }
				 */
				return compare;

			}
		});

		mergedTrainAB.add(0, stationsConfig.getTrainABName());
		trainAB = mergedTrainAB;

	}

	private Boolean printMessage(String message) {
		System.out.print(message);
		return false;
	}

	@Override
	public void printTrain() {
		int stopsAfterHyd = trainAB.size();
		Boolean either = (stopsAfterHyd > 3) ? printMessage(message + String.join(" ", trainAB))
				: printMessage(message + journeyEnd);

	}
}
