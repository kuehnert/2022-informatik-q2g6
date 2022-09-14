package test;

import trees.ArrayHelper;
import trees.BinSuchBaum;

public class TestBinBaum {
    public static void test1() {
        BinSuchBaum bsb = new BinSuchBaum("5 9 32 2 6 8 7 4");
        System.out.println(bsb.toStringSortiert());
        System.out.println(bsb.tiefe());
        System.out.println(bsb.anzahl());

        System.out.println(bsb.suche(5));
        System.out.println(bsb.suche(9));
        System.out.println(bsb.suche(4));
        System.out.println(bsb.suche(13));

    }

    public static void test2() {
        BinSuchBaum bsb2 = new BinSuchBaum("5");
        System.out.println(bsb2.anzahl());
    }

    public static void test3() {
        String rStr = ArrayHelper.randomString();
        System.out.println(rStr);
        BinSuchBaum bsb3 = new BinSuchBaum(rStr);
        System.out.println(bsb3);
        System.out.println(bsb3.anzahl());
    }

    public static void main(String[] args) {
        test4();
    }

    public static void test4() {
        // Baum entspricht [3,9,20,null,null,15,7]
        BinSuchBaum bsb = new BinSuchBaum("10 5 15 12 18");
        System.out.println(bsb.ausgeglichen());

        // Baum entspricht [1,2,2,3,3,null,null,4,4]
        bsb = new BinSuchBaum("10 5 15 3 7 1 4");
        System.out.println(bsb.ausgeglichen());
    }
}
