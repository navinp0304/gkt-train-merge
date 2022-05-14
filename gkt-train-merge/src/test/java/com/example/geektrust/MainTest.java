package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class MainTest {

	@Test
	void testInit() {
		testMain();
	}

	@Test
	void testMain() {
		Main mainInstance = new Main();
		String[] args = { "sample_input/input1.txt" };
		Main.main(args);
		mainInstance.init();
		assertNotEquals(mainInstance, null);
	}

}
