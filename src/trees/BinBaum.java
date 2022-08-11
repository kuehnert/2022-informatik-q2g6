package trees;

public class BinBaum {
    Knoten<Integer> wurzel;

    public BinBaum() {
        // Top-down
        wurzel = new Knoten<Integer>(5);
        wurzel.links = new Knoten<>(8, new Knoten<>(18), new Knoten<>(3));
        wurzel.rechts = new Knoten<>(6);
        wurzel.rechts.links = new Knoten<>(36);
        wurzel.rechts.rechts = new Knoten<>(4, null, new Knoten<>(12));

        System.out.println(wurzel.gibAus());;

        // Bottom-Up
        Knoten t8 = new Knoten<>(8, new Knoten<>(18), new Knoten<>(3));
        Knoten t6 = null;
        wurzel = new Knoten<>(5, t8, t6);
    }

    public static void main(String[] args) {
        new BinBaum();
    }
}

class Knoten<T> {
    public Knoten<T> links;
    public Knoten<T> rechts;
    public T data;

    public Knoten(T data) {
        this.data = data;
    }

    public Knoten(T data, Knoten<T> links, Knoten<T> rechts) {
        this.data = data;
        this.links = links;
        this.rechts = rechts;
    }

    public String gibAus() {
        // 1. Gib die aktuelle Zahl aus
        String output = data.toString();

        // 2. Wenn links ein Knoten ist, gib ihn aus
        if (links != null) {
            output += links.gibAus();
        }

        // 2. Wenn rechts ein Knoten ist, gib ihn aus
        if (rechts != null) {
            output += rechts.gibAus();
        }

        return output;
    }
}