package trees;

public class MorseDecoder {
    private Node wurzel;

    public MorseDecoder() {
        wurzel = new Node(' ', new Node('E', new Node('I', new Node('S',
                new Node('H'), new Node('V')), new Node('U', new Node('F'),
                null)), new Node('A', new Node('R', new Node('L'), null),
                new Node('W', new Node('P'), new Node('J')))), new Node('T',
                new Node('N', new Node('D', new Node('B'), new Node('X')),
                        new Node('K', new Node('C'), new Node('Y'))),
                new Node('M', new Node('G', new Node('Z'), new Node('Q')),
                        new Node('O'))));

        System.out.println(wurzel);
    }

    public String decode(String in) {
        var runner = wurzel;
        var out = "";

        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);

            switch (c) {
                case '.':
                    runner = runner.left;
                    break;
                case '-':
                    runner = runner.right;
                    break;
                case '/':
                    out += runner.letter;
                    runner = wurzel;
                    break;
            }
        }

        return out;
    }

    public static void main(String[] args) {
        var md = new MorseDecoder();
        String botschaft = "./-/--.-/--.-/.-/../..../--.-/-.-/./-..././--/";
        String quelle = md.decode(botschaft);
        System.out.println(botschaft);
        System.out.println(quelle);
    }
}

class Node {
    Node left, right;
    char letter;

    public Node(char letter) {
        this.letter = letter;
    }

    public Node(char letter, Node left, Node right) {
        this.left = left;
        this.right = right;
        this.letter = letter;
    }

    public String toString() {
        // 1. Gib die aktuelle Zahl aus
        String output = "" + letter;

        // 2. Wenn links ein Knoten ist, gib ihn aus
        if (left != null) {
            output += "[" + left + "]";
        }

        // 2. Wenn rechts ein Knoten ist, gib ihn aus
        if (right != null) {
            output += "<" + right + ">";
        }

        return output;
    }
}