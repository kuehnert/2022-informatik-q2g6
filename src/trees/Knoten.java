package trees;

class Knoten {
    public Knoten links;
    public Knoten rechts;
    public int data;

    public Knoten(int data) {
        this.data = data;
    }

    public Knoten(int data, Knoten links, Knoten rechts) {
        this.data = data;
        this.links = links;
        this.rechts = rechts;
    }

    public int tiefe() {
        // Bestimme Tiefe vom linken TB, oder -1 wenn nicht existent
        int tLinks = (links == null) ? -1 : links.tiefe();

        // Bestimme Tiefe vom rechten TB, oder -1 wenn nicht existent
        int tRechts = (rechts == null) ? -1 : rechts.tiefe();

        // Gib die größere Zahl + 1 zurück
        return Math.max(tLinks, tRechts) + 1;
    }

    public Knoten suche(int gesucht) {
        // Wenn der Knoten der gesuchte ist, gibt er sich selbst zurück
        if (data == gesucht) {
            return this;
        }

        if (links != null) {
            // Wenn nicht, dann frag linken TB, wenn es ihn gibt, nach dem gesuchten Knoten
            Knoten ergebnis = links.suche(gesucht);
            // Wenn linker TB einen Knoten zurückgibt, gib diesen Knoten zurück
            if (ergebnis != null) {
                return ergebnis;
            }
        }

        if (rechts != null) {
            // Wenn nicht, dann frag rechten TB nach dem gesuchten Knoten
            // Wenn rechter TB einen Knoten zurückgibt, gib diesen Knoten zurück
            return rechts.suche(gesucht);
        }

        // Sonst null
        return null;
    }

    // NLR-Ausgabe
    public String toString() {
        // 1. Gib die aktuelle Zahl aus
        String output = "" + data;

        // 2. Wenn links ein Knoten ist, gib ihn aus
        if (links != null) {
            output += "[" + links.toString() + "]";
        }

        // 2. Wenn rechts ein Knoten ist, gib ihn aus
        if (rechts != null) {
            output += "<" + rechts.toString() + ">";
        }

        return output;
    }

    // LNR-Ausgabe
    public String toStringSortiert() {
        String output = "";

        // 1. Wenn links ein Knoten ist, gib ihn aus
        if (links != null) {
            output += links.toStringSortiert();
        }

        // 2. Gib die aktuelle Zahl des Knotens aus
        output += data + " ";


        // 3. Wenn rechts ein Knoten ist, gib ihn aus
        if (rechts != null) {
            output += rechts.toStringSortiert();
        }

        return output;
    }
}
