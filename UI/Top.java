package UI;

import Main.Grade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Top extends VBox {
	public Top() {
		HBox stats = new HBox();
		stats.setSpacing(30);
		// other stats go here if implemented
		stats.getChildren().add(new Text((int) Math.floor(GUI.school.getTime()/12) + "-" + GUI.school.getTime()%12));
		stats.getChildren().add(new Text("Schüler: "+GUI.school.students.size()));
		stats.getChildren().add(new Text("Angestellte: "+GUI.school.staff.size()));
		stats.getChildren().add(new Text("Notenschnitt: "+Grade.average(GUI.school)));
		
		HBox menu = new HBox();
		menu.setSpacing(30);
		// other buttons go here if implemented
		Button tick = new Button("nächster Monat");
		tick.setOnAction
		(
			new EventHandler<ActionEvent>() 
			{
			    @Override public void handle(ActionEvent e) 
			    {
			    	GUI.tick();
			    }
			}
		);
		menu.getChildren().addAll(tick);
		this.getChildren().addAll(stats, menu);
	}
}
