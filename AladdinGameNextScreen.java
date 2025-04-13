import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.*;
import javafx.scene.Node;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

/******************************************************************************
 * The following class programs the the screen where we play the game 
 *
 * @authors: Fatima Asif
 *           Hanna Susan Jossy
 *           Sanjida Shakhayet
 *          
 * @Version: AladdinGame ~ game screen 
 ******************************************************************************/
 public class AladdinGameNextScreen extends Application
{
    //Instance Data 
    // this is the rows and coloumns for the gridpane
    private static final int ROWS = 3;
    private static final int COLS = 4;
    // this is an array of vbucks value 
    private static final int[] VBucks_VALUES = {1000, 900, 800, 700, 600, 500, 400, 300, 200, 100, 50, 0};
    
    //Hash map to match each lamp to the vbucks behind it 
    private Map<ImageView, Integer> lampVbucksMap = new HashMap<>();
     
    //Array list to store  the eliminated VBucks values
    private List<Integer> eliminatedVbucks = new ArrayList<>();
    
    // VBox to display the points table vertically
    private VBox pointsTable = new VBox(5);
    
    // The user's chosen lamp(this is null because when the user pick a lamp it will disappear)
    private ImageView chosenLamp = null;
    
    // Hidden value of the chosen lamp
    private int chosenLampValue = -1;
    
    // This is what aladdin says when the game starts 
    private Label aladdinMessage = new Label("Welcome! Pick a lamp to keep as your own.");
    
    //layout for lamps
    private GridPane grid;
    
    //Hbox at the bottom for the deal box 
    private HBox bottomBox;
    
    // Track deal rejections
    private int rejectionCount = 0;
    
    // Track the  revealed lamps
    private boolean[] revealed;
    
    private VBox dealBox;
    
    //variable for the deal offer 
    private int dealOffer;
    
    //initailly no lamp is choosen 
    private boolean isChosen = false;
    
    //initially no deal box is offered
    private boolean dealOffered = false;
    
    // Variable to track the number of clicks
    private int clickCount = 0;
    
    // Variable to track how many lamps have been opened
    private int lampsOpened = 0;
    
    //game active state
    private boolean isPlaying = false;
    
