import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Thrower thrower = new Thrower();

        catchIOException(thrower);

        readUserInputTryInside(thrower);

        readUserInputTryWithResources(thrower);

        readUserInputTryWithResourcesAndCustomException(thrower);

        createIOExceptionEvent(thrower);

    }

    private static void createIOExceptionEvent(Thrower thrower) {
        readUserInputTryWithResourcesAndCustomException(thrower);
        readUserInputTryWithResources(thrower);
    }

    private static void readUserInputTryWithResourcesAndCustomException(Thrower thrower) {
        try {
            System.out.println(thrower.readUserInputTryWithResourcesAndCustomException());
        } catch (CustomIOException e) {
            System.out.println(e.name);
            e.printStackTrace();
        }
    }

    private static void readUserInputTryWithResources(Thrower thrower) {
        try {
            System.out.println(thrower.readUserInputTryWithResources());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readUserInputTryInside(Thrower thrower) {
        System.out.println(thrower.readUserInputTryInside());
    }

    private static void catchIOException(Thrower thrower) {
        try {
            thrower.throwException();
        } catch (IOException e) {
            System.out.println("Caught IOException");
        }
    }
}
