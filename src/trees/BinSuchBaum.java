package trees;

public class BinSuchBaum extends BinBaum {
    public BinSuchBaum() {
        wurzel = null;
    }

    public BinSuchBaum(String s) {
        einfuegen(s);
    }

    public void einfuegen(String s) {
        String[] zahlenStr = s.split(" ");
        for (String zahlStr: zahlenStr) {
            int zahl = Integer.parseInt(zahlStr);
            einfuegenLuca(zahl);
        }
    }

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
    public Knoten suche(Integer gesucht) {
        Knoten runner = wurzel;

        while (runner != null) {
            if (gesucht == runner.data) {
                return runner;
            } else if (gesucht < runner.data ) {
                runner = runner.links;
            } else {
                runner = runner.rechts;
            }
        }

        return null;
    }
}
