package application;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class SpecialLabel extends Label implements Serializable {
	
	private static final long serialVersionUID = 9125298604545121015L;

	public SpecialLabel() {
		super();
	}

	public SpecialLabel(String arg0) {
		super(arg0);
	}

	public SpecialLabel(String arg0, Node arg1) {
		super(arg0, arg1);
	}

}
