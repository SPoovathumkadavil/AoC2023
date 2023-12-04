import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Answer Should Be:-85010461
 * Current Answer:---85008877
 */
public class DiaTres {

    public static void main(String[] args) {

        File encryptedFile = new File("DiaTresSupplement.txt");
        File testCase = new File("DiaTresTestCase.txt");
        ArrayList<String> fileStrings = new ArrayList<>();

        try {

            Scanner fileScanner = new Scanner(encryptedFile);

            while (fileScanner.hasNextLine()) {
                fileStrings.add(fileScanner.nextLine());
            }

            fileScanner.close();

        } catch (Exception e) {
            System.err.println("Uh Oh: " + e.getMessage());
            e.printStackTrace();
        }

        ArrayList<Integer> viableDigits = pt2GearRatios(fileStrings);
        int sum = 0;
        for (int i : viableDigits)
            sum += i;
        System.out.println(sum);

    }

    public static ArrayList<Integer> pt1GetAllViableCodes(ArrayList<String> strings) {

        ArrayList<Integer> viableDigits = new ArrayList<>();

        for (int i = 0; i < strings.size(); i++) {
            String middle = strings.get(i);
            boolean currentDigitIsViable = false;
            String currentDigit = "";
            for (int j = 0; j < middle.length(); j++) {
                char middleCenter = middle.charAt(j);

                if (!Character.isDigit(middleCenter)) {
                    if (currentDigitIsViable) // The finished digit is viable
                        viableDigits.add(Integer.parseInt(currentDigit));

                    currentDigit = "";
                    currentDigitIsViable = false;
                    continue;
                } else {
                    currentDigit += middleCenter;
                }

                String top = strings.get(Math.max(i - 1, 0));
                String bottom = strings.get(Math.min(i + 1, strings.size() - 1));
                char topLeft = top.charAt(Math.max(j - 1, 0));
                char topCenter = top.charAt(j);
                char topRight = top.charAt(Math.min(j + 1, top.length() - 1));
                char middleLeft = middle.charAt(Math.max(j - 1, 0));
                char middleRight = middle.charAt(Math.min(j + 1, middle.length() - 1));
                char bottomLeft = bottom.charAt(Math.max(j - 1, 0));
                char bottomCenter = bottom.charAt(j);
                char bottomRight = bottom.charAt(Math.min(j + 1, bottom.length() - 1));

                if (!Character.isDigit(topLeft) && topLeft != '.' ||
                        !Character.isDigit(topCenter) && topCenter != '.' ||
                        !Character.isDigit(topRight) && topRight != '.' ||
                        !Character.isDigit(middleLeft) && middleLeft != '.' ||
                        !Character.isDigit(middleCenter) && middleCenter != '.' ||
                        !Character.isDigit(middleRight) && middleRight != '.' ||
                        !Character.isDigit(bottomLeft) && bottomLeft != '.' ||
                        !Character.isDigit(bottomCenter) && bottomCenter != '.' ||
                        !Character.isDigit(bottomRight) && bottomRight != '.') {
                    currentDigitIsViable = true;
                }

                if (j == middle.length() - 1 && currentDigitIsViable) {
                    viableDigits.add(Integer.parseInt(currentDigit));
                }

                System.out.println(top + " " + topLeft + " " + middleCenter + " " + currentDigitIsViable);

            }

        }

        return viableDigits;

    }

