package application;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpecialImageView extends ImageView implements Serializable {
	private static final long serialVersionUID = -4551134497800917441L;

	public SpecialImageView() {
		super();
	}

	public SpecialImageView(String arg0) {
		super(arg0);
	}

	public SpecialImageView(Image arg0) {
		super(arg0);
	}

}
