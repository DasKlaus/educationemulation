package UI;

import Main.LogEvent;
import UI.Buttons.CourseButton;
import UI.Buttons.StudentButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Logger extends VBox implements TickBox{
	
	// TODO: This *has* to have its own menu as well as a ScrollPane
	HBox menu;
	ScrollPane container;
	
	public Logger() {
		menu = new HBox();
		container = new ScrollPane();
		container.setMinHeight(200);
		container.setMaxHeight(300);
		container.setContent(new VBox());
		this.getChildren().add(container);
		tick();
	}
	
	public void tick() {
		((VBox) container.getContent()).getChildren().clear();
		// TODO: for better performance: loop backwards and stop when time doesn't match
		// TODO: order and/or filter by date, type and subject
		for (int i=0; i<GUI.school.log.size(); i++) {
			HBox line = new HBox();
			if (GUI.school.log.get(i).time == GUI.school.getTime()) {
				final LogEvent event = GUI.school.log.get(i);
				switch (event.type) {
				case NEWSTUDENTS:
					line.getChildren().add(new Text(event.intValue+" neue Schüler aufgenommen: "));
					for (int j=0; j<event.students.size(); j++) {
						line.getChildren().add(new StudentButton(event.students.get(i).name, event.students.get(i)));
					}
					break;
				case RETENTION:
					line.getChildren().addAll(new StudentButton(event.students.get(0).name, event.students.get(0)),
							new Text(" ist in "),
							new CourseButton(event.courses.get(0).subject.name + " " + (int) (Math.floor(event.courses.get(0).level/36)+1)+event.courses.get(0).name, event.courses.get(0)),
							new Text(" nicht versetzt worden und belegt jetzt "),
							new CourseButton(event.courses.get(1).subject.name + " " + (int) (Math.floor(event.courses.get(1).level/36)+1)+event.courses.get(1).name, event.courses.get(1)));
					break;
				case MOVEFASTER:
					line.getChildren().addAll(new StudentButton(event.students.get(0).name, event.students.get(0)),
							new Text(" war in "),
							new CourseButton(event.courses.get(0).subject.name + " " + (int) (Math.floor(event.courses.get(0).level/36)+1)+event.courses.get(0).name, event.courses.get(0)),
							new Text(" zu langweilig und belegt jetzt "),
							new CourseButton(event.courses.get(1).subject.name + " " + (int) (Math.floor(event.courses.get(1).level/36)+1)+event.courses.get(1).name, event.courses.get(1)));
					break;
				case MOVESLOWER:
					line.getChildren().addAll(new StudentButton(event.students.get(0).name, event.students.get(0)),
							new Text(" war in "),
							new CourseButton(event.courses.get(0).subject.name + " " + (int) (Math.floor(event.courses.get(0).level/36)+1)+event.courses.get(0).name, event.courses.get(0)),
							new Text(" überfordert und belegt jetzt "),
							new CourseButton(event.courses.get(1).subject.name + " " + (int) (Math.floor(event.courses.get(1).level/36)+1)+event.courses.get(1).name, event.courses.get(1)));
					break;
				case DROPCOURSE:
					line.getChildren().addAll(new StudentButton(event.students.get(0).name, event.students.get(0)),
							new Text(" hat "),
							new CourseButton(event.courses.get(0).subject.name + " " + (int) (Math.floor(event.courses.get(0).level/36)+1)+event.courses.get(0).name, event.courses.get(0)),
							new Text(" verlassen und belegt jetzt "+event.courses.get(0).subject.name+" nicht mehr."));
					break;
				case STARTCOURSE:
					if (event.boolValue) {
						line.getChildren().addAll(new CourseButton(event.courses.get(0).subject.name + " " + (int) (Math.floor(event.courses.get(0).level/36)+1)+event.courses.get(0).name, event.courses.get(0)),
								new Text(" beginnt."));
					} else {
						line.getChildren().add(new Text(event.subject.name + " kann aufgrund fehlender Lehrkräfte nicht angeboten werden"));
					}
					break;
				default:
					break;
				}
				((VBox) container.getContent()).getChildren().add(line);
			}
		}
	}
}
