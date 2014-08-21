package Main.Helper;

import java.util.Random;

import Main.Subject;

public class Knowledge {
	public int value;
	public Subject subject;
	
	public Knowledge(Subject subject) {
		Random random = new Random();
		value = random.nextInt(50);
		this.subject = subject;
	}
}
