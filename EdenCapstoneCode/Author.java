package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import java.io.Serializable;
import java.util.ArrayList;

public class Author extends FlowPane implements Serializable {
    private static final long serialVersionUID = -6523779535879274831L;
    private ArrayList<Book> bookList = new ArrayList<>();
    private ArrayList<BookButton> bookBtnList = new ArrayList<>();
    private String authorName = "Example Author";
    private SpecialVBox initialVbox = new SpecialVBox(5);
    private SpecialLabel addBookLbl = new SpecialLabel("Add New Book");

    public Author(){
        super();
        makeFlowPane();
    }

    public Author(String name) {
        super();
        this.authorName = name;
        makeFlowPane();
    }

    public void makeFlowPane() {
        this.setVgap(15);
        this.setHgap(15);
        this.setPrefWidth(800);
        //this.setPrefWidth(1300);
        Image aLogo = new Image("ALogo.jpg");
        ImageView aLogoView = new ImageView(aLogo);
        aLogoView.setPreserveRatio(true);
        aLogoView.setFitHeight(200);
        SpecialLabel nameLbl = new SpecialLabel("Author: " + this.getAuthorName());
        initialVbox.getChildren().addAll(nameLbl, aLogoView, addBookLbl);
        SpecialButton addNewBookBtn = new SpecialButton("", initialVbox);
        this.getChildren().add(addNewBookBtn);
        
        SpecialLabel deleteBookLbl = new SpecialLabel("To DELETE a book, type its\ntitle EXACTLY as it appears:");
        SpecialTextField deleteBookTF = new SpecialTextField();
        deleteBookTF.setEditable(true);
        SpecialVBox deleteBookVbox = new SpecialVBox(5);
        deleteBookVbox.getChildren().addAll(deleteBookLbl, deleteBookTF);
        SpecialButton deleteBookBtn = new SpecialButton("", deleteBookVbox);
        this.getChildren().add(deleteBookBtn);

        for (int i = 0; i < this.getBookList().size(); i++) {
            BookButton bookButton = this.getBookBtnList().get(i);
            bookButton.populateButton();
            this.getChildren().add(2, bookButton);
            bookButton.setEvents();
        }
        
        deleteBookBtn.setOnAction(e -> {
        	Book bookToDelete;
            BookButton buttonToDelete;
        	for(int i = 0; i < this.getBookList().size(); i++) {
        		bookToDelete = this.getBookList().get(i);

        		if(bookToDelete.getBookTitle().equals(deleteBookTF.getText().trim())) {
        			this.getBookList().remove(i);
        			System.out.println("book theoretically removed from bookList");

                    for(int j = 2; j < this.getChildren().size(); j++) {
                        buttonToDelete = (BookButton) this.getChildren().get(j);

                        if(buttonToDelete.toString().equals(deleteBookTF.getText().trim())){
                            this.getChildren().remove(j);
                            System.out.println("book theoretically removed from flowpane");
                        }
                    }
        		}
        	}
        });

        addNewBookBtn.setOnAction(e -> {
        	this.getBookList().add(new Book("Set Title"));
            int size = this.getBookList().size() - 1;
            System.out.println(size);

            this.getBookBtnList().add(new BookButton(size, "Title"));
            BookButton newBookBtn = this.getBookBtnList().get(this.getBookBtnList().size() - 1);
            this.getChildren().add(2, newBookBtn);
            newBookBtn.setEvents();
        });
    }

    public ArrayList<BookButton> getBookBtnList() {
        return bookBtnList;
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public void setBookList(ArrayList<Book> bookList) {
		this.bookList = bookList;
	}

	public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
