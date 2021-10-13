import java.io.IOException;

// У IOException жавы есть message, можно name у этого класса туда пропихнуть
// https://docs.oracle.com/javase/7/docs/api/java/io/IOException.html
public class CustomIOException extends IOException {

    public String name = "Custom IO Exception";

}
