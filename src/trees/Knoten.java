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

    public Knoten suche(int gesucht) {
        // Wenn der Knoten der gesuchte ist, gibt er sich selbst zurück
        if (data == gesucht) {
            return this;
        }

        // Wenn nicht, dann frag linken TB nach dem gesuchten Knoten
        // Wenn linker TB einen Knoten zurückgibt, gib diesen Knoten zurück

        // Wenn nicht, dann frag rechten TB nach dem gesuchten Knoten
        // Wenn rechter TB einen Knoten zurückgibt, gib diesen Knoten zurück

        // Sonst null
        return null;
    }

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
}
