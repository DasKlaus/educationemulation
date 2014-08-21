package UI.DetailWindows;

import java.util.ArrayList;

import Main.Course;
import Main.Grade;
import Main.Student;
import UI.GUI;
import UI.TickBox;
import UI.Buttons.StudentButton;
import UI.Buttons.TeacherButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ShowCourse extends VBox implements TickBox {

	public Course course;
	public Boolean visible;
	
	public ShowCourse(Course course) {
		this.course = course;
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
		Button toggle = new Button(course.subject.name+(int) (Math.floor(course.level/36)+1)+course.name+" "+Grade.average(course)); // TODO: add requirements to year
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
			this.getChildren().add(new TeacherButton(course.teacher.name, course.teacher));
			for (int i=0; i<course.students.size(); i++) {
				Student student = course.students.get(i);
				ArrayList<Grade> grades = Grade.getGrades(student, course);
				String strGrades = "";
				for (int j=0; j<grades.size(); j++) {
					strGrades += " "+grades.get(j).value;
				}
				this.getChildren().add(new StudentButton(student.name+strGrades, student));
			}
		}
	}
	
	public void destroy() {
		((VBox) GUI.p.getRight()).getChildren().remove(this);
	}
}
