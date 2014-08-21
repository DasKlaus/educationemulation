package Main.Helper;

import Main.Subject;

public class Talent {
	public Modifiable value;
	public Subject subject;
	
	public Talent(Subject subject, Modifiable value) {
		this.subject = subject;
		this.value = value;
	}
}
