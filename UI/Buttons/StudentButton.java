package UI.Buttons;

import Main.Student;
import UI.GUI;
import UI.Right;
import UI.DetailWindows.ShowStudent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class StudentButton extends Button {
	
	final public Student student;
	
	public StudentButton(String title, Student thisStudent) {
		super(title);
		setMinWidth(100);
		student = thisStudent;
		setOnAction
		(
			new EventHandler<ActionEvent>() 
			{
			    @Override public void handle(ActionEvent e) 
			    {
			    	VBox right = ((Right) GUI.p.getRight()).container;
			    	Boolean exists = false;
			    	for (int l=0; l<right.getChildren().size(); l++) {
			    		try {
			    			ShowStudent node = (ShowStudent) right.getChildren().get(l);
				    		if (node.student == student) {
				    			exists = true;
				    			node.visible = true;
				    			node.show();
				    			node.toFront();
				    		}
			    		} catch (Exception exc) {}
			    	}
			    	if (!exists) {
			    		right.getChildren().add(new ShowStudent(student));
			    	}
			    }
			}
		);
	}
}
