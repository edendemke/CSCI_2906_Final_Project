package application;

import java.io.InputStream;
import java.io.Serializable;

import javafx.scene.image.Image;

public class SpecialImage extends Image implements Serializable {
	private static final long serialVersionUID = 4077453238089649490L;
	
	public SpecialImage() {
        super("ALogo.jpg");
    }

	public SpecialImage(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SpecialImage(InputStream arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SpecialImage(String arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SpecialImage(String arg0, double arg1, double arg2, boolean arg3, boolean arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		// TODO Auto-generated constructor stub
	}

	public SpecialImage(InputStream arg0, double arg1, double arg2, boolean arg3, boolean arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		// TODO Auto-generated constructor stub
	}

	public SpecialImage(String arg0, double arg1, double arg2, boolean arg3, boolean arg4, boolean arg5) {
		super(arg0, arg1, arg2, arg3, arg4, arg5);
		// TODO Auto-generated constructor stub
	}

}
