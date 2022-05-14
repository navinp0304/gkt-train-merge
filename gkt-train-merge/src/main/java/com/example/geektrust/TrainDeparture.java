package com.example.geektrust;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class TrainDeparture implements ITrainState {
	@Getter
	ITrainState nextState = null;
	@Getter
	private List<String> trainA;
	@Getter
	private List<String> trainB;
	private String message = "ARRIVAL ";

	TrainDeparture(List<String> trainA, List<String> trainB) {
		List<String> beforeArrivalTrainAHyd = trainA;
		List<String> beforeArrivalTrainBHyd = trainB;
		this.trainA = beforeArrivalTrainAHyd.stream().filter(station -> !stationsConfig.checkBeforeHyd(station))
				.collect(Collectors.toList());
		this.trainB = beforeArrivalTrainBHyd.stream().filter(station -> !stationsConfig.checkBeforeHyd(station))
				.collect(Collectors.toList());
		this.nextState = new TrainArrival(this.trainA, this.trainB);

	}

	@Override
	public void printTrain() {
		System.out.println(message + String.join(" ", trainA));
		System.out.println(message + String.join(" ", trainB));

	}
}
