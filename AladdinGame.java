import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Popup;


/************************************************************************
 * The following class programs the Aladdin's game starting screen
 *
 * @authors: Fatima Asif
 *           Hanna Susan Jossy
 *           Sanjida Shakhayet
 *          
 * @Version: AladdinGame ~ starting scene
 **********************************************************************/
public class AladdinGame extends Application 
{
    // Instance Data
    private boolean isPlaying = false;
    private MediaPlayer mediaPlayer;
    
    /**********************************************************************************
     * The following method is a start method
     * 
     * Input: Stage primaryStage
     * Output: None
     **********************************************************************************/
    @Override
    public void start(Stage primaryStage)
    {
        // setup the audio for playing the backgroung music
        //String path = "ARABICmusic.mp3";  
        //Media media = new Media(new File(path).toURI().toString());
        //mediaPlayer = new MediaPlayer(media);
        // this to start the music 
        //mediaPlayer.play();
       
        // setup the Background Image
        Image bgImage = new Image("background.png");
        ImageView bgView = new ImageView(bgImage);
        
        // Setting Image length and width to fit the screen
        bgView.setFitWidth(1000);
        bgView.setFitHeight(700);

        // create a start button as an Image
        Image startImg = new Image("StartButton.png");
        ImageView startView = new ImageView(startImg);
        
        // Setting Image length and width
        startView.setFitWidth(110);
        startView.setFitHeight(155);

        // Label on top of Start Button and formating the label
        Label startLabel = new Label("Start");
        startLabel.setStyle("-fx-font-size: 35px; -fx-text-fill: gold; -fx-font-weight: bold;");
       
        // StackPane to layer image and label
        StackPane startStack = new StackPane(startView, startLabel);
        startStack.setAlignment(Pos.CENTER);
       
        // Button with stacked content
        Button startButton = new Button();
        startButton.setGraphic(startStack);
        startButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        // Button Action - Switch Scene and stop the music
        startButton.setOnAction(e -> {
       
            //If the music is still playing stop the music as the button is clicked
            if (mediaPlayer != null) 
            {
                mediaPlayer.stop();
            }
            //Switch to next screen
            switchScene(primaryStage);
        });
       
        //create an Instructions Button as an Image
        Image instrImg = new Image("StartButton.png");
        ImageView instrView = new ImageView(instrImg);
        
        //Setting Image length and width to fit the screen and setting it on screen
        instrView.setFitWidth(110);
        instrView.setFitHeight(155);
        instrView.setTranslateX(650);
         
        // instruction button with an image 
        Button instrButton = new Button("", instrView);
        instrButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");


        // Button Action - Show Instructions Scene
        instrButton.setOnAction(e -> new InstructionScreen().start(primaryStage));
       
        // Bottom Left HBox for Buttons
        // Spacing between buttons
        HBox bottomLayout = new HBox(20, startButton, instrButton);
        bottomLayout.setAlignment(Pos.CENTER_LEFT);
        bottomLayout.setPadding(new Insets(0, 0, 50, 20));

        // VBox to stack background, button layout
        VBox root = new VBox();
        root.getChildren().addAll(new StackPane(bgView), bottomLayout);
        
        
        VBox.setMargin(bottomLayout, new Insets(-175, 0, 0, 0));

        // Creating the scene 
        Scene scene1 = new Scene(root, 1000, 700);

        // Set the stage size to fixed and disable resizing
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Aladdin Game");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /************************************************************************
     * The following method changes screens from start screen to next screen
     *
     * input: Stage stage
     * output: None
     **********************************************************************/
    private void switchScene(Stage stage)
    {
        AladdinGameNextScreen nextScreen = new AladdinGameNextScreen();
        nextScreen.start(stage);
    }

    /************************************************************************
     * The following method displays the instructions screen
     *
     * input: Stage stage
     * output: None
     **********************************************************************/
    private void showInstructions(Stage stage)
    {
        // Instructions Image
        Image instrImage = new Image("InstructionScreen.png");
        ImageView instrView = new ImageView(instrImage);
        
        // Setting the width and height of the image to fit the screen
        instrView.setFitWidth(1000);
        instrView.setFitHeight(700);
        StackPane instrLayout = new StackPane(instrView);

        // Scene for Instructions
        Scene instrScene = new Scene(instrLayout, 1000, 700);

        // Set the scene
        stage.setScene(instrScene);
    }
}

