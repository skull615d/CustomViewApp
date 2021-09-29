import parser.ParserBufferedReader;
import parser.ParserScanner;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        String suffering = "страдани";

        File file = new File("voyna.txt");

        ParserBufferedReader parserBR = new ParserBufferedReader();
        ArrayList<String> listWithBR = parserBR.parseFile(file);

        ParserScanner parserScanner = new ParserScanner();
        ArrayList<String> listWithScanner = parserScanner.parseFile(file);

        System.out.println(repetitions(listWithBR, suffering));
        System.out.println(repetitions(listWithScanner, suffering));

        multiplyingTableRecursive(10, 10);

    }

    private static int repetitions(ArrayList<String> textArray, String substring) {
        int result = 0;
        for (String s : textArray) {
            String[] words = s.split(" ");
            for (String word : words) {
                if (word.toLowerCase(Locale.ROOT).contains(substring.toLowerCase(Locale.ROOT)))
                    result++;
            }
        }
        return result;
    }

    private static void multiplyingTableRecursive(int rows, int multiplyUpTo) {
        if (rows > 0) {
            multiplyingTableRecursive(rows - 1, multiplyUpTo);
            for (int i = 1; i <= multiplyUpTo; i++) {
                System.out.print(rows * i + " ");
            }
            System.out.println();
        }
    }
}
