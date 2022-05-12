package com.example.geektrust;

import java.util.List;
import java.util.stream.Collectors;

public class HydArrivalState implements IState {
	TrainsState trainsState;
	StationsConfiguration stationsConfig = new StationsConfiguration();
	private final String message = "ARRIVAL ";

	HydArrivalState(TrainsState trainsState) {

		List<String> beforeArrivalTrainAHyd = trainsState.getTrainA();
		List<String> beforeArrivalTrainBHyd = trainsState.getTrainB();
		List<String> arrivalTrainAHyd = beforeArrivalTrainAHyd.stream()
				.filter(station -> !stationsConfig.checkBeforeHyd(station)).collect(Collectors.toList());
		List<String> arrivalTrainBHyd = beforeArrivalTrainBHyd.stream()
				.filter(station -> !stationsConfig.checkBeforeHyd(station)).collect(Collectors.toList());
		// System.out.println(Arrays.toString(trainAHyd.toArray()));
		System.out.println(message + String.join(" ", arrivalTrainAHyd));
		System.out.println(message + String.join(" ", arrivalTrainBHyd));

		this.trainsState = new TrainsState(arrivalTrainAHyd, arrivalTrainBHyd);
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

		return new HydDepartureState(trainsState);
	}
}
