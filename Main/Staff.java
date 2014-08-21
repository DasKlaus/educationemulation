package Main;

import java.util.Observable;
import java.util.Random;

import Main.Helper.Tickable;
import UI.GUI;

public class Staff implements Tickable {
	
	public final String name;
	
	public Staff() {
		Random random = new Random();
		// generate Name
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		int length = random.nextInt(15)+3;
		for (int i=0; i<length; i++) {
			sb.append(chars[random.nextInt(chars.length)]); 
		}
		name = sb.toString();
		GUI.school.time.addObserver(this);
	}
	
	public void tick() {
		// detect effects
	}
	
	public void startYear() {
		// nothing
	}
	
	@Override
	public void update(Observable time, Object newYear) {
		tick();
		if ((Boolean) newYear) {
			startYear();
		}
	}
}
