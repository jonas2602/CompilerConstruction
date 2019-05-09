import antlr.Interpreter;

public class App {

    public static void main(String[] args) {
        System.out.println("START");

        new Interpreter().ProcessExpression("3 + 4.5");
    }
}
