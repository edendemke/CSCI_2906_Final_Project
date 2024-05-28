package application;

import java.io.Serializable;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class SpecialRectangle extends Rectangle implements Serializable {
	private static final long serialVersionUID = 1878903346596103485L;

	public SpecialRectangle() {
		super();
	}

	public SpecialRectangle(double arg0, double arg1) {
		super(arg0, arg1);
	}

	public SpecialRectangle(double arg0, double arg1, Paint arg2) {
		super(arg0, arg1, arg2);
	}

	public SpecialRectangle(double arg0, double arg1, double arg2, double arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
