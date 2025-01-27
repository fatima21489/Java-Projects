import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

/***********************************************************
 * The following class represents one single digit 
 *
 * @author Fatima Asif
 * @version 26 January 2025
 **********************************************************/
public class Digit extends Pane
{
    RedDash d1,d2,d3,d4,d5,d6,d7;
    int value;

    /*******************************************************
     * The following constructors sets the value to zero 
     * using setNum() method and creates a digit using 
     * initializeSegments() method. 
     * 
     *******************************************************/
    public Digit()
    {
        this.value = 0;
        createADigit();
        setNum(value);
    }
    
    /**********************************************************
     * The following constructor assigns a value 
     * 
     * Parameter: value
     *********************************************************/
    public Digit(int value) 
    {
        this.value = value;
    }
    
    /*********************************************************
     * The following method creates a digit
     * 
     * input: none
     * output: none
     *********************************************************/
    protected void createADigit() 
    {
        d1 = new RedDash(50, 350);
        
        d2 = new RedDash(50, 570);
        
        d3 = new RedDash(170, 235);
        d3.setRotate(90);
        
        d4 = new RedDash(280, 350);
        
        d5 = new RedDash(280, 570);
        
        d6 = new RedDash(165, 460);
        d6.setRotate(90);
        
        d7 = new RedDash(165, 675);
        d7.setRotate(90);

        this.getChildren().addAll(d1, d2, d3, d4, d5, d6, d7);
    }
    
    /**********************************************************
     * The following method is a accssor method
     * 
     * intput: none
     * output: value
     *********************************************************/
    public int getValue()
    {
        return value;
    }
    
    /**********************************************************
     * The following method is a mutator method
     * 
     * intput: newValue
     * output: none
     *********************************************************/
    public void setValue(int newValue) 
    {
        this.value = newValue;
        setNum(value);
    }
    
    /**********************************************************
     * The following method increments the value
     * 
     * intput: none
     * output: boolean true or false
     *********************************************************/
    protected boolean inc()
    {
        boolean carryOver = false;
        value++;
        if(value > 9)
        {
            value = 0;
            carryOver = true;
        }
            
        setNum(value);
        return carryOver;
    }
    
    /**********************************************************
     * The following method sets a number 
     * 
     * intput: int num
     * output: none
     *********************************************************/
    public void setNum(int num)
    {
        switch(num)
        {
            case 0:
                //prints 0
                d1.set(true);
                d2.set(true);
                d3.set(true);
                d4.set(true);
                d5.set(true);
                d6.set(false);
                d7.set(true);
                break;
            case 1:
                //prints 1
                d1.set(true);
                d2.set(true);
                d3.set(false);
                d4.set(false);
                d5.set(false);
                d6.set(false);
                d7.set(false);
                break;
            case 2:
                // prints 2
                d1.set(false);
                d2.set(true);
                d3.set(true);
                d4.set(true);
                d5.set(false);
                d6.set(true);
                d7.set(true);
                break;
            case 3:
                //prints 3
                d1.set(false);
                d2.set(false);
                d3.set(true);
                d4.set(true);
                d5.set(true);
                d6.set(true);
                d7.set(true);
                break;
            case 4:
                //prints 4
                d1.set(true);
                d2.set(false);
                d3.set(false);
                d4.set(true);
                d5.set(true);
                d6.set(true);
                d7.set(false);
                break;
            case 5:
                //prints 5
                d1.set(true);
                d2.set(false);
                d3.set(true);
                d4.set(false);
                d5.set(true);
                d6.set(true);
                d7.set(true);
                break;    
            case 6:
                //prints 6
                d1.set(true);
                d2.set(true);
                d3.set(true);
                d4.set(false);
                d5.set(true);
                d6.set(true);
                d7.set(true);
                break; 
            case 7:
                //prints 7
                d1.set(false);
                d2.set(false);
                d3.set(true);
                d4.set(true);
                d5.set(true);
                d6.set(false);
                d7.set(false);
                break;
            case 8:
                // prints 8
                d1.set(true);
                d2.set(true);
                d3.set(true);
                d4.set(true);
                d5.set(true);
                d6.set(true);
                d7.set(true);
                break;
            case 9:
                //prints 9
                d1.set(true);
                d2.set(false);
                d3.set(true);
                d4.set(true);
                d5.set(true);
                d6.set(true);
                d7.set(true);
                break;
        }
    }
}