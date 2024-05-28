package application;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class SpecialBorderPane extends BorderPane implements Serializable {
	private static final long serialVersionUID = 4985513871360703710L;

	public SpecialBorderPane() {
		super();
	}

	public SpecialBorderPane(Node arg0) {
		super(arg0);
	}

	public SpecialBorderPane(Node arg0, Node arg1, Node arg2, Node arg3, Node arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
	}

}
