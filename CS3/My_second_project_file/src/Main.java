import java.util.*;
import java.io.*;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Scanner scan = new Scanner(new File("Pokedex.txt"));
        Map<String, Pokemon> pokemonList = new HashMap<>();
        Map<String, Player> game = new HashMap<>();
        while(scan.hasNextLine())
        {
            Scanner line = new Scanner(scan.nextLine());
            String name = line.next();
            int hp = line.nextInt();
            int attack = line.nextInt();
            int defence = line.nextInt();
            int speed = line.nextInt();
            pokemonList.put(name, new Pokemon(name, attack, defence, speed, hp));
        }

        Scanner scanner = new Scanner(System.in);
        String again = "yes";
        while(again.equals("yes") || again.equals("y"))
        {
            String[] actions = {"reset the order", "add player",
                    "modify player", "print current list of players"};
            for(int i = 0; i < actions.length; i++)
                System.out.println(i + 1 + ". " + actions[i]);
            Scanner ins = new Scanner(System.in);
            String given = "bad";
            while(!isValid(given, 5))
            {
                System.out.print("What do you want to do? ");
                given = ins.nextLine();
            }
            int userAction = Integer.parseInt(given);
            String[] prefs = {"attack", "defense", "hp", "name", "speed"};
            if(userAction == 1) {
                String choices = "bad";
                while(!check(prefs, choices))
                {
                    System.out.print("How do you want to sort it (\"attack\", \"defense\", \"hp\", \"name\", \"speed\")? ");
                    choices = ins.nextLine().strip();
                }
                for (Player player : game.values())
                    player.reset(choices);
            }
            else if(userAction == 2) {
                System.out.print("Player's username to add to the game: ");
                String nams = ins.nextLine();
                game.put(nams, new Player(nams));
            }
            else if(userAction == 3) {
                String givenPlayer = "bad";
                while(!game.containsKey(givenPlayer))
                {
                    System.out.print("What player do you want to modify?");
                    givenPlayer = ins.nextLine();
                }
                Player player = game.get(givenPlayer);

                System.out.print("Do you want to add pokemon?");
                if(ins.nextLine().equals("yes")) {
                    String pok = "bad";
                    while (!pokemonList.containsKey(pok)) {
                        System.out.print("What pokemon do you want to add? ");
                        pok = ins.nextLine();
                    }
                    player.addPokemon(pokemonList.get(pok));
                }
                System.out.print("Do you want to remove pokemon?");
                if(ins.nextLine().equals("yes")) {
                    String pok = "bad";
                    while (!pokemonList.containsKey(pok)) {
                        System.out.print("What pokemon do you want to add? ");
                        pok = ins.nextLine();
                    }
                    player.removePokemon(pokemonList.get(pok));
                }
            }
            else
                for(Player player: game.values())
                    System.out.println(player.toString());
            System.out.println("More pokemons are being added");
            for(String name: pokemonList.keySet())
                Pokemon.repopulate(name);
            System.out.print("again?");
            again = scanner.next();
            System.out.println();
        }
        scanner.close();
    }

    public static boolean isValid(String str, int range) {
        try {
            int check = Integer.parseInt(str);
            if(check < range)
                return true;
        } catch(NumberFormatException e){
            return false;
        }
        return false;
    }

    private static boolean check(String[] arr, String toCheckValue)
    {
        for (String element : arr)
            if (element.equals(toCheckValue))
                return true;
        return false;
    }
}