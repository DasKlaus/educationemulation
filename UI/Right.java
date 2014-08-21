package UI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class Right extends ScrollPane {

	public VBox container;
	
	public Right() {
		final VBox container = new VBox();
		container.setSpacing(10);
		this.setContent(container);
		this.setMinWidth(200);
		this.container = container;
		
		// always scroll to the last item in the container
		container.heightProperty().addListener(new ChangeListener() {
	        @Override
	        public void changed(ObservableValue ov, Object t, Object t1) {
	           setVvalue(container.getHeight()); 
	        }
	    }) ;
	}
}
