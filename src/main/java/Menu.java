import util.ActionsImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private ActionsImpl actions;

    public Menu() {
        actions = new ActionsImpl();
        startMenu();
    }

    private void startMenu(){
        while(true){
            System.out.println("1. Get random criminal.");
            System.out.println("2. Show youngest criminal.");
            System.out.println("3. Show oldest criminal.");
            System.out.println("4. Show criminals by hair color.");
            System.out.println("5. Update database.");
            System.out.println("6. Export criminals to excel.");
            System.out.println("7. Exit.");

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
                actions.getRandomCriminal();
                break;
            case 2:
                actions.getYoungestCriminal();
                break;
            case 3:
                actions.getOldestCriminal();
                break;
            case 4:
                actions.getCriminalsByHairColor();
                break;
            case 5:
                actions.updateDataBase();
                break;
            case 6:
                actions.exportAllToExcel();
                break;
            case 7:
                Runtime.getRuntime().exit(0);
                break;

            default:
                System.out.println("You have to choose number from menu list!");
        }
    }
}
