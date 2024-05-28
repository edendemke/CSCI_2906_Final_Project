package application;

import java.io.Serializable;

import javafx.scene.control.TextArea;

public class SpecialTextArea extends TextArea implements Serializable {
	private static final long serialVersionUID = 1523657496993947274L;

	public SpecialTextArea() {
		super();
	}

	public SpecialTextArea(String arg0) {
		super(arg0);
	}

}