    //background music
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage)
    {   
         
        // background image setup
        Image bgImage = new Image("back1.png");
        ImageView bgView = new ImageView(bgImage);
        
        // set the width and height
        bgView.setFitWidth(1000);
        bgView.setFitHeight(700);
   
        // Background setup - StackPane allows layering elements
        StackPane backgroundPane = new StackPane();
        
        // Background image will be at the bottom
        backgroundPane.getChildren().add(bgView);
   
        //gridPane for lamps(the position of the lamps are in the matrix form ,so we used gridpane)
        GridPane grid = new GridPane();
        
        // set padding to position the lamps
        grid.setPadding(new Insets(120, 70, 20, 150));
        
        //Vertical spacing between lamps
        grid.setVgap(50);
        
        //Horizontal spacing between lamps
        grid.setHgap(50);
        
        //positioning the lamps to the center 
        grid.setAlignment(Pos.CENTER);
   
        //  Array list to shuffle VBucks values for random placement
        List<Integer> vbucksList = new ArrayList<>(Arrays.asList(Arrays.stream(VBucks_VALUES).boxed().toArray(Integer[]::new)));
        Collections.shuffle(vbucksList);
        
        // create and positon lamps
        int index = 0;
        // a for loop to display the lamps in a grid 
        for (int row = 0; row < ROWS; row++)
        {
            for (int col = 0; col < COLS; col++)
            {  
                // Prevents out-of-bounds if values are less
                if (index >= vbucksList.size()) break;
   
                // Creating the  lamp image
                Image lampImage = new Image("allLamp.png");
                ImageView lampView = new ImageView(lampImage);
                
                //set the width and height
                lampView.setFitWidth(90);
                lampView.setFitHeight(90);
   
                // Create a label for the number on the lamp
                // Numbering lamps starting from 1
                Label lampNumber = new Label(Integer.toString(index + 1));
                
                //set the font
                lampNumber.setFont(new Font("Arial", 16));
                
                //set the text color and format the text
                lampNumber.setTextFill(Color.GOLD);
                lampNumber.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 5; -fx-border-radius: 10;");
                
                // Adjust number position horizontally
                lampNumber.setTranslateX(30);
                
                // Adjust number position vertically
                lampNumber.setTranslateY(30);
   
                // StackPane to hold the lamp and the number(combine lamp and number)
                StackPane lampStack = new StackPane();
                
                // Add lamp and number on top of each other
                lampStack.getChildren().addAll(lampView, lampNumber);
   
                // Store VBucks value for this lamp
                int vbucks = vbucksList.get(index++);
                lampVbucksMap.put(lampView, vbucks);
   
                // set the click handler to pick a lamp
                lampStack.setOnMouseClicked(event -> selectLamp(grid, lampStack, lampView, vbucks));
   
                // Add lamp to the grid
                grid.add(lampStack, col, row);
            }
        }
   
        // Aladdin message setup and formatting 
        aladdinMessage.setFont(new Font("Arial", 20));
        aladdinMessage.setTextFill(Color.WHITE);
        aladdinMessage.setStyle("-fx-background-color: black; -fx-padding: 5; -fx-border-color: gold;");
        aladdinMessage.setAlignment(Pos.CENTER);
        aladdinMessage.setTranslateY(5);
        aladdinMessage.setTranslateX(-5);
       
        // Layout for Aladdin(we use vbox for vertical organization)
        VBox aladdinBox = new VBox(10, aladdinMessage);
        
        // Aligns Aladdin to the bottom-left
        aladdinBox.setAlignment(Pos.BOTTOM_LEFT);
        
        // Padding adjusted to place it properly  
        aladdinBox.setPadding(new Insets(10, 10, 20, 20));
   
        // set up the Reset Button
        Button resetButton = new Button("Reset");
        
        // Create an image for the button 
        Image resetImage = new Image("carpetButton.png");
        ImageView imageView = new ImageView(resetImage);
        
        // Optionally, resize the image to fit the button
        // Set image width 
        imageView.setFitWidth(100); 
        
        // Set image height 
        imageView.setFitHeight(100);
        resetButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
   
        // Set the image as the graphic on the button
        resetButton.setGraphic(imageView);
        
        // Create a Label for the text
        Label resetText = new Label("Reset");
        resetText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: yellow;");
        
        //we use stackpane here to perfectly overlays text lebel on the top of button image
        StackPane stackPane = new StackPane();
        
        // Image is the background, text is on top
        stackPane.getChildren().addAll(imageView, resetText);
       
        // Set the StackPane as the graphic for the button
        resetButton.setGraphic(stackPane);
        
        // set the action for reset button
        resetButton.setOnAction(e -> restartGame(primaryStage));
        
        // move 380 pixel right from the original position
        resetButton.setTranslateX(380);
        
        // Move 510 pixels up from original position
        resetButton.setTranslateY(-510);
       
        // setup the points table
        pointsTable.setAlignment(Pos.BOTTOM_RIGHT);
        
        // Adjust padding
        pointsTable.setPadding(new Insets(100, 0, 0, 0));
        
        //set width and height
        pointsTable.setPrefWidth(250);  
        pointsTable.setMaxWidth(150);
        pointsTable.setPrefHeight(250);
        pointsTable.setMaxHeight(150);
        pointsTable.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-border-color: gold; -fx-padding: 5;");
        
        // setting the points table 
        pointsTable.setTranslateY(20);
        
        // Moves it down by 50 pixels
        pointsTable.setTranslateX(50);
   
        // Initialize the points table with all VBucks values
        updatePointsTable();
   
        // Layout organization
        VBox gameLayout = new VBox(10,grid ,aladdinBox, resetButton);
        gameLayout.setAlignment(Pos.CENTER);
       
        // Main layout using BorderPane to ensure visibility
        BorderPane mainLayout = new BorderPane();
        
        // Game in center
        mainLayout.setCenter(gameLayout);
        
        // Ensure alignment
        BorderPane.setAlignment(aladdinBox, Pos.BOTTOM_LEFT);
        
        // Move Aladdin to the bottom
        mainLayout.setBottom(aladdinBox);
        
        // Points table on the left
        mainLayout.setLeft(pointsTable);
        mainLayout.setPadding(new Insets(5));
           
        // StackPane for background layering
        StackPane root = new StackPane(backgroundPane, mainLayout);
        
        //Create main scene with fixed dimensions matching background
        Scene scene = new Scene(root,1000, 700);
        primaryStage.setTitle("Aladdin Game - Next Scene");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
     //Displays the game instructions screen with a full-screen image
     // The primary stage to display the instructions
    
    /************************************************************************
     * The following method loads the image into the screen
     *
     * input: Stage stage
     * output: None
     **********************************************************************/
    private void showInstructionsScreen(Stage stage)
    {
        // Create an image for the instructions screen
        Image instructionsImage = new Image("instructions.png");  
        ImageView instructionsView = new ImageView(instructionsImage);
        instructionsView.setFitWidth(1000);  
        instructionsView.setFitHeight(700); 
   
        // StackPane layout for the instructions screen
        StackPane instructionsLayout = new StackPane();
        instructionsLayout.getChildren().add(instructionsView);
   
        // Create the instructions scene
        Scene instructionsScene = new Scene(instructionsLayout, 1000, 700);
        stage.setScene(instructionsScene);
        stage.show();
    }
    
    /************************************************************************
     * The following method displays the final screen
     *
     * input: int winnings
     * output: None
     **********************************************************************/
    private void finalScreen(int winnings)
    { 
        //loading the background image
        Image backgroundImage = new Image("fff.png"); 
        ImageView backgroundView = new ImageView(backgroundImage);
        
        //set the width and height
        backgroundView.setFitWidth(1000);
        backgroundView.setFitHeight(700);
        
        
        Stage finalStage = new Stage();
        
        
        VBox finalLayout = new VBox(20);
        finalLayout.setAlignment(Pos.CENTER);
        finalLayout.setPadding(new Insets(20));
        
        //set a lebel as a message
        Label finalMessage = new Label("Congratulations! You won " + winnings + " VBucks!");
        finalMessage.setFont(new Font("Arial", 40));
        finalMessage.setTextFill(Color.GOLD);
        
        //set a restart button
        Button restartButton = new Button("Restart");
        restartButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: yellow;");
        restartButton.setFont(new Font("Arial", 18));
        restartButton.setOnAction(e ->
         { 
            finalStage.close();
            try
            { 
                new AladdinGame().start(new Stage());
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });
        //set an image of the restart button
        Image restartImage = new Image("carpetButton.png");
        ImageView restartImageView = new ImageView(restartImage);
        
        //set the height and width
        restartImageView.setFitWidth(90);  
        restartImageView.setFitHeight(90);
        
        //here we used the stackpane for layering the button
        StackPane buttonStack = new StackPane();
        buttonStack.getChildren().addAll(restartImageView, restartButton); 
   
        // Align image and button within the StackPane
        StackPane.setAlignment(restartButton, Pos.CENTER);
        StackPane.setAlignment(restartImageView, Pos.CENTER);
   
        //add the components
        finalLayout.getChildren().addAll(finalMessage, buttonStack);
   
        // StackPane for layering background and VBox
        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundView, finalLayout);
        
        //create a scene with fixed dimension
        Scene finalScene = new Scene(root, 1000, 700);
        finalStage.setScene(finalScene);
        finalStage.setTitle("Game Over");
        finalStage.show();
    }
    
    /************************************************************************
     * The following method updates the point table 
     *
     * input: None
     * output: None
     **********************************************************************/
    private void updatePointsTable()
    {  
        // Clear previous table
        pointsTable.getChildren().clear();

        // Header for the points table
        Label header = new Label("Remaining VBucks:");
        header.setFont(new Font("Arial", 12));
        header.setTextFill(Color.GOLD);
        pointsTable.getChildren().add(header);

        // Display all VBucks values, mark eliminated ones in red
        for (int vbucks : VBucks_VALUES)
        {  
            //set the lebel
            Label vbucksLabel = new Label(vbucks + " VBucks");
            vbucksLabel.setFont(new Font("Arial", 16));
            
            if (eliminatedVbucks.contains(vbucks))
            {  
                //If the lamps selected by the user have certain vbucks turn the lable red
                vbucksLabel.setTextFill(Color.RED);  
            }
            else
            {  
                //If the lamps selected by the user doesn't have certain vbucks turn the lable white
                vbucksLabel.setTextFill(Color.WHITE);
            }
            
            //Adding the vbucks values to the points table
            pointsTable.getChildren().add(vbucksLabel);
        }
    }
    
     /****************************************************************************
     * The following method handles the lamp selection logic for aladdins game
     *
     * input: GridPane grid, StackPane lampStack, ImageView lampView, int vbucks
     * output: None
     *************************************************************************/
     private void selectLamp(GridPane grid, StackPane lampStack, ImageView lampView, int vbucks)
    {
        if (chosenLamp == null)
        {  
            // If no lamp has been chosen yet, highlight it
            lampView.setEffect(new javafx.scene.effect.DropShadow(20, Color.YELLOW));
            chosenLamp = lampView;
            chosenLampValue = vbucks;
            aladdinMessage.setText("You selected a lamp. Now start opening others!");
            clickCount++;
   
            if (lampsOpened >= 10)
            {  
                //If more than 10 lamps are opened, let the first lamp reveal its vbucks 
                // Enable the last lamp
                lampStack.setDisable(false);  
            } else
            {  
                //Disable the lamp stack to prevent further interaction
                lampStack.setDisable(true);  
            }
        } else
        {
            // After the first lamp is chosen, allow the user to open other lamps
            if (!eliminatedVbucks.contains(vbucks))
            {  
                // Reveal the VBucks value of the clicked lamp
                revealVbucks(grid, lampStack, vbucks);
                
                // Add to eliminated values
                eliminatedVbucks.add(vbucks);
                
                // Update points table
                updatePointsTable();
   
                lampsOpened++;
   
                // Enable last lamp when 10 lamps are opened
                if (lampsOpened == 10)
                {   
                    // Enable the final lamp
                    lampStack.setDisable(false);  
                }
   
                // Show deal box after certain lamps are opened
                if (lampsOpened == 4 || lampsOpened == 7 || lampsOpened == 10)
                { 
                    // Display the deal box
                    showDealBox(grid);
                }
   
                // End game if all lamps are opened
                if (lampsOpened == 11)
                {  
                    // Show final screen
                    finalScreen(chosenLampValue);  
                }
            }
        }
    }
     
    /************************************************************************
     * The following method handles response to deal box
     *
     * input: Stage stage
     * output: None
     **********************************************************************/
    private void handleDealResponse(boolean accepted)
    { 
        // Initial no deal box is offered
        dealOffered = false;  
        // If the deal is accepted
        if (accepted)
        {
            aladdinMessage.setText("Game over! You took the deal.");
            
            // Disable all lamps after accepting the deal
            disableAllLamps();
        } else
        {
            aladdinMessage.setText("You rejected the deal. Keep opening lamps!");
            
            // Re-enable remaining lamps after rejecting the deal
            enableRemainingLamps();          
        }
    }
   
    /************************************************************************
     * The following method disables all the lamps
     *
     * input: None
     * output: None
     **********************************************************************/
    private void disableAllLamps()
    { 
        // Traverse all nodes in the game grid
        for (Node node : grid.getChildren())
        {  // Check if node is a lamp container (StackPane)
            if (node instanceof StackPane)
            {
                StackPane lampStack = (StackPane) node;
                
                // Disable the lamp
                lampStack.setDisable(true);
            }
        }
    }
   
    /************************************************************************
     * The following method enable only the lamps that have not been eliminated
     *
     * input: None
     * output: None
     **********************************************************************/
    private void enableRemainingLamps()
    {  
        // Iterate through all nodes in the game grid
        for (Node node : grid.getChildren())
        {  
            // Check for lamp containers (StackPanes)
            if (node instanceof StackPane)
            {
                StackPane lampStack = (StackPane) node;
                // Extract lamp image and its associated value
                ImageView lampView = (ImageView) lampStack.getChildren().get(0);
                int lampValue = Integer.parseInt(lampView.getId()); 
   
                // Only re-enable the lamp if it hasn't been eliminated
                if (!eliminatedVbucks.contains(lampValue))
                {  
                    // Enable the lamp
                    lampStack.setDisable(false);  
                }
            }
        }
    }
   
    /************************************************************************
     * The following method gets the lamp's value based on its image
     *
     * input: ImageView lampView
     * output: None
     **********************************************************************/
    private int getLampValueFromImageView(ImageView lampView)
    {     
        // lampView.getId() contains the value
        return Integer.parseInt(lampView.getId());
    }
    
    //Reveals the hidden VBucks value by replacing a lamp with a value display
    /************************************************************************
     * The following method reveals the hidden VBucks value
     *
     * input: GridPane grid, StackPane lampStack, int vbucks
     * output: None
     **********************************************************************/
    private void revealVbucks(GridPane grid, StackPane lampStack, int vbucks)
    {
        // Create a label to show VBucks
        Label vbucksLabel = new Label(vbucks + " VBucks");
        vbucksLabel.setFont(new Font("Arial", 14));
        vbucksLabel.setTextFill(Color.GOLD);
        vbucksLabel.setStyle("-fx-background-color: black; -fx-padding: 5; -fx-border-color: gold;");
       
        // Get the row and column indexes of the lamp
        int row = GridPane.getRowIndex(lampStack);
        int col = GridPane.getColumnIndex(lampStack);
       
        // Remove the lamp stack from the grid
        grid.getChildren().remove(lampStack);
       
        // Add the VBucks label to the same position as the lampStack was
        GridPane.setRowIndex(vbucksLabel, row);
        GridPane.setColumnIndex(vbucksLabel, col);
        grid.getChildren().add(vbucksLabel);
       
        // Update the points table after each elimination
        updatePointsTable();
    }
       
    /************************************************************************
     * The following method Calculates the current deal offer based on 
     * remaining VBucks values 
     *
     * input: GridPane grid
     * output: None
     **********************************************************************/
    private void showDealBox(GridPane grid)
    {
        // Calculate the average of remaining VBucks
        double sum = 0;
        int count = 0;
        
        for (int i = 0; i < VBucks_VALUES.length; i++)
        {
            if (!eliminatedVbucks.contains(VBucks_VALUES[i]))
            { 
                // Only count unrevealed lamps
                sum += VBucks_VALUES[i];
                count++;
            }
        }
        
        // Avoid division by zero
        if (count == 0) return;
   
        // Calculate the average remaining VBucks
        double averageRemaining = sum / count;
   
        // Calculate the random multiplier between 0.7 and 1.3
        double randomMultiplier = 0.7 + (Math.random() * (1.3 - 0.7));
   
        // Calculate the deal offer and rounds the deal to an integer
        dealOffer = (int) (averageRemaining * randomMultiplier);
   
        // Create the deal box with options to accept or reject the deal
        Button acceptButton = new Button("Accept Deal");
        Button rejectButton = new Button("Reject Deal");
   
        // Label to display the deal offer amount
        Label dealOfferLabel = new Label("Deal offered: " + dealOffer + " VBucks");
        dealOfferLabel.setFont(new Font("Arial", 16));
        dealOfferLabel.setTextFill(Color.GOLD);
        dealOfferLabel.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5); -fx-padding: 5; -fx-border-radius: 10;");
   
        // Create the deal box layout (VBox for vertical stacking of buttons)
        VBox dealBox = new VBox(10, dealOfferLabel, acceptButton, rejectButton);
        dealBox.setStyle("-fx-background-color: purple; -fx-padding: 10;");
        dealBox.setTranslateY(400);
        dealBox.setTranslateX(5);
        //add the components in matrix form
        grid.getChildren().add(dealBox);
       
        // Handle accept/reject logic
        acceptButton.setOnAction(e -> {
            // When deal is accepted, reveal the vbucks and end the game
            revealVbucksBehindDealBox();
            isChosen = true;
            finalScreen(dealOffer);
           
        });
   
        rejectButton.setOnAction(e -> {
            // When deal is rejected, increment the rejection counter and continue the game
            // Increment the rejection count
            rejectionCount++;
            aladdinMessage.setText("You rejected the deal. Keep going!");
            // Remove the deal box
            grid.getChildren().remove(dealBox);  
        });
       
    }
    
    /************************************************************************
     * The following method reveals the VBucks value behind the deal box
     *
     * input: None
     * output: None
     **********************************************************************/
    private void revealVbucksBehindDealBox()
    {
        if(isChosen == true)
        {
            // Player accepted a deal
            aladdinMessage.setText("The final deal is: " + dealOffer + " VBucks!");
        } else
        {
            //Player rejected all deals and kept original lamp
            aladdinMessage.setText("Game Over! Final Lamp Value: " + chosenLampValue + " VBucks.");
        }
    }
           
    /************************************************************************
     * The following method Handles end-game scenarios here we used gridpane 
     * to comtail all the elements
     *
     * input: GridPane grid
     * output: None
     **********************************************************************/
    private void gameOver(GridPane grid)
    {
        if(isChosen == true)
        {
            // Logic to end the game, could include showing a game over message or updating scores
            aladdinMessage.setText("The final deal is: " + dealOffer + " VBucks!");
        } else
        {
            aladdinMessage.setText("Game Over! Final Lamp Value: " + chosenLampValue + " VBucks.");
        }
               
        // Disable all interactions once the game ends
        for(Node node : grid.getChildren())
        {
            if (node instanceof StackPane)
            {
                // make unclickable
                ((StackPane) node).setDisable(true);
            }
        }
    }
     
    /************************************************************************
     * The following method handles the acceptance of the deal
     *
     * input: double dealValue
     * output: None
     **********************************************************************/
    private void acceptDeal(double dealValue)
    {
        aladdinMessage.setText("You accepted the deal for " + (int) dealValue + " VBucks!");
    }

    /************************************************************************
     * The following method handles deal rejection 
     *
     * input: None
     * output: None
     **********************************************************************/ 
    private void rejectDeal()
    {
        // Handle the rejection of the deal
        aladdinMessage.setText("You rejected the deal. Continue opening lamps!");
    }
      
    /************************************************************************
     * The following method tracks which values have been revealed through
     * eliminatedVbucks list   
     *
     * input:vbucks
     * output: boolean true or false
     **********************************************************************/ 
    private boolean isLampOpen(int vbucks)
    {
        return eliminatedVbucks.contains(vbucks);
    }
       //Restarts the game from the beginning
    /************************************************************************
     * The following method restarts the game from the beginning
     *
     * input:Stage primaryStage
     * output: None
     **********************************************************************/ 
    private void restartGame(Stage primaryStage)
    { 
        // Close the current game screen
        primaryStage.close();
        
        // Create an instance of the first screen
        AladdinGame firstScreen = new AladdinGame();
        
        // Start the first screen
        firstScreen.start(new Stage());
    }
}
