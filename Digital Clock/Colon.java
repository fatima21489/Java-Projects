import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**********************************************************
 * The following class displays a colon
 * 
 * @author: Fatima Asif
 * @version: 26 January 2025
 **********************************************************/
public class Colon extends Text 
{
    public Colon() 
    {
        // displays the colon
        super(":");

        // Style the colon 
        this.setFont(Font.font("Times", 100)); 
        this.setTranslateY(340);
        this.setTranslateX(45);
        this.setFill(Color.RED); 
    }
}