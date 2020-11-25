package moo.restservice;

public class PlayerAverage {
	
//	private int playerId;
	private String name;
	private double averageResult;

	public PlayerAverage(String name, double averageResult) {
		this.name = name;
		this.averageResult = averageResult;
	}

//	public int getPlayerId() {
//		return playerId;
//	}
//
//	public void setPlayerId(int playerId) {
//		this.playerId = playerId;
//	}

	public double getAverageResult() {
		return averageResult;
	}

	public void setAverageResult(double averageResult) {
		this.averageResult = averageResult;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
