package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Main extends Application {
	public BorderPane mainBorderPane = new BorderPane();
	public Image havenPic = new Image("HavenPicture.png");
	public ImageView havenPicView = new ImageView(havenPic);
	public Image havenLogo = new Image("Author'sHavenLogo.jpg");
	public ImageView havenLogoView = new ImageView(havenLogo);
    public static Tab writingTab = new Tab("Write");
    public static Tab mainMenuTab = new Tab("Main Menu");
    public static TabPane mainTabPane = new TabPane();
    public HavenDatabase authorHashMap;
    public File authorListFile = new File("HavenDatabase.dat");

    @Override
    public void start(Stage primaryStage) throws Exception {
        if (authorListFile.exists()) {
            try {
                upload();
            } catch (ClassNotFoundException o) {
                o.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            authorHashMap = new HavenDatabase();
        }

        mainMenuTab.setClosable(false);
        BorderPane mainMenuBorderPane = new BorderPane();
        BorderPane innerBorderPane = new BorderPane();
        havenPicView.setPreserveRatio(true);
        havenPicView.setFitHeight(600);
        VBox havenVbox = new VBox(havenPicView);
        havenVbox.setAlignment(Pos.CENTER);
        havenLogoView.setPreserveRatio(true);
        havenLogoView.setFitHeight(100);
        HBox havenLogoHbox = new HBox(havenLogoView);
        HBox topSloganHbox = new HBox();
        topSloganHbox.setAlignment(Pos.CENTER);
        Label topSloganLabel = new Label("~~Where Creativity Thrives~~");
        topSloganLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 30));
        topSloganLabel.setTextFill(Color.PURPLE);
        topSloganHbox.getChildren().add(topSloganLabel);
        VBox topVbox = new VBox(10);
        topVbox.getChildren().addAll(havenLogoHbox, topSloganHbox);
        havenLogoHbox.setAlignment(Pos.CENTER);
        innerBorderPane.setTop(topVbox);
        
        Label selectAuthorLbl = new Label("Type name of Author to\ncontinue (or begin) writing:");
        selectAuthorLbl.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 30));
        selectAuthorLbl.setTextFill(Color.ORANGERED);
        TextField authorNameTF = new TextField();
        Label blankLbl = new Label("");
        HBox formatHbox = new HBox();
        formatHbox.getChildren().addAll(authorNameTF, blankLbl);
        formatHbox.setAlignment(Pos.CENTER);
        authorNameTF.setEditable(true);
        authorNameTF.setPrefColumnCount(20);
        Button createNewBtn = new Button("Create New Account");
        Button continueBtn = new Button("Continue Writing");
        HBox buttonHbox = new HBox(10);
        buttonHbox.getChildren().addAll(createNewBtn, continueBtn);
        buttonHbox.setAlignment(Pos.CENTER);
        VBox selectAuthorVbox = new VBox(10);
        selectAuthorVbox.setPrefWidth(50);
        selectAuthorVbox.setAlignment(Pos.CENTER);
        selectAuthorVbox.getChildren().addAll(selectAuthorLbl, formatHbox, buttonHbox);
        StackPane firstPageStackPane = new StackPane(selectAuthorVbox);
        innerBorderPane.setCenter(firstPageStackPane);

        createNewBtn.setOnAction(o -> {
            String name = authorNameTF.getText().trim().toLowerCase();
            authorHashMap.put(name, new Author(name));
            Author currentAuthor = authorHashMap.get(name);
            innerBorderPane.setCenter(new SpecialScrollPane(currentAuthor));
        });

        continueBtn.setOnAction(i -> {
            String name = authorNameTF.getText().trim().toLowerCase();
            Author currentAuthor = authorHashMap.get(name);
            innerBorderPane.setCenter(new SpecialScrollPane(currentAuthor));
        });

        Label bottomSlogan = new Label("**The Only Limitation Is Your Imagination**");
        bottomSlogan.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 20));
        bottomSlogan.setTextFill(Color.FORESTGREEN);
        HBox bottomSloganHbox = new HBox();
        Button selectAuthorBtn = new Button("Select Different Author");
        VBox bottomSloganVbox = new VBox(5);
        bottomSloganVbox.getChildren().addAll(selectAuthorBtn, bottomSlogan);
        bottomSloganVbox.setAlignment(Pos.CENTER);
        bottomSloganHbox.getChildren().add(bottomSloganVbox);
        bottomSloganHbox.setAlignment(Pos.CENTER);
        innerBorderPane.setBottom(bottomSloganHbox);
        mainMenuBorderPane.setCenter(innerBorderPane);
        mainMenuBorderPane.setRight(havenVbox);
        mainMenuTab.setContent(mainMenuBorderPane);
        writingTab.setClosable(false);
        Tab plotGridTab = new Tab("Plot Grid");
        plotGridTab.setClosable(false);
        Tab infoTab = new Tab("Info");
        infoTab.setClosable(false);
        mainTabPane.getTabs().addAll(mainMenuTab, writingTab, plotGridTab, infoTab);
        mainTabPane.setSide(Side.BOTTOM);

        selectAuthorBtn.setOnAction(e -> {
            innerBorderPane.setCenter(firstPageStackPane);
        });

        mainBorderPane.setCenter(mainTabPane);

        MenuBar menuBar = new MenuBar();
        mainBorderPane.setTop(menuBar);
        Menu menuSave = new Menu("Save");
        MenuItem miSave = new MenuItem("Save");
        menuSave.getItems().add(miSave);
        Menu menuFile = new Menu("File");
        Menu menuEdit = new Menu("Edit");
        Menu menuInsert = new Menu("Insert");
        Menu menuFormat = new Menu("Format");
        menuBar.getMenus().addAll(menuSave, menuFile, menuEdit, menuInsert, menuFormat);
        MenuItem miAddChapter = new MenuItem("Add Chapter");
        MenuItem miExport = new MenuItem("Export");
        MenuItem miDownload = new MenuItem("Download");
        menuFile.getItems().addAll(miAddChapter, miExport, miDownload);
        MenuItem miUndo = new MenuItem("Undo");
        MenuItem miRedo = new MenuItem("Redo");
        MenuItem miCut = new MenuItem("Cut");
        MenuItem miCopy = new MenuItem("Copy");
        MenuItem miPaste = new MenuItem("Paste");
        MenuItem miFindNReplace = new MenuItem("Find & Replace");
        menuEdit.getItems().addAll(miUndo, miRedo, miCut, miCopy, miPaste, miFindNReplace);

        miSave.setOnAction(e -> {
            try {
                System.out.println("tried to save");
                save();
                System.out.println("successfully saved");
                //todo add pop up or label that saves successfully saved
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(mainBorderPane, 1350, 695);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Author's Haven");
        primaryStage.show();
    }

    public static TabPane getMainTabPane() {
        return mainTabPane;
    }

    public static Tab getWritingTab() {
        return writingTab;
    }

    public static Tab getMainMenuTab() {
		return mainMenuTab;
	}

    private void save() throws IOException {
        System.out.println("made it to the method");
        try {
            if (authorListFile.exists()) {
                authorListFile.delete();
            }
            try (ObjectOutputStream objectOutput =
                         new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(authorListFile)))
            ) {
                System.out.println("about to save authorHashMap");

                Chapter currentChapter;
                ArrayList<Map.Entry<String, Author>> list = new ArrayList<>(authorHashMap.entrySet());
                for (int i = 0; i < authorHashMap.values().size(); i++) {
                    Author author = list.get(i).getValue();
                    System.out.println(author + ": " + i);
                    //for each book
                    for (int j = 0; j < author.getBookList().size(); j++) {
                        Book book = (Book) author.getBookList().get(j);
                        System.out.println(book + ": " + j);
                        //todo save book notes to book note file
                        //for each chapter
                        for (int k = 0; k < book.getChapterList().size(); k++) {
                            currentChapter = book.getChapterList().get(k);
                            System.out.println(currentChapter + ": " + k);

                            File currentFile = new File(currentChapter.getFileName());
                            if (currentFile.exists()) {
                                currentFile.delete();
                            }

                            try (
                                    PrintWriter textOutput = new PrintWriter(currentFile)
                            ) {
                                System.out.println("about to save current chapter " + k + " text area");
                                textOutput.print(currentChapter.getChapterTA().getText());
                                System.out.println("saved current chapter " + k + " text area");
                            }

                            File currentNoteFile = new File(currentChapter.getNoteFileName());
                            if (currentNoteFile.exists()) {
                                currentNoteFile.delete();
                            }

                            try (
                                    PrintWriter textNoteOutput = new PrintWriter(currentNoteFile)
                            ) {
                                System.out.println("about to save current chapter " + k + " note text area");
                                textNoteOutput.print(currentChapter.getNotesTA().getText());
                                System.out.println("saved current chapter " + k + " note text area");
                            }
                        }
                    }
                }
                objectOutput.writeObject(authorHashMap);
                System.out.println("saved authorHashMap");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void upload() throws ClassNotFoundException, IOException {
        try (ObjectInputStream objectInput =
                     new ObjectInputStream(new BufferedInputStream(new FileInputStream(authorListFile)))
        ) {
            System.out.println("about to upload authorHashMap");
            authorHashMap = (HavenDatabase) objectInput.readObject();
            System.out.println("successfully uploaded authorHashMap");
        }

        //for each author
        ArrayList<Map.Entry<String, Author>> list = new ArrayList<>(authorHashMap.entrySet());
        for (int i = 0; i < authorHashMap.values().size(); i++) {
            Author author = list.get(i).getValue();
            System.out.println(author + ": " + i);
            author.makeFlowPane();
            //for each book
            for (int j = 0; j < author.getBookList().size(); j++) {
                Book book = (Book) author.getBookList().get(j);
                System.out.println(book + ": " + j);
                //for each chapter
                for (int k = 0; k < book.getChapterList().size(); k++) {
                    Chapter currentChapter = book.getChapterList().get(k);
                    System.out.println(currentChapter + ": " + k);
                    
                    File currentFile = new File(currentChapter.getFileName());
                    if (currentFile.exists()) {
                        try (
                                Scanner textInput = new Scanner(currentFile).useDelimiter("(\\b|\\B)")
                        ) {
                            System.out.println("about to upload current chapter " + k + " text area");
                            while(textInput.hasNext()){
                            		currentChapter.getChapterTA().appendText(textInput.next());
                            }
                            System.out.println("successfully uploaded current chapter " + k + " text area");
                        }
                    }

                    File currentNoteFile = new File(currentChapter.getNoteFileName());
                    if (currentNoteFile.exists()) {
                        try (
                                Scanner textNoteInput = new Scanner(currentNoteFile).useDelimiter("(\\b|\\B)")
                        ) {
                            System.out.println("about to upload current chapter " + k + " note text area");
                            while(textNoteInput.hasNext()){
                            		currentChapter.getNotesTA().appendText(textNoteInput.next());
                            }
                            System.out.println("successfully uploaded current chapter " + k + " note text area");
                        }
                    }
                    currentChapter.calculateChWordCount();
                }
            }
        }
    }

    private void export() {}

    private void download() {}

    private int calculateWordCount(Chapter chapter){
        File file = chapter.getChFile();

        return 0;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
