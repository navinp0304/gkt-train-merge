package com.example.geektrust;

public interface ITrainState {
	StationsConfiguration stationsConfig = new StationsConfiguration();

	void printTrain();

	ITrainState getNextState();
}
