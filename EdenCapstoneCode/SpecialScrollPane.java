package application;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class SpecialScrollPane extends ScrollPane implements Serializable {
	private static final long serialVersionUID = 3419305511741834186L;

	public SpecialScrollPane() {
		super();
	}

	public SpecialScrollPane(Node arg0) {
		super(arg0);
	}

}
