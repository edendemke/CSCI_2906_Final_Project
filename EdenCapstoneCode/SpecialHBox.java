package application;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class SpecialHBox extends HBox implements Serializable {
	private static final long serialVersionUID = 1199407072995156474L;

	public SpecialHBox() {
		super();
	}

	public SpecialHBox(double arg0) {
		super(arg0);
	}

	public SpecialHBox(Node... arg0) {
		super(arg0);
	}

	public SpecialHBox(double arg0, Node... arg1) {
		super(arg0, arg1);
	}

}
