import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DiaDos {

    public static void main(String[] args) {
        
        // Load Supplement File
        File file = new File("DiaDosSupplement.txt");
        ArrayList<String> encryptedStrings = new ArrayList<>();

        // Load encrypted strings
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                encryptedStrings.add(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Uh Oh");
            e.printStackTrace();
        }

        int MAX_R_ALLOWED = 12;
        int MAX_G_ALLOWED = 13;
        int MAX_B_ALLOWED = 14;

        int idSum = ptTwoPowerOfSet(encryptedStrings, MAX_R_ALLOWED, MAX_G_ALLOWED, MAX_B_ALLOWED);
        System.out.println(idSum);

    }

    public static int ptOneSumIsPossible(ArrayList<String> gs, int maxR, int maxG, int maxB) {
        ArrayList<Game> games = new ArrayList<>();
        for (String g : gs) {
            games.add(new Game(g));
        }

        int idSum = 0;

        for (Game game : games) {
            if (game.getMaxRed() <= maxR && game.getMaxGreen() <= maxG && game.getMaxBlue() <= maxB) {
                System.out.println("ID: "+game.id +"  R: "+game.rShown);
                idSum += game.id;
            }
        }

        return idSum;
    }

    public static int ptTwoPowerOfSet(ArrayList<String> gs, int maxR, int maxG, int maxB) {
        ArrayList<Game> games = new ArrayList<>();
        for (String g : gs) {
            games.add(new Game(g));
        }

        int powerSum = 0;

        for (Game game : games) {
            System.out.println("ID: "+game.id +"  R: "+game.rShown);
            powerSum += game.power;
        }

        return powerSum;
    }

    static class Game {

        int id;
        int timesCubesShown = 1;
        int power;
        ArrayList<Integer> rShown = new ArrayList<>();
        ArrayList<Integer> gShown = new ArrayList<>();
        ArrayList<Integer> bShown = new ArrayList<>();

        public Game(String gameString) {

            String[] spls = gameString.split(" ");
            this.id = Integer.parseInt(spls[1].substring(0, spls[1].length()-1));

            String[] sections = gameString.split(";");
            for (int i = 0; i < sections.length; i++) {
                String[] secSS = sections[i].split(" ");
                for (int j = 0; j < secSS.length; j++) {
                    if (secSS[j].contains("red")) rShown.add(Integer.parseInt(secSS[j-1]));
                    if (secSS[j].contains("green")) gShown.add(Integer.parseInt(secSS[j-1]));
                    if (secSS[j].contains("blue")) bShown.add(Integer.parseInt(secSS[j-1]));
                }
                if (rShown.size() < timesCubesShown) rShown.add(0);
                if (gShown.size() < timesCubesShown) gShown.add(0);
                if (bShown.size() < timesCubesShown) bShown.add(0);
                timesCubesShown++;
            }

            calculatePower();

        }

        public int getMaxRed() {
            int max = rShown.get(0);
            for (int i : rShown) { if (i > max) max = i; }
            return max;
        }

        public int getMaxGreen() {
            int max = gShown.get(0);
            for (int i : gShown) { if (i > max) max = i; }
            return max;
        }

        public int getMaxBlue() {
            int max = bShown.get(0);
            for (int i : bShown) { if (i > max) max = i; }
            return max;
        }

        public void calculatePower() {
            power = getMaxRed() * getMaxGreen() * getMaxBlue();
        }

    }

}
