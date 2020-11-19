package moo.restservice;

public class PlayerAverage {
	
	private Integer playerId;
	private double averageResult;

	public PlayerAverage(Integer playerId, double averageResult) {
		this.playerId = playerId;
		this.averageResult = averageResult;
	}

	public Integer getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	public double getAverageResult() {
		return averageResult;
	}

	public void setAverageResult(double averageResult) {
		this.averageResult = averageResult;
	}

}
