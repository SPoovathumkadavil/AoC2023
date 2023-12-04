import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DiaCuatro {

    public static void main(String[] args) {

        File supplementFile = new File("DiaCuatroSupplement.txt");
        ArrayList<String> fileStrings = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(supplementFile);

            while (fileScanner.hasNextLine()) {
                fileStrings.add(fileScanner.nextLine());
            }

            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Uh OH: " + e.getMessage());
            e.printStackTrace();
        }

        int sum = pt1GetPointWorth(fileStrings);
        System.out.println(sum);
    }

    public static int pt1GetPointWorth(ArrayList<String> strings) {

        ArrayList<ScratchCard> games = new ArrayList<>();
        int sum = 0;

        for (String str : strings)
            games.add(new ScratchCard(str));

        for (ScratchCard game : games) {

            for (int wn : game.winningNumbers) {
                if (game.availableNumbers.contains(wn)) {
                    game.pointWorth = (int) Math.pow(2, game.matching);
                    game.matching++;

                    /* 0, 1, 2, 3, 4 */
                    /* 1, 2, 4, 8, 16 */

                    game.points += game.pointWorth;

                }
            }
            sum += game.pointWorth;
            System.out.println(game.availableNumbers);
        }

        return sum;

    }

    static class ScratchCard {

        int id;
        int points = 0;
        int pointWorth = 0;
        int matching = 0;
        ArrayList<Integer> winningNumbers = new ArrayList<>();
        ArrayList<Integer> availableNumbers = new ArrayList<>();

        public ScratchCard(String cardString) {

            id = Integer.parseInt(cardString.split(":")[0].substring(5).trim());

            String wns = cardString.substring(10, 39);
            String ans = cardString.substring(42);

            for (int i = 0; i < wns.split(" ").length; i++)
                if (wns.split(" ")[i] != "")
                    winningNumbers.add(Integer.parseInt(wns.split(" ")[i]));

            for (int i = 0; i < ans.split(" ").length; i++)
                if (ans.split(" ")[i] != "")
                    availableNumbers.add(Integer.parseInt(ans.split(" ")[i]));

        }

    }

}