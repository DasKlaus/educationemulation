package Main.Helper;

import java.util.Observable;

public class Ticker extends Observable {
	private int time;
	
	public Ticker() {
		time = 0;
	}
	
	public void tick() {
		time++;
		setChanged();
		Boolean newYear = (time%12==0)?true:false;
		notifyObservers(newYear);
	}
	
	public int getTime() {
		return time;
	}
}