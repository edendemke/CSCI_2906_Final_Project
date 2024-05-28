package application;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Chapter extends SpecialBorderPane implements Comparable<Chapter>, Serializable {
	private static final long serialVersionUID = -531151007223393442L;
	private int chNumber = 0;
    private String title = "Title";
    private String fileName = "Ch" + chNumber + title + ".txt";
    private String noteFileName = "NotesCh" + chNumber + title + ".txt";
    private File chFile = new File(fileName);
    private File chNoteFile = new File(noteFileName);
    private int chWordCount = 0;
    private SpecialTextArea notesTA = new SpecialTextArea();
    private SpecialTextArea chapterTA = new SpecialTextArea();
    private SpecialScrollPane scrollPane = new SpecialScrollPane(chapterTA);
    private SpecialScrollPane notesScrollPane = new SpecialScrollPane(notesTA);
    private SpecialTextField chWordCountTF = new SpecialTextField();
    private SpecialTextField titleTF = new SpecialTextField(this.getTitle());
    private SpecialRadioButton draftRB = new SpecialRadioButton("Draft");
    private SpecialRadioButton finishedRB = new SpecialRadioButton("Finished for now");
    private SpecialRadioButton workingRB = new SpecialRadioButton("Still working on it");
    private SpecialRectangle rectangle = new SpecialRectangle(16, 16);
    private OrderableTab tab = new OrderableTab();
    private SpecialLabel tabLabel = new SpecialLabel();
    private String rectangleColor = "red";
    
    //todo when saving, check if another chapter (in any book) has the same file name, if so rename chapter file name before saving
    // (for the off chance that another book has a chapter with the same number and title)

    Chapter(){

    }

	Chapter(String title) {
        this.title = title;
    }

    public Chapter addNewChapter(Book testBook, OrderableTabPane chapterTabPane) {
        chapterTA.setEditable(true);
        chapterTA.setOnKeyTyped(e -> calculateChWordCount());
        chapterTA.setPrefColumnCount(50);
        chapterTA.setPrefRowCount(35);
        chapterTA.setWrapText(true);

        SpecialHBox writingHbox = new SpecialHBox(15);
        writingHbox.getChildren().addAll(new SpecialLabel(" "), scrollPane);
        writingHbox.setAlignment(Pos.CENTER);
        this.setCenter(writingHbox);

        SpecialVBox rightVbox = new SpecialVBox(5);
        SpecialLabel titleLbl = new SpecialLabel("Chapter Title:");
        titleTF.setEditable(true);
        titleTF.setPrefColumnCount(10);
        draftRB.setSelected(true);
        ToggleGroup group = new ToggleGroup();
        draftRB.setToggleGroup(group);
        finishedRB.setToggleGroup(group);
        workingRB.setToggleGroup(group);
        SpecialLabel chWordCountLbl = new SpecialLabel("Chapter Word Count:");
        chWordCountTF.setEditable(false);
        chWordCountTF.setPrefColumnCount(10);
        SpecialLabel notesLbl = new SpecialLabel("Notes:");
        notesTA.setEditable(true);
        notesTA.setWrapText(true);
        notesTA.setPrefRowCount(20);
        notesTA.setPrefColumnCount(25);

        rightVbox.getChildren().addAll(titleLbl, titleTF, draftRB, workingRB, finishedRB,
                chWordCountLbl, chWordCountTF, notesLbl, notesScrollPane);
        this.setRight(rightVbox);
        
        draftRB.setOnAction(e -> {
        	//make rectangle red
        	this.getRectangle().setFill(Color.RED);
        	this.setRectangleColor("red");
        	System.out.println(this.getRectangleColor());
        });
        
        workingRB.setOnAction(e -> {
        	//make rectangle yellow
        	this.getRectangle().setFill(Color.YELLOW);
        	this.setRectangleColor("yellow");
        	System.out.println(this.getRectangleColor());
        });
        
        finishedRB.setOnAction(e -> {
        	//make rectangle green
        	this.getRectangle().setFill(Color.GREEN);
        	this.setRectangleColor("green");
        	System.out.println(this.getRectangleColor());
        });

        tab.setClosable(false);
        tabLabel.setText("Ch " +
                this.getChNumber() + ": " + this.getTitle());
        this.getRectangle().setFill(Color.RED);
        tabLabel.setGraphic(rectangle);
        tabLabel.setRotate(90);
        StackPane stp = new StackPane(new Group(tabLabel));
        tab.setGraphic(stp);
        tab.setContent(this);
        chapterTabPane.getTabs().add(tab);

        titleTF.setOnAction(e -> {
            String newTitle = titleTF.getText().trim();
            this.setTitle(newTitle);
            tabLabel.setText("Ch " + this.getChNumber() + ": " + this.getTitle());
        });

        return this;
    }

    public void uploadChapters(OrderableTabPane tabPane) {
    	SpecialTextArea chapterTA1 = this.getChapterTA();
    	this.setChapterTA(chapterTA1);
        chapterTA.setEditable(true);
        chapterTA.setOnKeyTyped(e -> calculateChWordCount());
        chapterTA.setPrefColumnCount(50);
        chapterTA.setPrefRowCount(35);
        chapterTA.setWrapText(true);
        SpecialScrollPane scrollPane1 = new SpecialScrollPane(chapterTA);

        SpecialHBox writingHbox = new SpecialHBox(15);
        writingHbox.getChildren().addAll(new SpecialLabel(" "), scrollPane1);
        writingHbox.setAlignment(Pos.CENTER);
        this.setCenter(writingHbox);

        SpecialVBox rightVbox = new SpecialVBox(5);
        SpecialLabel titleLbl = new SpecialLabel("Chapter Title:");
        titleTF.setEditable(true);
        titleTF.setPrefColumnCount(10);
        titleTF.setText(this.getTitle());
        SpecialRadioButton draftRB = new SpecialRadioButton("Draft");
        SpecialRadioButton finishedRB = new SpecialRadioButton("Finished for now");
        SpecialRadioButton workingRB = new SpecialRadioButton("Still working on it");
        ToggleGroup group = new ToggleGroup();
        draftRB.setToggleGroup(group);
        finishedRB.setToggleGroup(group);
        workingRB.setToggleGroup(group);
        SpecialLabel chWordCountLbl = new SpecialLabel("Chapter Word Count:");
        chWordCountTF.setEditable(false);
        chWordCountTF.setPrefColumnCount(10);
        chWordCountTF.setText(this.getChWordCount() + "");
        SpecialLabel notesLbl = new SpecialLabel("Notes:");
        SpecialTextArea notesTA1 = this.getNotesTA();
        this.setNotesTA(notesTA1);
        notesTA.setEditable(true);
        notesTA.setWrapText(true);
        notesTA.setPrefRowCount(20);
        notesTA.setPrefColumnCount(25);
        SpecialScrollPane notesScrollPane1 = new SpecialScrollPane(notesTA);

        rightVbox.getChildren().addAll(titleLbl, titleTF, draftRB, workingRB, finishedRB,
                chWordCountLbl, chWordCountTF, notesLbl, notesScrollPane1);
        this.setRight(rightVbox);

        titleTF.setOnAction(e -> {
            String newTitle = titleTF.getText().trim();
            this.setTitle(newTitle);
            tabLabel.setText("Ch " + this.getChNumber() + ": " + this.getTitle());
        });

        OrderableTab tab = new OrderableTab();
        tab.setClosable(false);
        
        tabLabel.setText("Ch " +
                this.getChNumber() + ": " + this.getTitle());
        
        SpecialRectangle rectangleNew = new SpecialRectangle(16, 16);
        this.setRectangle(rectangleNew);
        if (this.getRectangleColor().equalsIgnoreCase("red") || this.getRectangleColor().equals(""))  {
        	draftRB.setSelected(true);
        	this.getRectangle().setFill(Color.RED);
        } else if (this.getRectangleColor().equalsIgnoreCase("yellow")) {
        	workingRB.setSelected(true);
        	this.getRectangle().setFill(Color.YELLOW);
        } else if (this.getRectangleColor().equalsIgnoreCase("green")) {
        	finishedRB.setSelected(true);
        	this.getRectangle().setFill(Color.GREEN);
        }
        
        tabLabel.setGraphic(this.getRectangle());
        tabLabel.setRotate(90);
        StackPane stp = new StackPane(new Group(tabLabel));
        tab.setGraphic(stp);
        tab.setContent(this);
        tabPane.getTabs().add(tab);
        
        draftRB.setOnAction(e -> {
        	Color red = Color.RED;
            this.getRectangle().setFill(red);
            this.setRectangleColor("red");
            System.out.println(this.getRectangleColor());
        });

        workingRB.setOnAction(e -> {
        	Color yellow = Color.YELLOW;
            this.getRectangle().setFill(yellow);
            this.setRectangleColor("yellow");
            System.out.println(this.getRectangleColor());
        });

        finishedRB.setOnAction(e -> {
        	Color green = Color.GREEN;
            this.getRectangle().setFill(green);
            this.setRectangleColor("green");
            System.out.println(this.getRectangleColor());
        });
    }
    
    public void calculateChWordCount() {
    	int count = 0;
    	
    	try (
    		Scanner scanner = new Scanner(this.getChapterTA().getText());
    	) {
    		while (scanner.hasNext()) {
    			String string = scanner.next();
    			count++;
    		}
    		this.setChWordCount(count);
    		this.getChWordCountTF().setText(this.getChWordCount() + "");
    		//todo somehow update total word count in Book
    		
    		//Book.updateTotalWordCount();
    		//Book currentBook = (Book) this.getTab().getGraphic().getParent().getParent().getParent().getParent().getParent();
//    		Book currentBook = (Book) this.getParent();
//    		currentBook.updateTotalWordCount();
    	}
    }

	public int getChNumber() {
        return chNumber;
    }

    public String getRectangleColor() {
		return rectangleColor;
	}

	public void setRectangleColor(String rectangleColor) {
		this.rectangleColor = rectangleColor;
	}

	public void setRectangle(SpecialRectangle rectangle) {
		this.rectangle = rectangle;
	}

	public void setChNumber(int chNumber) {
        this.chNumber = chNumber;
        setFileName();
        setNoteFileName();
        setTabLabel();
    }

    public void setNotesTA(SpecialTextArea notesTA) {
		this.notesTA = notesTA;
	}

	public void setChapterTA(SpecialTextArea chapterTA) {
		this.chapterTA = chapterTA;
	}

	public SpecialTextArea getNotesTA() {
        return this.notesTA;
    }

    public SpecialTextArea getChapterTA() {
        return this.chapterTA;
    }

    public SpecialScrollPane getScrollPane() {
        return scrollPane;
    }

    public SpecialTextField getChWordCountTF() {
        return chWordCountTF;
    }

    public SpecialRadioButton getDraftRB() {
        return draftRB;
    }

    public SpecialRadioButton getWorkingRB() {
		return workingRB;
	}

	public SpecialRadioButton getFinishedRB() {
        return finishedRB;
    }

    public SpecialRectangle getRectangle() {
        return rectangle;
    }

    public OrderableTab getTab() {
        return tab;
    }

    public SpecialLabel getTabLabel() {
		return tabLabel;
	}

	public void setTabLabel() {
		tabLabel.setText("Ch " + this.getChNumber() + ": " + this.getTitle());
	}

	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        setFileName();
        setNoteFileName();
    }

    public String getFileName() {
        return "Ch" + getChNumber() + getTitle() + ".txt";
    }

    public void setFileName() {
        this.fileName = "Ch" + getChNumber() + getTitle() + ".txt";
    }

    public String getNoteFileName() {
        return "NotesCh" + getChNumber() + getTitle() + ".txt";
    }

    public void setNoteFileName() {
        this.noteFileName = "NotesCh" + getChNumber() + getTitle() + ".txt";
    }

    public File getChFile() {
        return chFile;
    }

    public void setChFile(File chFile) {
        this.chFile = chFile;
    }

    public File getChNoteFile() {
        return chNoteFile;
    }

    public void setChNoteFile(File chNoteFile) {
        this.chNoteFile = chNoteFile;
    }

    public int getChWordCount() {
        return chWordCount;
    }

    public void setChWordCount(int chWordCount) {
        this.chWordCount = chWordCount;
    }

	@Override
	public int compareTo(Chapter o) {
		if (this.getChNumber() > o.getChNumber()) {
			return 1;
		} else if (this.getChNumber() < o.getChNumber()) {
			return -1;
		} else {
			return 0;
		}
	}
}
