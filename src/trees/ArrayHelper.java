package trees;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class ArrayHelper {
    public static String randomString() {
        Random r = new Random();
        int[] a = new int[100];

        // insert numbers from 1 to 100
        for (int i = 0; i < a.length; i++) {
            a[i] = i + 1;
        }

        // shuffle: 100 times swap two elements
        for (int i = 0; i < 100; i++) {
            swap(a, r.nextInt(a.length), r.nextInt(a.length));
        }

        // convert int array to string with space " " as separator
        return Arrays.stream(a).mapToObj(String::valueOf).collect(Collectors.joining(" "));
    }

    public static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
