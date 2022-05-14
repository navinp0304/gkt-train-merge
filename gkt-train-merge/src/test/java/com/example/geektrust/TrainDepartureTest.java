package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TrainDepartureTest {

	List<String> trainAexp = List.of("TRAIN_A", "ENGINE", "NDL", "NDL", "GHY", "NJP", "NGP");
	List<String> trainBexp = List.of("TRAIN_B", "ENGINE", "NJP", "GHY", "AGA", "BPL", "PTA");

	List<String> trainAB = List.of("TRAIN_AB", "ENGINE", "ENGINE", "GHY", "GHY", "NJP", "NJP", "PTA", "NDL", "NDL",
			"AGA", "BPL", "NGP");

	List<String> prefixTrainAexp = List.of("ARRIVAL", "TRAIN_A", "ENGINE", "NDL", "NDL", "GHY", "NJP", "NGP");
	List<String> prefixTrainBexp = List.of("ARRIVAL", "TRAIN_B", "ENGINE", "NJP", "GHY", "AGA", "BPL", "PTA");
	List<String> prefixtrainAB = List.of("DEPARTURE", "TRAIN_AB", "ENGINE", "ENGINE", "GHY", "GHY", "NJP", "NJP", "PTA",
			"NDL", "NDL", "AGA", "BPL", "NGP");

	@Test
	void testTrainMerged() {
		TrainArrival trainState = new TrainArrival(trainAexp, trainBexp);
		assertAll("check train depature state", () -> assertNotEquals(trainState, null),
				() -> assertEquals(trainState.getTrainAB(), trainAB));
	}

	@Test
	void testPrintTrain() {
		TrainArrival trainState = new TrainArrival(trainAexp, trainBexp);
		PrintStream outStream = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		trainState.printTrain();
		System.setOut(outStream);
		String outString = outContent.toString();
		String[] observed = outString.split("\n");
		List<String> obsB = Arrays.asList(observed[0].split(" "));
		assertAll("check observed", () -> assertEquals(obsB, prefixtrainAB));

	}

	@Test
	void printJourneyEnded() {
		List<String> listA = List.of("TRAIN_A", "ENGINE", "KRN", "SLM", "BLR");
		List<String> listB = List.of("TRAIN_B", "ENGINE", "MAO");

		TrainDeparture unmergedState = new TrainDeparture(listA, listB);
		TrainArrival journeyTrainState = new TrainArrival(unmergedState.getTrainA(), unmergedState.getTrainB());

		PrintStream outStream = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));

		journeyTrainState.printTrain();
		System.setOut(outStream);

		String outString = outContent.toString();
		String[] observed = outString.split("\n");

		List<String> obsB = Arrays.asList(observed[0].split(" "));
		String exp = "DEPARTURE JOURNEY_ENDED";
		List<String> expJourneyEnded = Arrays.asList(exp.split(" "));

		assertAll("check observed", () -> assertEquals(obsB, expJourneyEnded));
	}

	@Test
	void testGetNextState() {
		TrainArrival trainState = new TrainArrival(trainAexp, trainBexp);
		assertEquals(trainState.getNextState(), null);
	}

}
