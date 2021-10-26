package qsort;

import javafx.util.Pair;

import java.util.Arrays;

public class QuickSortMethods {

    private static void swap(int[] a, int i, int j) {
        int tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    public static Pair<Integer, Integer> partition3(int left, int right,
                                                    int[] a) {
        int v = a[right];


        int i = left;
        int j = right - 1;
        int p = left - 1;
        int q = right;
        while (i <= j) {
            while (i < a.length && a[i] < v) {
                i++;
            }
            while (j > 0  && a[j] > v ) {
                j--;

            }
            if (i >= j) {
                break;
            }

            swap(a, i, j);

            if (a[i] == v) {
                p++;

                swap(a, p, i);
            }
            i++;
            if (a[j] == v) {
                q--;
                swap(a, q ,j);
            }
            j--;

        }
        swap(a, i, right);
        j = i - 1;
        i++;
        for (int k = left; k <=p; k++, j-- ) {
            swap(a, k, j);
        }
        for (int k = right - 1; k >=q; k--, i++ ) {
            swap(a, k, i);
        }
        return new Pair<>(j, i);
    }


}
