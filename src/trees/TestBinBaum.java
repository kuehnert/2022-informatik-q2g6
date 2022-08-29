package trees;

public class TestBinBaum {
    /*
        Hausis: ❤😍
        1. Schreiben Sie eine Methode anzahl(), welche die
           Anzahl von Knoten in einem Baum zählt.
        2. Eine Methode, welche die x-Position eines beliebigen
           Knotens bestimmt
     */
    public static void main(String[] args) {
        BinSuchBaum bsb = new BinSuchBaum("5 9 32 2 6 8 7 4");

        System.out.println(bsb.toStringSortiert());
        System.out.println(bsb.tiefe());
        System.out.println(bsb.anzahl());

        BinSuchBaum bsb2 = new BinSuchBaum("5");
        System.out.println(bsb2.anzahl());

        String rStr = ArrayHelper.randomString();
        System.out.println(rStr);
        BinSuchBaum bsb3 = new BinSuchBaum(rStr);
        System.out.println(bsb3);
        System.out.println(bsb3.anzahl());
    }
}
