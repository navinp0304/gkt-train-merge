package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class StationsConfigurationTest {
	StationsConfiguration stationConfig = new StationsConfiguration();

	@Test
	void testCheckBeforeHyd() {
		assertEquals(stationConfig.checkBeforeHyd("CHN"), true);
	}

	@Test
	void testGetStationsDist() {
		assertAll("check stations dist", () -> assertEquals(stationConfig.getStationsDist("ENGINE"), 0),
				() -> assertEquals(stationConfig.getStationsDist("GHY"), 1),
				() -> assertEquals(stationConfig.getStationsDist("NGP"), 8));
	}

	@Test
	void testRun() {

		InputStream stdin = System.in;
		PrintStream stdout = System.out;
		String inputData = "TRAIN_A ENGINE NDL NDL KRN GHY SLM NJP NGP BLR\n"
				+ "TRAIN_B ENGINE NJP GHY AGA PNE MAO BPL PTA\n";

		String expected = "ARRIVAL TRAIN_A ENGINE NDL NDL GHY NJP NGP\n"
				+ "ARRIVAL TRAIN_B ENGINE NJP GHY AGA BPL PTA\n"
				+ "DEPARTURE TRAIN_AB ENGINE ENGINE GHY GHY NJP NJP PTA NDL NDL AGA BPL NGP\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setIn(new ByteArrayInputStream(inputData.getBytes()));

		stationConfig.run("sample_input/input1.txt");

		System.setIn(stdin);
		System.setOut(stdout);
		assertEquals(expected.trim(), outContent.toString());
	}

	@Test
	void testRunNoFile() {
		StationsConfiguration nostationConfig = new StationsConfiguration();

		String inputData = "TRAIN_A ENGINE NDL NDL KRN GHY SLM NJP NGP BLR\n"
				+ "TRAIN_B ENGINE NJP GHY AGA PNE MAO BPL PTA\n";

		String expected = "FILE NOT FOUND";
		String errContent = "";
		try {
			nostationConfig.run("sample_input/nofile.txt");
		} catch (IllegalArgumentException ex) {
			errContent = ex.getMessage();
		}

		assertEquals(expected.trim(), errContent);
	}

	@Test
	void testGetHydStationCode() {
		assertEquals(stationConfig.getHydStationCode(), "HYB");
	}

	@Test
	void testGetTrainAName() {
		assertEquals(stationConfig.getTrainAName(), "TRAIN_A");
	}

	@Test
	void testGetTrainBName() {
		assertEquals(stationConfig.getTrainBName(), "TRAIN_B");
	}

	@Test
	void testGetTrainABName() {
		assertEquals(stationConfig.getTrainABName(), "TRAIN_AB");
	}

}
