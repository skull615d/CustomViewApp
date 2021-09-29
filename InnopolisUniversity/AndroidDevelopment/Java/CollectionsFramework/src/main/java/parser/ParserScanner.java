package parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParserScanner implements Parser {

    @Override
    public ArrayList<String> parseFile(File file) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            String string = scanner.nextLine();
            while (scanner.hasNextLine()) {
                stringArrayList.add(string);
                string = scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringArrayList;
    }
}
