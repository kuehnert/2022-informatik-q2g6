package trees;

public class BinBaum {
    Knoten<Integer> wurzel;

    public BinBaum() {
        wurzel = new Knoten<Integer>(5);
        wurzel.links = new Knoten(8);
        wurzel.links.links = new Knoten<>(18);

        wurzel.rechts = new Knoten(6);
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
}