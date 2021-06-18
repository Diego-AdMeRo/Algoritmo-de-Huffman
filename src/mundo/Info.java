package mundo;

public class Info {

	/**
	 * @author Diego Adrián Meneses Romero
	 * @version 1.0
	 */

	/* Atributos */
	private int times;
	private char letter;

	/* Constructor */
	public Info(char letter) {
		this.letter = letter;
		this.times = 1;

	}

	/* Metodos setters & getters */
	public void setLetter(char letter) {
		this.letter = letter;
	}

	public char getLetter() {
		return letter;
	}

	public void upTimes() {
		times += 1;
	}

	public int getTimes() {
		return times;
	}

	public void upTimes(int timesUD) {
		this.times = timesUD;
	}

}