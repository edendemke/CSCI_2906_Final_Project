package application;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.TabPane.TabDragPolicy;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

public class Book extends SpecialBorderPane implements Serializable {
    private static final long serialVersionUID = -6051689920193108082L;
	private String bookTitle = "Book Title";
    private int totalWordCount;
    private String bookNoteFileName = bookTitle + "BookNotes.txt";
    private File bookNoteFile = new File(bookNoteFileName);
    private ArrayList<Chapter> chapterList = new ArrayList<>();
    private OrderableTabPane chapterTabPane = new OrderableTabPane();
    private SpecialHBox Hbox = new SpecialHBox(15);
    private SpecialBorderPane chapterBorderPane = new SpecialBorderPane();
    private SpecialVBox chapterTabVbox = new SpecialVBox(10);
    private SpecialLabel bookTitleLbl = new SpecialLabel(this.getBookTitle());
    private SpecialHBox logoTitleHbox = new SpecialHBox(10);
    private SpecialTextField wordCountTF = new SpecialTextField();

    Book() {
    }

    Book(String title) {
        this.bookTitle = title;
    }

    public void setChapterNumbers() {
        for(int i = 0; i < chapterList.size(); i++) {
            Chapter currentChapter = chapterList.get(i);
            currentChapter.setChNumber(i + 1);
        }
    }

    public void populateBookBorderPane() {
    	SpecialButton addChapterButton = new SpecialButton("Add New Chapter");
        SpecialButton deleteChapterButton = new SpecialButton("Delete Selected Chapter");
        
        addChapterButton.setOnAction(e -> {
            addChapter();
        });

        deleteChapterButton.setOnAction(e -> {
            int index = this.getChapterTabPane().getSelectionModel().getSelectedIndex();
            Chapter chapter = (Chapter) this.getChapterTabPane().getSelectionModel().getSelectedItem().getContent();
            getChapterList().remove(chapter);
            this.getChapterTabPane().getTabs().remove(index);
        });

        chapterTabPane.setSide(Side.LEFT);
        chapterTabPane.setRotateGraphic(true);
        chapterTabPane.setTabMinHeight(115);
        chapterTabPane.setTabMaxHeight(115);
        chapterTabPane.setTabDragPolicy(TabDragPolicy.REORDER);

        if (this.getHbox().getChildren().size() < 1) {
            Hbox.getChildren().addAll(addChapterButton, deleteChapterButton);
            Hbox.setAlignment(Pos.BOTTOM_LEFT);
        }

        if (chapterTabVbox.getChildren().size() < 1) {
            chapterTabVbox.getChildren().addAll(chapterTabPane, Hbox);
            chapterBorderPane.setCenter(chapterTabVbox);
        }

        this.setCenter(chapterBorderPane);

        addFontStuff();

        for (int i = 0; i < getChapterList().size(); i++) {
            Chapter chapter = getChapterList().get(i);
            chapter.uploadChapters(this.getChapterTabPane());
            this.getChapterTabPane().setTabOrderNumbers();
        }
        
        ObservableList<Tab> tabList = chapterTabPane.getTabs();

        tabList.addListener(new ListChangeListener<Tab>() {
            public void onChanged(Change<? extends Tab> c) {
                while (c.next()) {
                    if (c.wasPermutated()) {
                        updateChapters(tabList);
                    } else {
                        for (Tab remitem : c.getRemoved()) {
                            updateChapters(tabList);
                        }
                    }
                }
            }
        });
    }

    private void addChapter() {
        getChapterList().add(new Chapter());
        this.setChapterNumbers();
        int listSize = getChapterList().size();
        Chapter chapter = getChapterList().get(listSize - 1);
        chapter.addNewChapter(this, this.getChapterTabPane());
        this.getChapterTabPane().setTabOrderNumbers();
    }

    private void updateChapters(ObservableList<Tab> tabList) {
        int orderNumber = 0;
        for (int i = 0; i < tabList.size(); i++) {
            String tabString = tabList.get(i).toString();
            System.out.println(tabString);
            if (tabString.length() == 6) {
                orderNumber = Character.getNumericValue(tabString.charAt(tabString.length() - 1));
            } else if (tabString.length() == 7) {
                orderNumber = (Character.getNumericValue(tabString.charAt(5)) * 10) +
                        (Character.getNumericValue(tabString.charAt(6)));
            } else {
            	System.out.println("error while obtaining tab number");
            }

            for (int j = 0; j < getChapterList().size(); j++) {
                int chNumber = getChapterList().get(j).getChNumber();
                if (chNumber == orderNumber) {
                    Chapter temp = getChapterList().get(j);
                    getChapterList().set(j, getChapterList().get(i));
                    getChapterList().set(i, temp);
                }
            }
        }
        //change chapter numbers to match new order
        this.setChapterNumbers();
        //change tab order numbers to match new order
        this.getChapterTabPane().setTabOrderNumbers();
    }

