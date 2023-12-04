import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DiaUno {

    public static void main(String[] args) {

        // Load Supplement File
        File file = new File("DiaUnoSupplement.txt");
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

        int sum = 0;
        for (String enc : encryptedStrings) {
            sum += ptTwoGetDigitSum(enc);
        }

        System.out.println(sum);

    }

    public static int ptOneGetDigitSum(String encryptedString) {

        String calDigits = "";

        for (int i = 0; i < encryptedString.length(); i++) {
            String pos = encryptedString.substring(i, i+1);
            if (isNumeric(pos)) {
                calDigits += pos;
                break;
            }
        }

        for (int i = encryptedString.length(); i > 0; i--) {
            String pos = encryptedString.substring(i-1, i);
            if (isNumeric(pos)) {
                calDigits += pos;
                break;
            }
        }

        return Integer.parseInt(calDigits);
    }

    public static int ptTwoGetDigitSum(String encryptedString) {

        String calDigits = "";

        for (int i = 0; i < encryptedString.length(); i++) {
            String check = encryptedString.substring(i, i+1);
            if (isNumeric(check)) {
                calDigits+=check;
                break;
            }
            int letterDigit = findLetterDigit(encryptedString.substring(i));
            if (letterDigit != -1) {
                calDigits += letterDigit;
                break;
            }
        }

        for (int i = encryptedString.length(); i > 0; i--) {
            String check = encryptedString.substring(i-1, i);
            if (isNumeric(check)) {
                calDigits+=check;
                break;
            }
            int letterDigit = findLetterDigit(encryptedString.substring(i-1));
            if (letterDigit != -1) {
                calDigits += letterDigit;
                break;
            }
        }

        return Integer.parseInt(calDigits);

    }

    /** 
     * @deprecated
     * Returns the first letter digit
     * @param str
     * @return -1 if no digit found
     */
    public static int findFirstLetterDigit(String str) {

        for (int i = 0; i < str.length(); i++) {
            switch (str.substring(i, i+1).toLowerCase()) {
                case "z":
                    if (i+4 > str.length()) break;
                    else if (str.substring(i, i+4).toLowerCase().equals("zero")) return 0;
                    break;
                case "o":
                    if (i+3 > str.length()) break;
                    else if (str.substring(i, i+3).toLowerCase().equals("one")) return 1;
                    break;
                case "t":
                    if (i+3 > str.length() && i+5 > str.length()) break;
                    else if (i+3 <= str.length()) if (str.substring(i, i+3).toLowerCase().equals("two")) return 2;
                    else if (i+5 <= str.length()) if (str.substring(i, i+5).toLowerCase().equals("three")) return 3;
                    break;
                case "f":
                    if (i+4 > str.length()) break;
                    else if (str.substring(i, i+4).toLowerCase().equals("four")) return 4;
                    else if (str.substring(i, i+4).toLowerCase().equals("five")) return 5;
                    break;
                case "s":
                    if (i+3 > str.length() && i+5 > str.length()) break;
                    else if (i+3 <= str.length()) if (str.substring(i, i+3).toLowerCase().equals("six")) return 6;
                    else if (i+5 <= str.length()) if (str.substring(i, i+5).toLowerCase().equals("seven")) return 7;
                    break;
                case "e":
                    if (i+5 > str.length()) break;
                    else if (str.substring(i, i+5).toLowerCase().equals("eight")) return 8;
                    break;
                case "n":
                    if (i+4 > str.length()) break;
                    else if (str.substring(i, i+4).toLowerCase().equals("nine")) return 9;
                    break;
                default:
                    break;
            }
        }
        return -1;
    }

    /**
     * index 0-1 search param fit
     * @param str
     * @return -1 if no digit found
     */
    public static int findLetterDigit(String str) {
        if (str.equals("")) return -1;
        switch (str.substring(0, 1).toLowerCase()) {
            case "z":
                if (4 > str.length()) break;
                else if (str.substring(0, 4).toLowerCase().equals("zero")) return 0;
                break;
            case "o":
                if (3 > str.length()) break;
                else if (str.substring(0, 3).toLowerCase().equals("one")) return 1;
                break;
            case "t":
                if (3 > str.length() && 5 > str.length()) break;
                else if (3 <= str.length()) if (str.substring(0, 3).toLowerCase().equals("two")) return 2;
                else if (5 <= str.length()) if (str.substring(0, 5).toLowerCase().equals("three")) return 3;
                break;
            case "f":
                if (4 > str.length()) break;
                else if (str.substring(0, 4).toLowerCase().equals("four")) return 4;
                else if (str.substring(0, 4).toLowerCase().equals("five")) return 5;
                break;
            case "s":
                if (3 > str.length() && 5 > str.length()) break;
                else if (3 <= str.length()) if (str.substring(0, 3).toLowerCase().equals("six")) return 6;
                else if (5 <= str.length()) if (str.substring(0, 5).toLowerCase().equals("seven")) return 7;
                break;
            case "e":
                if (5 > str.length()) break;
                else if (str.substring(0, 5).toLowerCase().equals("eight")) return 8;
                break;
            case "n":
                if (4 > str.length()) break;
                else if (str.substring(0, 4).toLowerCase().equals("nine")) return 9;
                break;
            default:
                break;
        }
        return -1;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}