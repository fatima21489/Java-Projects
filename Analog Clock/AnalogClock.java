import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/*************************************************************
 * In this program we are making a clock face object.
 *
 * @author Fatima Asif
 * @version 18 January 2025
 *************************************************************/
public class AnalogClock extends Application
{
    @Override
    public void start(Stage myStage){

        Circle cir1 = new Circle(250, 300, 100);
        cir1.setFill(Color.BLACK);
        
        Circle cir2 = new Circle(250, 300, 90);
        cir2.setFill(Color.WHITE); 
        
        Circle cir3 = new Circle(330, 205, 50);
        cir3.setFill(Color.BLACK);
        
        Circle cir4 = new Circle(155, 215, 50);
        cir4.setFill(Color.BLACK);
        
        Text num12 = new Text(243,225,"12");
        num12.setFill(Color.DARKRED);
        Text num11 = new Text(200,235,"11");
        Text num10 = new Text(175,265,"10");
        num10.setFill(Color.DARKRED);
        Text num9 = new Text(165,305,"9");
        Text num8 = new Text(175,350,"8");
        num8.setFill(Color.DARKRED);
        Text num7 = new Text(200,375,"7");
        Text num6 = new Text(245,385,"6");
        num6.setFill(Color.DARKRED);
        Text num5 = new Text(290,375,"5");
        Text num4 = new Text(315,350,"4");
        num4.setFill(Color.DARKRED);
        Text num3 = new Text(330,300,"3");
        Text num2 = new Text(320,265,"2");
        num2.setFill(Color.DARKRED);
        Text num1 = new Text(290,235,"1");
        
        ClockHand hourHand = new ClockHand(250, 300, 50, Color.rgb(139, 0, 0));
        ClockHand minuteHand = new ClockHand(250, 300, 65, Color.GOLD);
        ClockHand secondHand = new ClockHand(250, 300, 75, Color.BLACK);

        hourHand.setAngle(270); 
        minuteHand.setAngle(0); 
        secondHand.setAngle(0); 
        
        Group root = new Group(cir1,cir3,cir4, cir2,
                               num12, num11, num10,
                               num9, num8, num7,
                               num6, num5, num4,
                               num3,num2, num1,
                               hourHand, minuteHand,secondHand );
                               
        Timeline time = new Timeline(
                            new KeyFrame(Duration.seconds(1),
                            event -> {
                                secondHand.rotateByDegrees(6);
                                minuteHand.rotateByDegrees(0.1);
                                hourHand.rotateByDegrees(1.0 / 120);
                            })
                        );
        time.setCycleCount(Timeline.INDEFINITE);
        time.play();
                                               
        Scene scene = new Scene(root, 500, 600);
        
        myStage.setTitle("My First Clock");
        myStage.setScene(scene);
        myStage.show();
        
    }
    public static void main(String[] args) 
    {
        launch(args);
    }
}
