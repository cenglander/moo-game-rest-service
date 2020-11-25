package moo.restservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="resultsmoo")
public class Result {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue
	private int id;
	
	private int result;
	
//	private int player;

//	public Result(Integer result) {
	public Result(int result) {
		this.result = result;
	}
	
	public Result() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

//	public int getPlayer() {
//		return player;
//	}
//
//	public void setPlayer(int player) {
//		this.player = player;
//	}
}
