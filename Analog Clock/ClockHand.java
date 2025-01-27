import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

/********************************************************
 * This class designs clock hands and rotates them
 *
 * @author Fatima Asif
 * @version 21 January 2025
 ********************************************************/
public class ClockHand extends Group
{
    protected int x,y;
    protected int length;
    protected Color color;
    protected Rotate rotate;
    protected Line handLine;
    
    public ClockHand(int x, int y, int length, Color color)
    {
        this.x = x;
        this.y = y;
        this.length = length;
        this.color = color;
        
        printHand();
        
        rotate = new Rotate(0, x, y);
        this.getTransforms().add(rotate);
    }
    
    protected void printHand()
    {
       Circle circle1 = new Circle(x, y, 5);
       circle1.setFill(Color.BLACK);
       
       Line l1 = new Line(x, y, x, y - length);
       l1.setStroke(color);
       l1.setStrokeWidth(2);
    
       this.getChildren().addAll(circle1,l1);
    }
    
    public void setAngle(double angle) {
        double radians = Math.toRadians(angle);
        double endX = x + length * Math.sin(radians);
        double endY = y - length * Math.cos(radians);
        
        ((Line) this.getChildren().get(1)).setEndX(endX);
        ((Line) this.getChildren().get(1)).setEndY(endY);
    }
    
    public void rotateByDegrees(double degrees)
    {
        rotate.setAngle(rotate.getAngle() + degrees);
    }
}
