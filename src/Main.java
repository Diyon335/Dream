import Enums.GameState;
import Exceptions.BadFileFormatException;
import GameClasses.DreamWorld;

import java.io.IOException;
import java.util.Scanner;

/**
 * Dream - A game where the player must conquer the monsters in their dreams to stop having those terrible nightmares
 *
 * @author Diyon Wickrameratne
 */
public class Main {

    public static void main(String[] args) {

        DreamWorld world = new DreamWorld(35,42);

        Scanner scanner = new Scanner(System.in);

        String file = "";

        if (!world.getConfig().hasSaved()){
            file = "world.txt";
            System.out.println("Looks like you don't have any saved data. What is your name?");
            String name = scanner.nextLine();
            world.setPlayerName(name);
            System.out.println("**** Welcome to Dream, "+name+" ****");

            System.out.println("You are stuck in your dream. Defeat monsters in order to harvest");
            System.out.println("the strength required to beat the final boss: The Nightmare.");
            System.out.println("Once you kill The Nightmare, you are free from the torture in your dream.");
            System.out.println("\nSince you just entered your dream, you are too weak to kill The Nightmare.");
            System.out.println("You can travel across the world, and find monsters to kill.");
            System.out.println("By killing monsters, you obtain their souls. You can trade these souls for health points (HP).");
            System.out.println("You will also find food which you can eat for more HP.");
            System.out.println("\nYou will always have access to a map, so don't get scared.");
            System.out.println("However, to learn what the symbols are on the map, you must travel to them.");
            System.out.println("Your position on the map will always be denoted by \"p\"");
            System.out.println("If you get stuck at any moment, try the >help command to see your options");
            System.out.println("\nGOOD LUCK!");

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

        /*
        //TODO REMOVE WHEN DONE
        System.out.println(world);*/

        while(world.getGameState()== GameState.INGAME){
            System.out.printf("[HP:%d S:%d][%s] Type a command >", world.getPlayer().getHealth(),world.getPlayer().getSouls(),world.getPlayer().getFacing());
            world.play(scanner.nextLine());
        }

    }


}
