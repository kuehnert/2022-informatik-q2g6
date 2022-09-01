package trees;

public class BeispielBaum extends BinBaum {
    public BeispielBaum() {
        Knoten t8 = new Knoten(8, new Knoten(18), new Knoten(3));
        Knoten t4 = new Knoten(4, null, new Knoten(12));
        Knoten t6 = new Knoten(6, new Knoten(36), t4);
        wurzel = new Knoten(5, t8, t6);
    }

    public static void main(String[] args) {
        BinBaum bb = new BeispielBaum();
        System.out.println(bb.tiefe());
        System.out.println(bb.anzahl());
        System.out.println(bb);
    }
}

