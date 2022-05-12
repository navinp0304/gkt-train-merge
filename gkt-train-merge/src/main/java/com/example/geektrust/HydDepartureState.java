package com.example.geektrust;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HydDepartureState implements IState {
	TrainsState trainsState;
	StationsConfiguration stationsConfig = new StationsConfiguration();
	private final String journeyEnd = "JOURNEY_ENDED";
	private final String mergedTrainName = "TRAIN_AB";
	private final String message = "DEPARTURE ";

	private Boolean printMessage(String Message) {
		System.out.println(Message);
		return true;
	}

	HydDepartureState(TrainsState trainsState) {
		List<String> depatureTrainAHyd = trainsState.getTrainA();
		List<String> depatureTrainBHyd = trainsState.getTrainB();
		List<String> afterHydTrainA = depatureTrainAHyd.stream()
				.filter(station -> !station.equals(stationsConfig.getHydStationCode())
						&& !station.equals(stationsConfig.getTrainAName()))
				.collect(Collectors.toList());
		List<String> afterHydTrainB = depatureTrainBHyd.stream()
				.filter(station -> !station.equals(stationsConfig.getHydStationCode())
						&& !station.equals(stationsConfig.getTrainBName()))
				.collect(Collectors.toList());
		// System.out.println(Arrays.toString(trainAHyd.toArray()));
		// merge sort and print

		List<String> mergedTrainAB = Stream.concat(afterHydTrainA.stream(), afterHydTrainB.stream())
				.collect(Collectors.toList());

		Collections.sort(mergedTrainAB, new Comparator<String>() {
			public int compare(String bogie1, String bogie2) {
				int compare = stationsConfig.getStationsDist(bogie1) - stationsConfig.getStationsDist(bogie2);
				if (compare == 0) {
					return (bogie1.compareTo(bogie2));
				}
				return compare;
			}
		});

		// System.out.println(afterHydTrainA.toString());

		mergedTrainAB.add(0, mergedTrainName);
		this.trainsState = new TrainsState(mergedTrainAB);

		int stopsAfterHyd = mergedTrainAB.size();

		// TRAIN_AB,engine,engine
		Boolean either = (stopsAfterHyd > 3) ? printMessage(message + String.join(" ", mergedTrainAB))
				: printMessage(message + journeyEnd);

	}

	public TrainsState getTrainsState() {
		return trainsState;
	}

	public List<String> getTrainA() {
		return trainsState.getTrainA();
	}

	public List<String> getTrainB() {
		return trainsState.getTrainB();
	}

	@Override
	public IState getNextState() {
		return null;
	}
}
