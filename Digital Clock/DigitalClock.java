import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/************************************************************
 * The following class represents a digital clock
 *
 * @author Fatima Asif
 * @version 26 January 2025
 ************************************************************/
public class DigitalClock extends Application
{
    Digit hourTens;
    Digit hourOnes;
    Digit minuteTens;
    Digit minuteOnes;
    Digit secondTens;
    Digit secondOnes;
    Text amPmLabel;
    
    @Override
    public void start(Stage myStage)
    {
        
        // Creating the digits for the clock
        Digit hourTens = new Digit();
        Digit hourOnes = new Digit();
        Digit minuteTens = new Digit();
        Digit minuteOnes = new Digit();
        Digit secondTens = new Digit();
        Digit secondOnes = new Digit();
        
        //setting digit 
        hourTens.setValue(1);
        hourOnes.setValue(1);
        minuteTens.setValue(5);
        minuteOnes.setValue(9);
        secondTens.setValue(5);
        secondOnes.setValue(5);
        
        //Decreases the size 
        hourTens.setScaleX(0.25);
        hourTens.setScaleY(0.25);
        
        hourOnes.setScaleX(0.25);
        hourOnes.setScaleY(0.25);
        
        minuteTens.setScaleX(0.25);
        minuteTens.setScaleY(0.25);
        
        minuteOnes.setScaleX(0.25);
        minuteOnes.setScaleY(0.25);
        
        secondTens.setScaleX(0.25);
        secondTens.setScaleY(0.25);
        
        secondOnes.setScaleX(0.25);
        secondOnes.setScaleY(0.25);
        
        //Am OR Pm formating
        Text amAndPm = new Text("AM");
        amAndPm.setFont(Font.font("Times", 30));
        amAndPm.setFill(Color.RED);
        amAndPm.setTranslateY(430);

        //Containers for hours, minutes, and seconds
        HBox hourBox = new HBox(hourTens, hourOnes);
        HBox minuteBox = new HBox(minuteTens, minuteOnes);
        HBox secondBox = new HBox(secondTens, secondOnes);
        
        //Grouping them together
        HBox display = new HBox(hourBox, new Colon(), minuteBox, new Colon(), secondBox,amAndPm);
        display.setSpacing(0.25);


        //Updating timeline using time
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), events -> 
        {
            incrementTime(secondOnes, secondTens, minuteOnes, minuteTens, hourOnes, hourTens, amAndPm);
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        //Setting scene
        Scene scene = new Scene(display,1000, 1024, Color.BLACK);
        myStage.setScene(scene);
        myStage.setTitle("Digital Clock");
        myStage.show();
    }
    /***********************************************************
     * The following methods updates the di git
     * 
     * input: Digit[] Array
     * output: none
     **********************************************************/
    protected void updateDigits(Digit[] digitArray) 
    {
        boolean carry = true;
        for (int i = 0; i < digitArray.length; i++) 
        {
            Digit digit = digitArray[i];
            if (digit.getValue() >= 9) 
            {
                    digit.setValue(0);
            } else if (carry) 
            {
                    carry = digit.inc();
            }
        }
    }
    
    /*********************************************************************
     * The following method increments the time
     * 
     * Inputs:Digit secondOnes, Digit secondTens, Digit minuteOnes, 
     * Digit minuteTens, Digit hourOnes, Digit hourTens, Text amPmLabel
     * Outputs: none
     *********************************************************************/
    protected void incrementTime(Digit secondOnes, Digit secondTens, Digit minuteOnes, Digit minuteTens, Digit hourOnes, Digit hourTens, Text amAndPm) {
    // Incrementing seconds when s = 9
    if (secondOnes.getValue() == 9) 
    {
        secondOnes.setValue(0); 
        
        // Incrementing seconds when s = 5
        if (secondTens.getValue() == 5) 
        {
            secondTens.setValue(0);
            
            // Incrementing minutes when m = 9
            if (minuteOnes.getValue() == 9) 
            {
                minuteOnes.setValue(0);
                
                // Incrementing minutes when m = 5
                if (minuteTens.getValue() == 5) 
                {
                    minuteTens.setValue(0);
                    // Incrementing hours from 12:59 to 01:00
                    if (hourTens.getValue() == 1 && hourOnes.getValue() == 2) 
                    {
                        hourOnes.setValue(1);
                        hourTens.setValue(0);
                    } // Changes from 09 to 10
                    else if (hourTens.getValue() == 0 && hourOnes.getValue() == 9) 
                    {
                        hourOnes.setValue(0);
                        hourTens.setValue(1); 
                    } // Tncrements from 11:59 to 12:00
                    else if (hourTens.getValue() == 1 && hourOnes.getValue() == 1) 
                    {
                        hourOnes.setValue(2);
                        // Changes from AM to PM
                        if (amAndPm.getText().equals("AM")) 
                        { 
                            amAndPm.setText("PM"); 
                        } // Changes from PM to aM
                        else 
                        {
                            amAndPm.setText("AM"); 
                        }
                    } 
                    else 
                    {
                        hourOnes.inc();
                    }
                } 
                else 
                {
                    minuteTens.inc();
                }
            } 
            else 
            {
                minuteOnes.inc();
            }
        } 
        else 
        {
            secondTens.inc();
        }
    } 
    else 
    {
        secondOnes.inc();
    }
        }
    
    protected void toggleAmPm(Text amAndPm) 
    {
        if (amAndPm.getText().equals("AM")) 
        {
            amAndPm.setText("PM");
        } else 
        {
            amAndPm.setText("AM");
        }
    }
}

