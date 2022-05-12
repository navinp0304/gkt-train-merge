package com.example.geektrust;

import java.util.List;

public class TrainsState {
	List<String> trainA;
	List<String> trainB;
	List<String> mergedTrainAB;

	TrainsState(List<String> trainA, List<String> trainB) {
		this.trainA = trainA;
		this.trainB = trainB;
	}

	TrainsState(List<String> trainAB) {
		this.mergedTrainAB = trainAB;
		this.trainA = trainAB;
		this.trainB = trainAB;
	}

	public List<String> getTrainA() {
		return trainA;
	}

	public List<String> getTrainB() {
		return trainB;
	}

	public List<String> getTrainAB() {
		return mergedTrainAB;
	}

}
