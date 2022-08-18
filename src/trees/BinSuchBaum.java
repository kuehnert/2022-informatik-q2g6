package trees;

public class BinSuchBaum {
    Knoten wurzel;

    public void einfuegenLuis(int data) {
        Knoten newKnoten = new Knoten(data);
        Knoten parent = null;
        Knoten runner = wurzel;

        while (runner != null) {
            parent = runner;

            if (data == runner.data) {
                throw new IllegalArgumentException("Duplicate");
            }

            // Sind die Daten kleiner als runner.data?
            if (data < runner.data) {
                runner = runner.links;
            } else {
                runner = runner.rechts;
            }
        }

        if (parent == null) {
            wurzel = newKnoten;
        } else if (data < parent.data) {
            parent.links = newKnoten;
        } else {
            parent.rechts = newKnoten;
        }
    }

    public void einfuegenLuca(int data) {
        Knoten newKnoten = new Knoten(data);

        if (wurzel == null) {
            wurzel = newKnoten;
            return;
        }

        Knoten runner = wurzel;

        while (runner.links != null && data < runner.data || runner.rechts != null && data > runner.data) {
            // Sind die Daten kleiner als runner.data?
            if (data < runner.data) {
                runner = runner.links;
            } else {
                runner = runner.rechts;
            }
        }

        if (data < runner.data) {
            runner.links = newKnoten;
        } else {
            runner.rechts = newKnoten;
        }
    }

    @Override
    public String toString() {
        if (wurzel == null) {
            return "Baum leer";
        } else {
            return wurzel.toString();
        }
    }

    public static void main(String[] args) {
        BinSuchBaum bsb = new BinSuchBaum();
        bsb.einfuegenLuca(5);
        bsb.einfuegenLuca(9);
        bsb.einfuegenLuca(2);
        bsb.einfuegenLuca(32);
        bsb.einfuegenLuca(6);
        bsb.einfuegenLuca(8);
        bsb.einfuegenLuca(7);
        bsb.einfuegenLuca(4);
        System.out.println(bsb);
    }
}
