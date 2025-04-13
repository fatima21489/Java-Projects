import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
/******************************************************************************
 * The following class programs the Aladdin's game instruction display
 *
 * @authors: Fatima Asif
 *           Hanna Susan Jossy
 *           Sanjida Shakhayet
 *          
 * @Version: AladdinGame ~ instruction screen
 ******************************************************************************/
 

public class InstructionScreen extends Application
{
    // instance data
    private Stage stage;
 
    /**********************************************************************************
     * The following method is a start method
     * 
     * Input: Stage primaryStage
     * Output: None
     **********************************************************************************/
    @Override
    public void start(Stage primaryStage)
    {  
        this.stage = primaryStage;
 
        // This is the code to load the image into the screen 
        Image instrImage = new Image("InstructionScreen.png");
        ImageView instrView = new ImageView(instrImage);
        
        // setting the width and height to fit the screen
        instrView.setFitWidth(1000);
        instrView.setFitHeight(700);
 
        //creating the button to go back to the start screen with and image of a carpet 
        Image backImg = new Image("carpetButton.png");
        ImageView backView = new ImageView(backImg);
        
        
        // setting the width and height of the carpet image 
        backView.setFitWidth(100);
        backView.setFitHeight(100);

        // Make a vertical box layout to hold the image(here we used vbox as we wanted to hold the image vertically)
        // 5 is the gap or space between items in that stack
        VBox backButtonLayout = new VBox(5, backView);
        backButtonLayout.setAlignment(Pos.CENTER);
        
        //button construction( creats a button)
        Button backButton = new Button("", backView);
        
        //set the style
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        //button action( set the action to return the main screen)
        backButton.setOnAction(e -> goBack());
 
        // Layout(stackpane allows overlapping these elements)
        StackPane root = new StackPane(instrView, backButton);
        
        //position the button at bottom left
        StackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        
        //positioning the backbutton in stackpane
        StackPane.setMargin(backButton, new Insets(20));
 
        //create a Scene with fixed size
        Scene instrScene = new Scene(root, 1000, 700);
        stage.setScene(instrScene);
        //make window visible
        stage.show();
    }
   
    /**************************************************
     * The following method returns to the main screen
     *
     * input: none
     * output: None
     ***************************************************/
    private void goBack()
   
    {
        new AladdinGame().start(stage);  
    }
}


