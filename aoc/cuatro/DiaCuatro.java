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

        System.out.println(fileStrings);

    }

}