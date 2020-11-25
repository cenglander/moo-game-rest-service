package moo.restservice;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="players")
public class Player {
	
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue
	private int id;
	
	private String name;
	
//	@OneToMany(mappedBy="player", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="player")
	private List<Result> results;

	public void addResult(int numOfGuesses) {
		results.add(new Result(numOfGuesses));
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Result> getResults() {
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}	

}
