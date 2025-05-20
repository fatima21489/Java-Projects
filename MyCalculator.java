import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;

/*********************************************************************************
 * The following class makes a claculator
 *
 * @Author:  Fatima Asif
 * @Version: 11 Febuary 2025
 *********************************************************************************/
public class MyCalculator extends Application
{
    //Instance Data
    protected Label inputDisplay;
    protected Label outputDisplay;
    protected StringBuilder currentInput = new StringBuilder();
    protected int gradeLevel = 4;

    @Override
    public void start(Stage myStage)
    {
        //Creating a brand Image
        ImageView brandImage = new ImageView(new Image("BrandName.png"));
        brandImage.setFitWidth(300);
        brandImage.setPreserveRatio(true);
       
        //Creating a display Field
        inputDisplay = new Label(" ");
        outputDisplay = new Label(" ");
        inputDisplay.setStyle("-fx-font-size: 20px; -fx-alignment: center-right;");
        outputDisplay.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-alignment: center-right;");
       
        //Creating a display box
        VBox displayBox = new VBox(inputDisplay, outputDisplay);
        displayBox.setStyle("-fx-border-color: black; -fx-padding: 10px;");
        displayBox.setMinHeight(60);
        displayBox.setAlignment(Pos.CENTER_RIGHT);

        //To select the grade
        //Create a toggle group and group the radio buttons
        ToggleGroup gradeGroup = new ToggleGroup();
        RadioButton grade4 = new RadioButton("Grade 4");
        RadioButton grade5 = new RadioButton("Grade 5");
        RadioButton grade6 = new RadioButton("Grade 6");
       
        //add the buttons to radio buttons to toggle group
        grade4.setToggleGroup(gradeGroup);
        grade5.setToggleGroup(gradeGroup);
        grade6.setToggleGroup(gradeGroup);
        //Setting grade 4 as default
        grade4.setSelected(true);

        //Setting what each buttons do
        grade4.setOnAction(e -> gradeLevel = 4);
        grade5.setOnAction(e -> gradeLevel = 5);
        grade6.setOnAction(e -> gradeLevel = 6);
       
        //Creating a HBox and adding all the boxes together with 10 spacing
        HBox grades = new HBox(10, grade4, grade5, grade6);
        //Alligning all the boxes in center of each box
        grades.setAlignment(Pos.CENTER);

        //Creating a grid pane to add buttons
        GridPane myGB = new GridPane();
        myGB.setHgap(10);
        myGB.setVgap(10);
        myGB.setAlignment(Pos.CENTER);
       
        //Creating, formating and styling button 7
        Button button7 = new Button("7");
        button7.setMinSize(50, 50);
        button7.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        button7.setOnAction(e -> clickButton("7"));
       
        //Creating, formating and styling button 8
        Button button8 = new Button("8");
        button8.setMinSize(50, 50);
        button8.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        button8.setOnAction(e -> clickButton("8"));
       
        //Creating, formating and styling button 9
        Button button9 = new Button("9");
        button9.setMinSize(50, 50);
        button9.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        button9.setOnAction(e -> clickButton("9"));
       
        //Creating, formating and styling division button
        Button divideButton = new Button("÷");
        divideButton.setMinSize(50, 50);
        divideButton.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        divideButton.setOnAction(e -> clickButton("÷"));
       
        //Creating, formating and styling button 4
        Button button4 = new Button("4");
        button4.setMinSize(50, 50);
        button4.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        button4.setOnAction(e -> clickButton("4"));
       
        //Creating, formating and styling button 5
        Button button5 = new Button("5");
        button5.setMinSize(50, 50);
        button5.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        button5.setOnAction(e -> clickButton("5"));
       
        //Creating, formating and styling button 6
        Button button6 = new Button("6");
        button6.setMinSize(50, 50);
        button6.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        button6.setOnAction(e -> clickButton("6"));
       
        //Creating, formating and styling multiplication button
        Button multiplyButton = new Button("×");
        multiplyButton.setMinSize(50, 50);
        multiplyButton.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        multiplyButton.setOnAction(e -> clickButton("×"));
       
        //Creating, formating and styling button 1
        Button button1 = new Button("1");
        button1.setMinSize(50, 50);
        button1.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        button1.setOnAction(e -> clickButton("1"));
       
        //Creating, formating and styling button 2
        Button button2 = new Button("2");
        button2.setMinSize(50, 50);
        button2.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        button2.setOnAction(e -> clickButton("2"));
       
        //Creating, formating and styling button 3
        Button button3 = new Button("3");
        button3.setMinSize(50, 50);
        button3.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        button3.setOnAction(e -> clickButton("3"));
       
        //Creating, formating and styling subtraction button
        Button subtractButton = new Button("-");
        subtractButton.setMinSize(50, 50);
        subtractButton.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        subtractButton.setOnAction(e -> clickButton("-"));
       
        //Creating, formating and styling button 0
        Button button0 = new Button("0");
        button0.setMinSize(50, 50);
        button0.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        button0.setOnAction(e -> clickButton("0"));
       
        //Creating, formating and styling decimal button
        Button decimalButton = new Button(".");
        decimalButton.setMinSize(50, 50);
        decimalButton.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        decimalButton.setOnAction(e -> clickButton("."));
       
        //Creating, formating and styling equals button
        Button equalsButton = new Button("=");
        equalsButton.setMinSize(50, 50);
        equalsButton.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        equalsButton.setOnAction(e -> clickButton("="));
       
        //Creating, formating and styling addition button
        Button addButton = new Button("+");
        addButton.setMinSize(50, 50);
        addButton.setStyle("-fx-background-color:#8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        addButton.setOnAction(e -> clickButton("+"));
       
        //Creating, formating and styling clear button
        Button clearButton = new Button("AC");
        clearButton.setMinSize(220, 50);
        clearButton.setStyle("-fx-background-color: #8B0000; -fx-text-fill: white; -fx-font-size: 16px;");
        clearButton.setOnAction(e -> clearCalculator());
       
        //Adding all the buttons to grid pane using coordinates
        myGB.add(button7, 0, 0);
        myGB.add(button8, 1, 0);
        myGB.add(button9, 2, 0);
        myGB.add(divideButton, 3, 0);
       
        myGB.add(button4, 0, 1);
        myGB.add(button5, 1, 1);
        myGB.add(button6, 2, 1);
        myGB.add(multiplyButton, 3, 1);
       
        myGB.add(button1, 0, 2);
        myGB.add(button2, 1, 2);
        myGB.add(button3, 2, 2);
        myGB.add(subtractButton, 3, 2);
       
        myGB.add(button0, 0, 3);
        myGB.add(decimalButton, 1, 3);
        myGB.add(equalsButton, 2, 3);
        myGB.add(addButton, 3, 3);
        myGB.add(clearButton, 0, 4, 4, 1);

        //Putting together everything in the VBox
        VBox root = new VBox(10, brandImage, displayBox, grades, myGB);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: white; -fx-padding: 20px;");

        myStage.setScene(new Scene(root, 350, 550));
        myStage.setTitle("Calculator");
        myStage.show();
    }
    /***********************************************************************
     * The following methods tells the program what to do when the buttons
     * are pressed
     *
     * Input: String text
     * Output: none
     **********************************************************************/
    public void clickButton(String text)
    {
        //When equals is pressed the result is calculated
        if (text.equals("="))
        {
            calculateResult();
        }
        else if (text.equals("."))
        {
            //makes sure when decimal is pressed appears only once for grade 6
            if (gradeLevel == 6 && !currentInput.toString().contains("."))
            {
                //the decimal point is added to text
                currentInput.append(text);
                //input is displayed
                inputDisplay.setText(currentInput.toString());
            }
        }
        else
        {
            // adds operators
            currentInput.append(text);
            //input is displayed
            inputDisplay.setText(currentInput.toString());
        }
    }

    /***********************************************************************
     * The following method calculates the result
     *
     * Input: none
     * Output: none
     **********************************************************************/
    public void calculateResult()
    {
        //converts the string bulider to a string
        String input = currentInput.toString();
       
        //Finding the operator
        String operator = identifyOperator(input);
       
        //Split input into two parts by operator
        String[] parts = input.split("\\" + operator);
       
        String num1Str = parts[0];
        String num2Str = parts[1];
       
        int num1 = (int) Double.parseDouble(num1Str);  
        int num2 = (int) Double.parseDouble(num2Str);
       
        //Calculating result
        String result = calculate(num1, num2, operator);
        outputDisplay.setText(result);
    }
   
    /***********************************************************************
     * The following method checks which operator a user uses
     *
     * Input: String input
     * Output: returns operators
     **********************************************************************/
    public String identifyOperator(String input)
    {
        //if the input contains + return +
        if (input.contains("+"))
        {
            return "+";
        }
        //if the input contains - return -
        if (input.contains("-"))
        {
            return "-";
        }
        //if the input contains × return ×
        if (input.contains("×"))
        {
             return "×";
        }
        //if the input contains ÷ return ÷
        if (input.contains("÷"))
        {
             return "÷";
        }
        return "";
    }
   
    /***********************************************************************
     * The following method takes in two numbers and one operator and
     * calculates the result
     * Input: int num1, int num2, String operator
     * Output: String result
     **********************************************************************/
    public String calculate(int num1, int num2, String operator)
    {
        String result = " ";
        //Perform addition when operator is add
        if (operator.equals("+"))
        {
            result = String.valueOf(num1 + num2);
        }
        //Perform subtraction when operator is minus
        else if (operator.equals("-"))
        {
            //if the grade level is 4 and num1 is less than num2 we won't subtract
            if (gradeLevel == 4 && num1 < num2)
            {
                result = "Error: No negatives in grade 4";
            }
            else
            {
                result = String.valueOf(num1 - num2);
            }
        }
        //Perform multiplication when operator is multiply
        else if (operator.equals("×"))
        {
            result = String.valueOf(num1 * num2);
        }
        //Perform division when operator is divide
        else if (operator.equals("÷"))
        {
            if (num2 == 0)
            {
                result = "Error: Division by Zero";
            }
            else if (gradeLevel == 4)
            {
                //if the remainder is zero then return string value of num1 divided num2
                if (num1 % num2 == 0)
                {
                    result = String.valueOf(num1 / num2);
                }
                else
                {
                    result = "Error: No Remainders in Grade 4";
                }
            }
            // For grade 5,returns the remainder
            else if (gradeLevel == 5)
            {
                result = (num1 / num2) + " R " + (num1 % num2);
            }
            //for grade 6, returns formated 4 digit double
            else
            {
                result = String.format("%.4f", (double) num1 / num2);
            }
        }
        return result;
    }

    /********************************************************************
     * The following method clears both input and output displays
     * Input: none
     * Output: none
     ********************************************************************/
    public void clearCalculator()
    {
        currentInput.setLength(0);
        inputDisplay.setText("");
        outputDisplay.setText("");
    }
}