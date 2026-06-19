/**
 * Class: CMSC204
 * Instructor: Gary Thai
 * Description:
 * Due: 4/19/2026
 * Platform/compiler: IntelliJ IDEA
 * I pledge that I have completed the programming assignment
 * independently. I have not copied the code from a student or
 * any source. I have not given my code to any student.
 *Print your Name here: David Sandoval
 */


import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.util.Scanner;

public class MorseCodeMain extends BorderPane
{
    private Label lblCode, lblEnglish;
    private TextArea txtCode, txtEnglish;
    private ScrollPane codeScroll, englishScroll;
    private	Button convertFile, convertString, exitButton;

    private Alert alert = new Alert(AlertType.INFORMATION);

    /**
     * This sets up the graphics components
     */
    public MorseCodeMain()
    {
        VBox subpanel = new VBox();


        lblCode = new Label("Morse Code (' ' is separator for letters '/' for words ) ");
        lblEnglish = new Label("English Translation ");


        txtCode =  new TextArea();
        codeScroll = new ScrollPane(txtCode);
        txtCode.setWrapText(true);
        txtCode.setFont(new Font("Arial", 20));
        txtEnglish = new TextArea();
        txtEnglish.setEditable(false);
        englishScroll = new ScrollPane(txtEnglish);
        txtEnglish.setWrapText(true);
        txtEnglish.setFont(new Font("Arial", 20));
        txtEnglish.setEditable(false);

        VBox.setMargin(lblCode, new Insets(2,10,2,10));
        VBox.setMargin(txtCode, new Insets(2,10,2,10));
        VBox.setMargin(lblEnglish, new Insets(2,10,2,10));
        VBox.setMargin(txtEnglish, new Insets(2,10,2,10));

        subpanel.setAlignment(Pos.CENTER_LEFT);
        subpanel.getChildren().addAll(lblCode, txtCode, lblEnglish, txtEnglish);

        convertString = new Button("Convert String");
        convertString.setTooltip(new Tooltip("Converts a morse code string to English"));
        //use a lambda expression for the EventHandler class for convertString button
        convertString.setOnAction(
                event -> {
                    try {
                        txtEnglish.setText(MorseCodeConverter.convertToEnglish(txtCode.getText()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        convertFile = new Button ("Convert File");
        convertFile.setTooltip(new Tooltip("Converts a file of morse code into English."));
        //use a lambda expression for the EventHandler class for convertFile button
        convertFile.setOnAction(
                event -> {
                    readAndConvertFile();
                });

        exitButton = new Button("Exit");
        exitButton.setTooltip(new Tooltip("Select to close the application"));
        //use a lambda expression for the EventHandler class for exitButton
        exitButton.setOnAction(
                event -> {
                    Platform.exit();
                    System.exit(0);
                }
        );


        HBox buttonPanel = new HBox();
        HBox.setMargin(convertFile, new Insets(10,10,10,10));
        HBox.setMargin(convertString, new Insets(10,10,10,10));
        HBox.setMargin(exitButton, new Insets(10,10,10,10));
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.getChildren().addAll(convertFile, convertString, exitButton);

        setCenter(subpanel);
        setBottom(buttonPanel);


    }

    /**
     * Creates an extracts the contents of the file into a String
     * @param inputFile the file to be read from
     * @return the contents of the inputFile as a String
     */
    public String getFileContents(File inputFile)
    {
        String returnString="";
        try{
            Scanner scan = new Scanner(inputFile);
            while(scan.hasNext())
            {
                returnString = returnString + scan.next()+" ";
            }
            scan.close();
            return returnString;
        }
        catch(FileNotFoundException exception)
        {
            alert.setTitle("File Error");
            alert.setHeaderText("Cannot read file");
            alert.showAndWait();
            return null;
        }
    }

    /**
     * Creates the user to allown a select file of morse code and then display the contents of
     * the file
     */
    public void readAndConvertFile() {
        FileChooser chooser = new FileChooser();
        String originalString, returnString;
        File selectedFile = chooser.showOpenDialog(null);
        if(selectedFile != null)
        {

            try{
                originalString = getFileContents(selectedFile);
                txtCode.setText(originalString);
                returnString  = MorseCodeConverter.convertToEnglish(selectedFile);
                txtEnglish.setText(returnString);
            }
            catch(FileNotFoundException exception)
            {
                alert.setTitle("File Error");
                alert.setHeaderText("Cannot read file");
                alert.showAndWait();
            }
        }
        else {
            alert.setTitle("File Error");
            alert.setHeaderText("No selected File");
            alert.showAndWait();

        }

    }
}
