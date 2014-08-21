package Main.Helper;

import java.util.Observer;

public interface Tickable extends Observer {
	public void tick();
	public void startYear();	
}
