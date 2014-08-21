package Main.Helper;

import java.util.Random;

public class Modifiable {
	
	public int value; // generally: +/-10: normal, +/-20: relevant, +/- 30: extreme
	
	public Modifiable() {
		Random random = new Random();
		value = (int) (random.nextGaussian() * 10);
	}
	
	public Modifiable(int value) {
		this.value = value;
	}

	public void modify(int modifier) {
		// Pursuit of Normalcy (Gaussian standard deviation = 10)
		if (Math.abs(modifier+value)>Math.abs(value)) {
			value = value + modifier - (int) (modifier * (value / 100));
		} else {
			value = value + modifier;
		}
	}
}
