package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class TrainArrivalTest {
	List<String> trainA = List.of("TRAIN_A", "ENGINE", "NDL", "NDL", "KRN", "GHY", "SLM", "NJP", "NGP", "BLR");
	List<String> trainB = List.of("TRAIN_B", "ENGINE", "NJP", "GHY", "AGA", "PNE", "MAO", "BPL", "PTA");
	TrainDeparture trainState = new TrainDeparture(trainA, trainB);
	List<String> trainAexp = List.of("TRAIN_A", "ENGINE", "NDL", "NDL", "GHY", "NJP", "NGP");
	List<String> trainBexp = List.of("TRAIN_B", "ENGINE", "NJP", "GHY", "AGA", "BPL", "PTA");

	List<String> prefixTrainAexp = List.of("ARRIVAL", "TRAIN_A", "ENGINE", "NDL", "NDL", "GHY", "NJP", "NGP");
	List<String> prefixTrainBexp = List.of("ARRIVAL", "TRAIN_B", "ENGINE", "NJP", "GHY", "AGA", "BPL", "PTA");

	@Test
	void testTrainUnMerged() {
		assertNotEquals(trainState, null);
	}

	@Test
	void testPrintTrain() {
		PrintStream outStream = System.out;
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		trainState.printTrain();
		System.setOut(outStream);
		String outString = outContent.toString();
		String[] observed = outString.split("\n");
		List<String> obsA = Arrays.asList(observed[0].split(" "));
		List<String> obsB = Arrays.asList(observed[1].split(" "));
		assertAll("check observed", () -> assertEquals(obsA, prefixTrainAexp),
				() -> assertEquals(obsB, prefixTrainBexp));
	}

	@Test
	void testGetNextState() {
		assertAll("check next state", () -> assertNotEquals(trainState.getNextState(), null),
				() -> assertEquals(trainState.getNextState() instanceof TrainArrival, true));
	}

	@Test
	void testGetTrainA() {
		assertEquals(trainState.getTrainA(), trainAexp);
	}

	@Test
	void testGetTrainB() {
		assertEquals(trainState.getTrainB(), trainBexp);
	}

}
