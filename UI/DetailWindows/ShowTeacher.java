package UI.DetailWindows;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Main.Course;
import Staff.Teacher;
import UI.GUI;
import UI.Right;
import UI.TickBox;

public class ShowTeacher extends VBox implements TickBox {

	public Teacher teacher;
	public Boolean visible;
	
	public ShowTeacher(Teacher teacher) {
		this.teacher = teacher;
		this.visible = true;
		this.setWidth(150);
		show();
	}
	
	@Override
	public void tick() {
		this.show();
	}

	public void show() {
		this.getChildren().clear();
		HBox head = new HBox();
		Button toggle = new Button(teacher.name);
		toggle.setMinWidth(130);
		toggle.setOnAction
		(
			new EventHandler<ActionEvent>() 
			{
			    @Override public void handle(ActionEvent e) 
			    {
			    	visible = !visible;
			    	show();
			    }
			}
		);
		Button close = new Button("x");
		close.setMinWidth(20);
		close.setOnAction
		(
			new EventHandler<ActionEvent>() 
			{
			    @Override public void handle(ActionEvent e) 
			    {
			    	destroy();
			    }
			}
		);
		head.getChildren().addAll(toggle, close);
		this.getChildren().add(head);
		if (visible) {
			ArrayList<Course> courses = Course.getCourses(teacher);
			for (int i=0; i<courses.size(); i++) {
				final Course currentCourse = courses.get(i);
				Button course = new Button(currentCourse.subject.name + (int) (Math.floor(currentCourse.level/36)+1)+currentCourse.name);
				course.setOnAction
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
						    		if (node.course == currentCourse) {
						    			exists = true;
						    			node.visible = true;
						    			node.show();
						    			node.toFront();
						    		}
					    		} catch (Exception exc) {}
					    	}
					    	if (!exists) {
					    		right.getChildren().add(new ShowCourse(currentCourse));
					    	}
					    }
					}
				);
				this.getChildren().add(course);
			}
		}
	}
	
	public void destroy() {
		((VBox) GUI.p.getRight()).getChildren().remove(this);
	}
}
