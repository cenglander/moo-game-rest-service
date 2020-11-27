package moo.restservice;

//This class will not be used. 
//Instead using equivalent class GuessFormBean,
//because the only need for the class is when passing a guess from Thymeleaf to the web controller
public class Guess {
	private String value;

	public Guess() {
	}

	public Guess(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}