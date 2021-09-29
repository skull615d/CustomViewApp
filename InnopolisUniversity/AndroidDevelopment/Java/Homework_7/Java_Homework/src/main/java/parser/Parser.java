package parser;

import java.io.File;
import java.util.ArrayList;

public interface Parser {
    ArrayList<String> parseFile(File file);
}