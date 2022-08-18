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

    public Knoten suche(Integer gesucht) {
        if (data == gesucht) {
            return this;
        }

        // ....
        return null;
    }

    public String gibAus() {
        // 1. Gib die aktuelle Zahl aus
        String output = "" + data;

        // 2. Wenn links ein Knoten ist, gib ihn aus
        if (links != null) {
            output += "[" + links.gibAus() + "]";
        }

        // 2. Wenn rechts ein Knoten ist, gib ihn aus
        if (rechts != null) {
            output += "<" + rechts.gibAus() + ">";
        }

        return output;
    }
}
