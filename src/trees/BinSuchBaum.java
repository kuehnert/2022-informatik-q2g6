package trees;

public class BinSuchBaum {
    Knoten wurzel;

    public void einfuegen(Integer data) {
        Knoten newKnoten = new Knoten(data);
        Knoten runner = wurzel;

        while (true) {
            // Sind die Daten größer als runner.data?
            // Fehler, weil Datentyp "T" kein ">" kann!
            if (runner.data > data) {

            }
        }

    }

    public static void main(String[] args) {
        new BinSuchBaum();
    }
}
