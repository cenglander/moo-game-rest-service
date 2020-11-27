package moo.restservice;

public class PlayerAverage {
	
	private String player;
	private double averageResult;

	public PlayerAverage(String name, double averageResult) {
		this.player = name;
		this.averageResult = averageResult;
	}

	public double getAverageResult() {
		return averageResult;
	}

	public void setAverageResult(double averageResult) {
		this.averageResult = averageResult;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String name) {
		this.player = name;
	}
	
}