    private void addFontStuff() {
        SpecialVBox wordCountVbox = new SpecialVBox(5);
        SpecialLabel wordCountLabel = new SpecialLabel("Total Word Count:");
        SpecialTextField wordCountTF = this.getWordCountTF();
        wordCountTF.setEditable(false);
        wordCountVbox.getChildren().addAll(wordCountLabel, wordCountTF);
        SpecialHBox fontHbox = new SpecialHBox(5);

        ComboBox<String> fontCbo = new ComboBox<>();
        String font1 = new String("Times New Roman");
        String font2 = new String("SansSerif");
        fontCbo.getItems().addAll(font1, font2);
        fontCbo.setValue(font1);

        //TODO it is not working right now, fix it
//        fontCbo.setOnAction(e -> {
//            Font fontTNR = Font.font("Times New Roman"); //TODO update to getCurrentSelections from Bold, Italic, and size
//            Font fontSansSerif = Font.font("SansSerif");
//
//            String selectedString = fontCbo.getSelectionModel().getSelectedItem();
//            Chapter selectedChapter = (Chapter) chapterTabPane.getSelectionModel().getSelectedItem().getContent();
//            Text selectedText = new Text(selectedChapter.getChapterTA().getSelectedText());
//            String oldString = selectedText.toString();
//            String newString = "";
//
//            if (selectedString.equals(font1)) {
//                selectedText.setFont(fontTNR);
//                newString = selectedText.toString();
//                selectedChapter.getChapterTA().getSelectedText().replaceAll(oldString, newString);
//            } else if (selectedString.equals(font2)) {
//                selectedText.setFont(fontSansSerif);
//                newString = selectedText.toString();
//                selectedChapter.chapterTA.getSelectedText().replaceAll(oldString, newString);
//            }
//        });

        SpecialButton minusBtn = new SpecialButton("-");
        SpecialButton plusBtn = new SpecialButton("+");
        SpecialTextField textSizeTF = new SpecialTextField("11");
        SpecialButton boldBtn = new SpecialButton("", new SpecialLabel("Bold"));
        SpecialButton italicizeBtn = new SpecialButton("", new SpecialLabel("Italic"));
        SpecialButton underlineBtn = new SpecialButton("", new SpecialLabel("Underline"));

        ComboBox<Label> colorCbo = new ComboBox<>();
        SpecialLabel redLbl = new SpecialLabel("Red");
        SpecialLabel blackLbl = new SpecialLabel("Black");
        SpecialLabel blueLbl = new SpecialLabel("Blue");
        colorCbo.getItems().addAll(blackLbl, redLbl, blueLbl);
        colorCbo.setValue(blackLbl);

        ComboBox<Label> alignmentCbo = new ComboBox<>();
        SpecialLabel leftLbl = new SpecialLabel("Left");
        SpecialLabel centerLbl = new SpecialLabel("Center");
        SpecialLabel rightLbl = new SpecialLabel("Right");
        alignmentCbo.getItems().addAll(leftLbl, centerLbl, rightLbl);
        alignmentCbo.setValue(leftLbl);

        fontHbox.getChildren().addAll(fontCbo, minusBtn, textSizeTF, plusBtn, boldBtn,
                italicizeBtn, underlineBtn, colorCbo, alignmentCbo, wordCountVbox);
        fontHbox.setAlignment(Pos.CENTER);

        Image Aimage = new Image("ALogo.jpg");
        ImageView Aview = new ImageView(Aimage);
        Aview.setPreserveRatio(true);
        Aview.setFitHeight(75);
        SpecialVBox lblVbox = new SpecialVBox();
        bookTitleLbl.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
        this.getBookTitleLbl().setText(this.getBookTitle());
        lblVbox.getChildren().add(bookTitleLbl);
        lblVbox.setAlignment(Pos.CENTER);
        logoTitleHbox.getChildren().addAll(Aview, lblVbox);
        logoTitleHbox.setAlignment(Pos.TOP_LEFT);
        SpecialVBox thisVbox = new SpecialVBox(10);
        thisVbox.getChildren().addAll(logoTitleHbox, fontHbox);
        this.setTop(thisVbox);
    }

    public SpecialHBox getHbox() {
        return Hbox;
    }

    public SpecialBorderPane getChapterBorderPane() {
        return chapterBorderPane;
    }

    public SpecialVBox getChapterTabVbox() {
        return chapterTabVbox;
    }

    public SpecialLabel getBookTitleLbl() {
        return bookTitleLbl;
    }

    public OrderableTabPane getChapterTabPane() {
        return chapterTabPane;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
        setBookNoteFileName();
    }

    public int getTotalWordCount() {
        return this.totalWordCount;
    }

//    public static void updateTotalWordCount() {
//        int currentTotal = 0;
//
//        for(int i = 0; i < getChapterList().size(); i++) {
//            currentTotal += getChapterList().get(i).getChWordCount();
//        }
//        setTotalWordCount(currentTotal);
//        getWordCountTF().setText(getTotalWordCount() + "");
//        Chapter.setTotalWordCount(currentTotal);
//    }

    public void setTotalWordCount(int count) {
    	this.totalWordCount = count;
    }

    public String getBookNoteFileName() {
        return bookNoteFileName;
    }

    public void setBookNoteFileName() {
        this.bookNoteFileName = bookTitle + "BookNotes.txt";
    }

    public File getBookNoteFile() {
        return bookNoteFile;
    }

    public void setBookNoteFile(File bookNoteFile) {
        this.bookNoteFile = bookNoteFile;
    }

    public ArrayList<Chapter> getChapterList() {
        return chapterList;
    }

    public void setChapterList(ArrayList<Chapter> chapterList) {
        this.chapterList = chapterList;
    }

	public SpecialTextField getWordCountTF() {
		return wordCountTF;
	}
}
