package trees;

import java.util.Stack;

public class BinBaum {
    protected Knoten wurzel;

    public BinBaum() {
        wurzel = null;
    }

    public Knoten suche(Integer gesucht) {
        return wurzel.suche(gesucht);
    }

    @Override
    public String toString() {
        if (wurzel == null) {
            return "Baum leer";
        } else {
            return wurzel.toString();
        }
    }

    public String toStringSortiert() {
        if (wurzel == null) {
            return "Baum leer";
        } else {
            return wurzel.toStringSortiert();
        }
    }

    public int tiefe() {
        return wurzel.tiefe();
    }

    public int anzahl() {
        return wurzel.anzahl();
    }

    public boolean ausgeglichen() {
        return wurzel.ausgeglichen();
    }

    public static BinBaum fromString(String input) {
        BinBaum baum = new BinBaum();
        String currentToken = "";
        Stack<Knoten> stapel = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            switch (c) {
                case '[': stapel.push(new Knoten(Integer.parseInt(currentToken))); currentToken = ""; break;
                case ']': break;
                case '<': break;
                case '>': break;
                default: currentToken += c;
            }
        }

        return baum;
    }
}
