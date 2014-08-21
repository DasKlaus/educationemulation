package UI.MainScreens;

import java.util.ArrayList;

import Main.Course;
import Staff.Teacher;
import UI.GUI;
import UI.TickBox;
import UI.Buttons.CourseButton;
import UI.Buttons.TeacherButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ShowCourses extends HBox implements TickBox {
	public ShowCourses() {
		this.setSpacing(10);
		this.show();
	}
	
	public void tick() {
		this.getChildren().clear();
		this.show();
	}
	
	public void show() {
		for (int i=0; i<GUI.school.subjects.size(); i++) {
			VBox subject = new VBox();
			subject.setSpacing(5);
			VBox head = new VBox();
			head.setMinHeight(100);
			head.setMaxHeight(100);
			head.getChildren().add(new Text(GUI.school.subjects.get(i).name));
			for (int j=0; j<GUI.school.staff.size(); j++) {
				if (GUI.school.staff.get(j).getClass().equals((new Teacher()).getClass())) {
					Teacher currentTeacher = (Teacher) GUI.school.staff.get(j);
					if (currentTeacher.subjects.contains(GUI.school.subjects.get(i))) {
						head.getChildren().add(new TeacherButton(currentTeacher.name, currentTeacher));
					}
				}
			}
			HBox body = new HBox();
			subject.getChildren().addAll(head, body);
			int requirement = 0;
			for (int j=0; j<GUI.school.subjects.get(i).requirements.size(); j++) {
				if (GUI.school.subjects.get(i).requirements.get(j).level > requirement) {
					requirement = GUI.school.subjects.get(i).requirements.get(j).level;
				}
			}
			
			for (int j=1; j<6; j++) {
				ArrayList<Course> courses = Course.getCourses(GUI.school.subjects.get(i), j);
				if (courses.size()>0) {
					Pane container = new Pane();
					// position courses absolutely
					for (int k=0; k<courses.size(); k++) {
						Course currentCourse = courses.get(k);
						CourseButton course = new CourseButton((int) (Math.floor(currentCourse.level/36)+1+Math.ceil(requirement/36))+currentCourse.name, currentCourse);
						course.setMinSize(36, j*12);
						course.setMaxSize(36, j*12);
						container.getChildren().add(course);
						course.relocate(36*"abcdefghijklmnopqrstuvwxyz".indexOf(currentCourse.name), // x-position 
								requirement+(currentCourse.level-(j*(GUI.school.getTime()%12)))); // y-position
					}
					body.getChildren().add(container);
				}
			}
			this.getChildren().add(subject);
		}
	}
}
