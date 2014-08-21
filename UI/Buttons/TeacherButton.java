package UI.Buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import Staff.Teacher;
import UI.GUI;
import UI.Right;
import UI.DetailWindows.ShowTeacher;

public class TeacherButton extends Button{

	final public Teacher teacher;
	
	public TeacherButton(String title, Teacher thisTeacher) {
		super(title);
		setMinWidth(100);
		teacher = thisTeacher;
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
			    			ShowTeacher node = (ShowTeacher) right.getChildren().get(l);
				    		if (node.teacher == teacher) {
				    			exists = true;
				    			node.visible = true;
				    			node.show();
				    			node.toFront();
				    		}
			    		} catch (Exception exc) {}
			    	}
			    	if (!exists) {
			    		right.getChildren().add(new ShowTeacher(teacher));
			    	}
			    }
			}
		);
	}
}
