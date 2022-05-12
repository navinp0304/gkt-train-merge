package com.example.geektrust;

import java.util.Map;
import java.util.Set;

public class StationsConfiguration {
	private final Set<String> stationsBeforeHyd = Set.of("CHN", "SLM", "BLR", "KRN", "TVC", "SRR", "MAQ", "MAO", "PNE");

	private final Map<String, Integer> stationsDist = Map.of("ENGINE", 0, "GHY", 1, "NJP", 2, "PTA", 3, "NDL", 4, "AGA",
			5, "BPL", 6, "ITJ", 7, "NGP", 8);

	private final String hydStationCode = "HYB";

	private final String trainAName = "TRAIN_A";
	private final String trainBName = "TRAIN_B";

	public final String getTrainAName() {
		return trainAName;
	}

	public final String getTrainBName() {
		return trainBName;
	}

	public final String getHydStationCode() {
		return hydStationCode;
	}

	public final boolean checkBeforeHyd(String stationName) {
		return stationsBeforeHyd.contains(stationName);
	}

	public final int getStationsDist(String stationName) {
		return stationsDist.get(stationName);

	}
}

/*
 * public static void main(String[] args) { String trainAcmd =
 * "TRAIN_A ENGINE NDL NDL GHY NJP NGP"; String trainBcmd =
 * "TRAIN_B ENGINE NJP GHY AGA BPL PTA"; String trainAcmd =
 * "TRAIN_A ENGINE SLM BLR KRN HYB SLM NGP ITJ"; String trainBcmd =
 * "TRAIN_B ENGINE SRR MAO NJP PNE PTA"; StationsConfiguration stationsConfig =
 * new StationsConfiguration(); List<String> trainA =
 * stationsConfig.parseTrain(trainAcmd); List<String> trainB =
 * stationsConfig.parseTrain(trainBcmd); TrainsState trainsState = new
 * TrainsState(trainA, trainB); HydArrival arrival = new
 * HydArrival(trainsState); trainA = arrival.getTrainA(); trainB =
 * arrival.getTrainB(); trainsState = arrival.getTrainsState(); HydDeparture
 * dept = new HydDeparture(trainsState); trainsState = dept.getTrainsState(); }
 * 
 */
