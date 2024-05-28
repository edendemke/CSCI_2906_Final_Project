package application;

import java.io.Serializable;

import javafx.scene.Node;
import javafx.scene.control.Button;

public class SpecialButton extends Button implements Serializable {
	private static final long serialVersionUID = -7263340010697782256L;

	public SpecialButton() {
		super();
	}

	public SpecialButton(String arg0) {
		super(arg0);
	}

	public SpecialButton(String arg0, Node arg1) {
		super(arg0, arg1);
	}

}
