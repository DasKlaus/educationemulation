package UI.DetailWindows;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Main.Course;
import Main.Student;
import UI.GUI;
import UI.TickBox;
import UI.Buttons.CourseButton;

public class ShowStudent extends VBox implements TickBox {

	public Student student;
	public Boolean visible;
	
	public ShowStudent(Student student) {
		this.student = student;
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
		Button toggle = new Button(student.name);
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
			ArrayList<Course> courses = Course.getCourses(student);
			for (int i=0; i<courses.size(); i++) {
				Course currentCourse = courses.get(i);
				this.getChildren().add(new CourseButton(currentCourse.subject.name+" "+(int) (Math.floor(currentCourse.level/36)+1)+currentCourse.name, courses.get(i)));
			}
			
		}
	}
	
	public void destroy() {
		((VBox) GUI.p.getRight()).getChildren().remove(this);
	}
}

