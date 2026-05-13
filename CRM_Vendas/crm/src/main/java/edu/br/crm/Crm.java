package edu.br.crm;
import edu.br.crm.view.ConsoleView;
import java.io.UnsupportedEncodingException;

public class Crm {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        ConsoleView i = new ConsoleView();
        i.iniciar();
    }
}
