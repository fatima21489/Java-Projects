import java.util.Scanner;
/**************************************************************
 * The following program will let you book tictkets in cinema
 *
 * Author: Fatima Asif     
 * Date of completion: 24 July 2024
 *************************************************************/
public class cinemaManager {
    public static void main (String [] args){
      Scanner input = new Scanner(System.in);
        
           System.out.println("Enter the number of rows: ");
            int numOfRows = input.nextInt();
           
           System.out.println("Enter the number of seats in each row: ");
            int seatInRow = input.nextInt();
            
           char[][] Cinema = new char[numOfRows][seatInRow];
           
           for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < seatInRow; j++) {
                Cinema[i][j] = 'S';
            }
        }
        int TotalSeats = numOfRows * seatInRow;
        int tickets = 0;
        int Total = 0;
        double precentage = 0.0;
         menu();
        boolean booked = true;    
        boolean running = true;

           while(running) {
           int userInput = input.nextInt();
           
           
           switch (userInput){     
           case 1:
    
                 printCinema(Cinema,seatInRow,numOfRows);
                 
                 break;
               case 2:
                boolean Valid = false;
               while (!Valid) {
                System.out.println("Enter a row number: ");
                 int rows = input.nextInt();
                
                System.out.println("Enter a seat number in that row: ");
                 int seats = input.nextInt();
                
        
                if(rows <= 0 || seats <= 0 || rows > numOfRows || seats > seatInRow) {
                    System.out.println("Wrong input!");       
                } else if (Cinema[rows - 1][seats - 1] == 'B') {
                    System.out.println("That ticket has already been purchased!");
                } else {
                int price = ticketPrice(numOfRows, seatInRow, rows);
                System.out.println("Ticket price:$" + price);
                Total = Total + price;
                
                 Cinema[rows - 1][seats - 1] = 'B';
                  
                 printCinema(Cinema, seatInRow, numOfRows);
                 tickets++;
                 precentage = ((double)tickets/TotalSeats) * 100;
                 Valid = true;
                 
                } 
                }  
        
                 break;
                 case 3:
                    int Tincome = calculateIncome(numOfRows, seatInRow);
                    System.out.println("Number of purchased tickets: " + tickets);
                    System.out.printf("Percentage: %.2f%%%n", precentage);
                    System.out.println("Current income: $" + Total);
                    System.out.println("Total income: $" + Tincome);
                    break;
                case 0:
                    running = false;
                    break;
                 default:
                    System.out.println("Invalid choice. Please select a valid option.");
      
           }
                  
        if (running ){
                menu();
        }
       
        }
    }
    
    public static void printCinema(char[][] cinema, int seats, int rows) {
        
        System.out.println("Cinema:");
        System.out.print("  ");
        for (int i = 1; i <= seats; i++) {
                        System.out.print(i + " ");
                    }
                    System.out.println();
        for (int i = 0; i < cinema.length; i++) {
                        System.out.print((i + 1) + " ");
                        for (int j = 0; j < cinema[i].length; j++) {
                            System.out.print(cinema[i][j] + " ");
                        }
                        System.out.println();
                    }
    }
        public static int ticketPrice(int seat, int row, int RowsChosen){
        int numOfSeats= row * seat;
        int totalIncome;
    
        if (numOfSeats <= 60) {          
                        totalIncome = 10;
                    } else {
                        int frontHalf = row / 2;
                        int backHalf = RowsChosen - (RowsChosen / 2); 
                        if (RowsChosen <= frontHalf) {
                            totalIncome = 10;
                        } else {
                            totalIncome = 8;
                        }
                    }
                

         return totalIncome;           
                    
    }
    
    public static int calculateIncome(int row, int seat) {
        int numOfSeats= row * seat;
        int totalIncome;
    
        if (numOfSeats <= 60) {          
                        return numOfSeats * 10;
                    } else {
                        int frontHalf = row / 2;
                        int backHalf = row - (row / 2); 
                         return (frontHalf * seat * 10) + (backHalf * seat * 8);
                    }
            
                    
    }
    public static void menu(){
           System.out.println("1. Show the seats");
           System.out.println("2. Buy a ticket");
           System.out.println("3. Statistics");
           System.out.println("0. Exit");
    
    }                
    }

