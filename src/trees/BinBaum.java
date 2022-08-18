package trees;

public class BinBaum {
    Knoten wurzel;

    public BinBaum() {
        // Top-down
        // wurzel = ne w Knoten<Integer>(5);
        // wurzel.links = new Knoten(8, new Knoten(18), new Knoten(3));
        // wurzel.rechts = new Knoten(6);
        // wurzel.rechts.links = new Knoten(36);
        // wurzel.rechts.rechts = new Knoten(4, null, new Knoten(12));

        // Bottom-Up
        Knoten t8 = new Knoten(8, new Knoten(18), new Knoten(3));
        Knoten t4 = new Knoten(4, null, new Knoten(12));
        Knoten t6 = new Knoten(6, new Knoten(36), t4);
        wurzel = new Knoten(5, t8, t6);
    }

    public Knoten suche(Integer gesucht) {
        return wurzel.suche(gesucht);
    }

    public static void main(String[] args) {
        BinBaum bb = new BinBaum();
        System.out.println(bb.suche(5)); //? => Knoten(5)
        System.out.println(bb.suche(8)); //? => Knoten(5)
        System.out.println(bb.suche(4)); //? => Knoten(5)
        System.out.println(bb.suche(6)); //? => Knoten(5)
        System.out.println(bb.suche(3)); //? => Knoten(5)
        System.out.println(bb.suche(12)); //? => Knoten(5)
        System.out.println(bb.suche(36)); //? => Knoten(36)
        System.out.println(bb.suche(0)); //? => null
        System.out.println(bb.suche(37)); //? => null
        System.out.println(bb.suche(13)); //? => null
        System.out.println(bb.suche(9)); //? => null
    }
}
