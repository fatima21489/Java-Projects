import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.scene.shape.Polyline;

/************************************************************
 * The following class creates a dash using polyline
 *
 * @author Fatima Asif
 * @version 26 January 2025
 ************************************************************/
public class RedDash extends Group
{
    //Instance data
    protected int x;
    protected int y;
    protected Color color;
    protected boolean isOn;
    protected Polyline dash;
    
    /****************************************************
     * Following method is a constructor which draws a 
     * dash and changes its color
     * 
     * Parameters: x, y
     ****************************************************/
    public RedDash(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.color = Color.RED;
        this.isOn = true;
        DrawADash();
        changeColor();
    }
    
    /****************************************************
     * Following method draws a dash using polyline
     * 
     * input: none
     * output: none
     ****************************************************/
    protected void DrawADash()
    {
        dash = new Polyline(x+50, y-50, 
                            x+25, y-100,
                            x+25, y-225,
                            x+50, y-275,
                            x+75, y-225, 
                            x+75, y-100, 
                            x+50, y-50);

        this.getChildren().add(dash);
    }
    
    /****************************************************
     * Following method changes color of the line
     * 
     * input: none
     * output: none
     ****************************************************/
    protected void changeColor() {
        if (isOn) 
        {
            dash.setFill(Color.RED);
            dash.setStroke(Color.RED);
        } else 
        {
            dash.setFill(Color.rgb(30, 30, 30)); 
            dash.setStroke(Color.rgb(30, 30, 30));
        }
    }
    
    /***********************************************
     * Following method sets the dash as on
     * 
     * input: none
     * output: none
     **********************************************/
    protected void set(boolean on)
    {
        this.isOn = on;
        changeColor();
    }
}
