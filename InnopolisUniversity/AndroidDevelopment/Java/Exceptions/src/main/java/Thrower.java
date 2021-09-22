import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Thrower {

    public void throwException() throws IOException {
        throw new IOException();
    }

    private void throwCustomException() throws CustomIOException {
        throw new CustomIOException();
    }

    public String readUserInputTryInside() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Caught IOException");
            return null;
        }
    }

    public String readUserInputTryWithResources() throws IOException {
        try (BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)))) {
            return reader.readLine();
        }
    }

    public String readUserInputTryWithResourcesAndCustomException() throws CustomIOException {
        try (BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)))) {
            return reader.readLine();
        } catch (IOException e) {
            throwCustomException();
            return null;
        }
    }
}
