import java.math.BigDecimal;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Chars
        System.out.println("<--------------Chars-------------->");
        ArrayList<Character> chars = new ArrayList<>();
        chars.add(0, 'H');
        chars.add(1, 'e');
        chars.add(2, 'l');
        chars.add(3, 'l');
        chars.add(4, 'o');
        chars.add(5, ' ');
        chars.add(6, 'W');
        chars.add(7, 'o');
        chars.add(8, 'r');
        chars.add(9, 'l');
        chars.add(10, 'd');

        chars.forEach(System.out::print);
        System.out.print(" from ArrayList");
        System.out.println();

        char[] chars1 = {
                'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd'
        };
        System.out.println(String.valueOf(chars1) + "from char[]");
        System.out.println();

        // Strings
        System.out.println("<--------------Strings-------------->");
        String hello = "Hello";
        String world = "World";

        // String Concatenation
        String helloWorld = hello + " " + world;
        System.out.println(helloWorld);

        // String Buffer (Dynamically changing Strings)
        StringBuffer stringBuffer = new StringBuffer(helloWorld)
                .replace(6, 11, "Java from stringBuffer");
        System.out.println(stringBuffer);

        // String Builder
        StringBuilder stringBuilder = new StringBuilder(helloWorld)
                .replace(6, 11, "Java from stringBuilder");
        System.out.println(stringBuilder);

        // Type Conversion (Int -> String)
        int number = 10;
        System.out.println(helloWorld + " " + number);
        System.out.println();

        // Integers
        System.out.println("<--------------Integers-------------->");
        int i = 0;
        int j = 1;
        System.out.println("i = " + i + ", j = " + j);
        i = ++j;
        System.out.println("i = " + i + ", j = " + j);

        int i1 = 0;
        int j1 = 1;
        System.out.println("i1 = " + i1 + ", j1 = " + j1);
        i1 = j1++;
        System.out.println("i1 = " + i1 + ", j1 = " + j1);
        System.out.println();


        // Give me POWER Floating Point!!
        // Comparing two Double
        System.out.println("<--------------Doubles-------------->");
        double a = 1.00001;
        double b = 1.000009999999999; // That's OK
//        double b = 1.000010000000000000000000000000001; // That's NOT OK

        BigDecimal bigDecimalA = new BigDecimal(a);
        BigDecimal bigDecimalB = new BigDecimal(b);

        if (bigDecimalA.compareTo(bigDecimalB) == 0) {
            System.out.println("a equals b");
        } else if (bigDecimalA.compareTo(bigDecimalB) < 0) {
            System.out.println("a less than b");
        } else {
            System.out.println("a grater than b");
        }
    }
}
