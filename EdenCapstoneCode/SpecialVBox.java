package application;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class SpecialVBox extends VBox implements Serializable {
	private static final long serialVersionUID = -8736152893605408124L;

	public SpecialVBox() {
		super();
	}

	public SpecialVBox(double arg0) {
		super(arg0);
	}

	public SpecialVBox(Node... arg0) {
		super(arg0);
	}

	public SpecialVBox(double arg0, Node... arg1) {
		super(arg0, arg1);
	}

}
