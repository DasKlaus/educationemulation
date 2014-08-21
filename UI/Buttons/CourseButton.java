package UI.Buttons;

import Main.Course;
import UI.GUI;
import UI.Right;
import UI.DetailWindows.ShowCourse;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CourseButton extends Button {

final public Course course;
	
	public CourseButton(String title, Course thisCourse) {
		super(title);
		setMinWidth(100);
		course = thisCourse;
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
			    			ShowCourse node = (ShowCourse) right.getChildren().get(l);
				    		if (node.course == course) {
				    			exists = true;
				    			node.visible = true;
				    			node.show();
				    			node.toFront();
				    		}
			    		} catch (Exception exc) {}
			    	}
			    	if (!exists) {
			    		right.getChildren().add(new ShowCourse(course));
			    	}
			    }
			}
		);
	}
}
