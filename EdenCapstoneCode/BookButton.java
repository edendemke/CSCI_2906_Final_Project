package application;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.Serializable;

public class BookButton extends Button implements Serializable {
	private static final long serialVersionUID = 3280903141150543751L;
	private int buttonNumber = 0;
	private int bookPopulateCount = 0;
	private String bookTitle;
	private SpecialVBox newVbox = new SpecialVBox(5);
    private SpecialLabel newBookLbl = new SpecialLabel("Set Title");
    private SpecialTextField newTitleTF = new SpecialTextField("Title");

	public BookButton() {
		super("");

		newTitleTF.setEditable(true);
		Image anotherALogo = new Image("ALogo.jpg");
		ImageView anotherAView = new ImageView(anotherALogo);
		anotherAView.setPreserveRatio(true);
		anotherAView.setFitHeight(150);
		newBookLbl.setText(this.getBookTitle());
		newTitleTF.setText(this.getBookTitle());
		newVbox.getChildren().addAll(anotherAView, newBookLbl, newTitleTF);

		this.setGraphic(newVbox);
	}

	public BookButton(String arg0) {
		super(arg0);
	}

	public BookButton(String arg0, Node arg1) {
		super(arg0, arg1);
	}
	
	public BookButton(int buttonNumber, String bookTitle) {
		super("");
		
		newTitleTF.setEditable(true);
		Image anotherALogo = new Image("ALogo.jpg");
		ImageView anotherAView = new ImageView(anotherALogo);
        anotherAView.setPreserveRatio(true);
        anotherAView.setFitHeight(150);
        newVbox.getChildren().addAll(anotherAView, newBookLbl, newTitleTF);
        
		this.setGraphic(newVbox);
		this.bookTitle = bookTitle;
		this.buttonNumber = buttonNumber;
	}
	
	public void populateButton() {
		newTitleTF.setEditable(true);
		Image anotherALogo = new Image("ALogo.jpg");
		ImageView anotherAView = new ImageView(anotherALogo);
		anotherAView.setPreserveRatio(true);
		anotherAView.setFitHeight(150);
		newBookLbl.setText(this.getBookTitle());
		newTitleTF.setText(this.getBookTitle());
		newVbox.getChildren().addAll(anotherAView, newBookLbl, newTitleTF);

		this.setGraphic(newVbox);
		
		setBookPopulateCount(0);
	}

	public void setEvents() {
		Author author = (Author) this.getParent();

		System.out.println(author + " " + this.getButtonNumber());
		Book currentBook = author.getBookList().get(this.getButtonNumber());

        newTitleTF.setOnAction(o -> {
            currentBook.setBookTitle(newTitleTF.getText());
            newBookLbl.setText(currentBook.getBookTitle());
            currentBook.getBookTitleLbl().setText(currentBook.getBookTitle());
			this.setBookTitle(currentBook.getBookTitle());
        });

        this.setOnAction(t -> {
            Main.getWritingTab().setContent(currentBook);
            setBookPopulateCount(getBookPopulateCount() + 1);
            if(getBookPopulateCount() <= 1) {
            	currentBook.populateBookBorderPane();
            }
            Main.getMainTabPane().getSelectionModel().select(Main.getWritingTab());
        });
	}

	public int getBookPopulateCount() {
		return bookPopulateCount;
	}

	public void setBookPopulateCount(int bookPopulateCount) {
		this.bookPopulateCount = bookPopulateCount;
	}

	public SpecialVBox getNewVbox() {
		return newVbox;
	}

	public int getButtonNumber() {
		return buttonNumber;
	}

	public void setButtonNumber(int buttonNumber) {
		this.buttonNumber = buttonNumber;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Override
	public String toString() {
		return this.getBookTitle();
	}
}
