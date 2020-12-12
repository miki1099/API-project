import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {


    public Menu() {
        startMenu();
    }

    private void startMenu(){
        while(true){
            System.out.println("1. Get random criminal.");
            System.out.println("2. Show 10 youngest criminals.");
            System.out.println("3. Show 10 oldest criminals.");
            System.out.println("4. Exit.");

            Scanner scanner= new Scanner(System.in);
            System.out.print("> ");
            int ans;

            try{
                ans = scanner.nextInt();
                executeAction(ans);
            } catch (InputMismatchException e){

                System.out.println("You have to write number");
            }

        }

    }

    private void executeAction(int action){
        switch (action){
            case 1:
                //TODO get random criminal
                break;
            case 2:
                //TODO get youngest criminals
                break;
            case 3:
                //TODO get oldest criminals
                break;
            case 4:
                Runtime.getRuntime().exit(0);
                break;
            default:
                System.out.println("You have to choose number from menu list!");
        }
    }
}