    public static ArrayList<Integer> pt2GearRatios(ArrayList<String> strings) {

        ArrayList<Integer> gearRatios = new ArrayList<>();

        for (int i = 0; i < strings.size(); i++) {
            String middle = strings.get(i);
            for (int j = 0; j < middle.length(); j++) {
                char middleCenter = middle.charAt(j);

                if (middleCenter != '*')
                    continue;

                String top = strings.get(Math.max(i - 1, 0));
                String bottom = strings.get(Math.min(i + 1, strings.size() - 1));

                char topLeft = top.charAt(Math.max(j - 1, 0));
                char topCenter = top.charAt(j);
                char topRight = top.charAt(Math.min(j + 1, top.length() - 1));
                char middleLeft = middle.charAt(Math.max(j - 1, 0));
                char middleRight = middle.charAt(Math.min(j + 1, middle.length() - 1));
                char bottomLeft = bottom.charAt(Math.max(j - 1, 0));
                char bottomCenter = bottom.charAt(j);
                char bottomRight = bottom.charAt(Math.min(j + 1, bottom.length() - 1));

                int numCodeAttached = 0;
                int codeOne = -1;
                int codeTwo = -1;

                /*
                 * Positions:
                 * 1, 2, 3
                 * 4, 5, 6
                 * 7, 8, 9
                 */

                /* Positions 4 and 6 */
                if (Character.isDigit(middleLeft)) {
                    numCodeAttached++;
                    int num = getWholeNumber(strings, i, Math.max(j - 1, 0));
                    if (codeOne == -1)
                        codeOne = num;
                    else
                        codeTwo = num;
                }

                if (Character.isDigit(middleRight)) {
                    numCodeAttached++;
                    int num = getWholeNumber(strings, i, Math.min(j + 1, middle.length() - 1));
                    if (codeOne == -1)
                        codeOne = num;
                    else
                        codeTwo = num;
                }

                /* Positions 1, 2, 3 */
                /* Check 2 before 1 and 3 to check if they should connect */

                boolean isTopCenterDigit = false;
                boolean isTopCenterAddressed = false;
                if (Character.isDigit(topCenter)) {
                    isTopCenterDigit = true;
                }

                if (Character.isDigit(topLeft)) {
                    int num = getWholeNumber(strings, Math.max(i - 1, 0), Math.max(j - 1, 0));
                    if (isTopCenterDigit) {
                        /* Belongs to the left */
                        if (!isTopCenterAddressed) {
                            numCodeAttached++;
                            if (codeOne == -1)
                                codeOne = num;
                            else
                                codeTwo = num;
                            isTopCenterAddressed = true;
                        }
                    } else {
                        /* Center Not Present */
                        numCodeAttached++;
                        if (codeOne == -1)
                            codeOne = num;
                        else
                            codeTwo = num;
                    }
                }

                if (Character.isDigit(topRight)) {
                    int num = getWholeNumber(strings, Math.max(i - 1, 0), Math.min(j + 1, top.length() - 1));
                    if (isTopCenterDigit) {
                        /* Belongs to the left */
                        if (!isTopCenterAddressed) {
                            numCodeAttached++;
                            if (codeOne == -1)
                                codeOne = num;
                            else
                                codeTwo = num;
                            isTopCenterAddressed = true;
                        }
                    } else {
                        /* Center Not Present */
                        numCodeAttached++;
                        if (codeOne == -1)
                            codeOne = num;
                        else
                            codeTwo = num;
                    }
                }

                /* Positions 7, 8, 9 */
                /* Check 2 before 1 and 3 to check if they should connect */

                boolean isBottomCenterDigit = false;
                boolean isBottomCenterAddressed = false;
                if (Character.isDigit(bottomCenter)) {
                    isBottomCenterDigit = true;
                }

                if (Character.isDigit(bottomLeft)) {
                    int num = getWholeNumber(strings, Math.min(i + 1, strings.size() - 1), Math.max(j - 1, 0));
                    if (isBottomCenterDigit) {
                        /* Belongs to the left */
                        if (!isBottomCenterAddressed) {
                            numCodeAttached++;
                            if (codeOne == -1)
                                codeOne = num;
                            else
                                codeTwo = num;
                            isBottomCenterAddressed = true;
                        }
                    } else {
                        /* Center Not Present */
                        numCodeAttached++;
                        if (codeOne == -1)
                            codeOne = num;
                        else
                            codeTwo = num;
                    }
                }

                if (Character.isDigit(bottomRight)) {
                    int num = getWholeNumber(strings, Math.min(i + 1, strings.size() - 1),
                            Math.min(j + 1, bottom.length() - 1));
                    if (isBottomCenterDigit) {
                        /* Belongs to the left */
                        if (!isBottomCenterAddressed) {
                            numCodeAttached++;
                            if (codeOne == -1)
                                codeOne = num;
                            else
                                codeTwo = num;
                            isBottomCenterAddressed = true;
                        }
                    } else {
                        /* Center Not Present */
                        numCodeAttached++;
                        if (codeOne == -1)
                            codeOne = num;
                        else
                            codeTwo = num;
                    }
                }

                if (numCodeAttached != 2)
                    continue;
                else
                    gearRatios.add(codeOne * codeTwo);

            }

        }

        return gearRatios;
    }

    /**
     * @param strings all strings in file
     * @param i       of seen number
     * @param j       of seen number
     * @return the whole integer
     */
    public static int getWholeNumber(ArrayList<String> strings, int i, int j) {

        String wholeEnc = strings.get(i);
        char middleChar = wholeEnc.charAt(j); // Will be sandwhiched
        String tmpLeft = ""; // needs to be reversed before add
        String right = "";

        for (int tj = j - 1; tj >= 0; tj--) {
            if (Character.isDigit(wholeEnc.charAt(tj)))
                tmpLeft += wholeEnc.charAt(tj);
            else
                break;
        }

        for (int tj = j + 1; tj <= wholeEnc.length() - 1; tj++) {
            if (Character.isDigit(wholeEnc.charAt(tj)))
                right += wholeEnc.charAt(tj);
            else
                break;
        }

        String left = "";
        for (int a = tmpLeft.length() - 1; a >= 0; a--) {
            left += tmpLeft.charAt(a);
        }

        System.out.println(left + " " + middleChar + " " + right);
        return Integer.parseInt(left + middleChar + right);

    }

}