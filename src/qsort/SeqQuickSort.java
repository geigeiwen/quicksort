package qsort;

public class SeqQuickSort {
    private int leftInd;
    private int rightInd;
    private int[] arr;

    private static void swap(int[] a, int i, int j) {
        int tmp = a[j];
        a[j] = a[i];
        a[i] = tmp;
    }

    public static int seqPartition(int left, int right,
                                   int[] a) {
        int v = a[(left + right) / 2];
        int i = left;
        int j = right;
        while (i <= j) {
            while (a[i] < v) {
                i++;
            }
            while (a[j] > v) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(a, i, j);

            j--;
            i++;
        }

        return j;
    }

    public SeqQuickSort(int leftInd, int rightInd, int[] arr) {
        this.leftInd = leftInd;
        this.rightInd = rightInd;
        this.arr = arr;
    }


    public Integer sort() {
        if (leftInd >= rightInd) return null;
        int p = seqPartition(leftInd, rightInd, arr);
        //        Pair<Integer, Integer> p3 = QuickSortMethods.partition3(leftInd, rightInd, arr);
        SeqQuickSort leftSort = new SeqQuickSort(leftInd, p , arr); //l .. p
        SeqQuickSort rightSort = new SeqQuickSort(p + 1, rightInd, arr); //p + 1 .. r
        leftSort.sort();
        rightSort.sort();
        return null;
    }

}
