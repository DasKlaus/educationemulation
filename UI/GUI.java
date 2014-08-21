package UI;

import Main.School;
import UI.MainScreens.ShowCourses;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUI extends Application {

	public static BorderPane p = new BorderPane();
	public static School school;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		school = new School();
		school.init();
		p.setTop(new Top());
		p.setCenter(new ShowCourses());
		p.setRight(new Right());
		p.setBottom(new Logger());
		Scene scene = new Scene(p,500,350);
		primaryStage.setScene(scene);
		// TODO: make pretty
		// scene.getStylesheets().add("style.css");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void tick() {
		school.tick();
		p.setTop(new Top());
		((TickBox) p.getCenter()).tick(); // TODO: assure there's always a TickBox there
		for (int i=0; i<((Right) p.getRight()).container.getChildren().size(); i++) {
			((TickBox) ((Right) p.getRight()).container.getChildren().get(i)).tick();
		}
		((Logger) p.getBottom()).tick();
	}
}