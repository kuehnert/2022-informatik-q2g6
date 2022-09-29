package trees;

public class MorseEncoder {
    private static final String[] CODES = {".-", "-...", "-.-.", "-..", ".",
            "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--",
            "-..-", "-.--", "--.."};

    private String clean(String in) {
        var out = new StringBuilder();
        var chars = in.toUpperCase().toCharArray();
        for (char c : chars) {
            if (c >= 'A' && c <= 'Z') {
                out.append(c);
            }
        }

        return out.toString();
    }

    private int ord(char c) {
        return c - 'A';
    }

    public String ordString(String in) {
        in = clean(in);
        String out = "";

        for (int i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            out += ord(c) + "/";
        }

        return out;
    }

    private String encodeChar(char c) {
        // wandle c in das Ordinal um ('A' -> 0, 'B' -> 1, ...)
        // schlage im Array nach, welcher Code korrespondiert und gib
        // ihn zurück
        return CODES[ord(c)] + '/';
    }

    public String encode(String input) {
        input = clean(input);
        String out = "";

        for (int i = 0; i < input.length(); i++) {
            // füge jedes verschl. Zeichen an
            char c = input.charAt(i);
            out += encodeChar(c);
        }

        return out;
    }

    public static void main(String[] args) {
        var m = new MorseEncoder();
        String input = "Hurra, wir leben!";
        String output = m.encode(input);
        System.out.printf("%s -> %s -> %s -> %s%n", input, m.clean(input),
                m.ordString(input), output);
    }
}
