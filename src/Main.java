import java.io.IOException;
import java.util.Scanner;

/**
 * Dream - A game where the player must conquer the monsters in their dreams to stop having those terrible nightmares
 *
 * @author Diyon Wickrameratne
 */
public class Main {

    public static void main(String[] args) {

        //TODO CHANGE!!!
        DreamWorld world = new DreamWorld(5,5);

        Scanner scanner = new Scanner(System.in);

        String file = "";

        if (!world.getConfig().hasSaved()){
            file = "world.txt";
            System.out.println("Looks like you don't have any saved data. What is your name?");
            String name = scanner.nextLine();
            world.setPlayerName(name);
            System.out.println("**** Welcome to Dream, "+name+" ****");

        } else {
            System.out.println("Welcome back, "+world.getConfig().getPlayerName());
            file = "saved_world.txt";
        }

        try{
            world.parseFile(file);
        } catch (IOException | BadFileFormatException e){
            e.printStackTrace();
            System.exit(0);
        }

        //TODO REMOVE WHEN DONE
        System.out.println(world);

        while(world.getGameState()==GameState.INGAME){
            System.out.printf("[HP:%d S:%d] Type a command >", world.getPlayer().getHealth(),world.getPlayer().getSouls());
            world.play(scanner.nextLine());
        }

    }


}
