package qsort;

import seqmethods.ParFilter;
import seqmethods.ParFor;

import java.util.concurrent.RecursiveTask;

public class ParQuickSort extends RecursiveTask<Integer> {
    private static final int MIN_TO_PARALLEL = 100;
    private final boolean useParFilter;
    private int leftInd;
    private int rightInd;
    private int[] arr;

    public ParQuickSort(int leftInd, int rightInd, int[] arr) {
        this.leftInd = leftInd;
        this.rightInd = rightInd;
        this.arr = arr;
        this.useParFilter = false;
    }

    public ParQuickSort(int leftInd, int rightInd, int[] arr, boolean useParFilter) {
        this.leftInd = leftInd;
        this.rightInd = rightInd;
        this.arr = arr;
        this.useParFilter = useParFilter;
    }

    @Override
    protected Integer compute() {
        if (leftInd >= rightInd) return null;


        if (rightInd - leftInd < MIN_TO_PARALLEL) {
            SeqQuickSort seqQuickSort = new SeqQuickSort(leftInd, rightInd, arr);
            seqQuickSort.sort();
            return null;
        }
        ParQuickSort leftSort, rightSort;

        if (useParFilter) {
            int v = arr[(leftInd + rightInd) / 2];
            int[] lessarr, eqarr, morearr;

            lessarr = new ParFilterLess(arr, v).compute();
            eqarr = new ParFilterEq(arr, v).compute();
            morearr = new ParFilterMore(arr, v).compute();


            leftSort = new ParQuickSort(0, lessarr.length - 1, lessarr, useParFilter);
            rightSort = new ParQuickSort(0, morearr.length - 1, morearr, useParFilter);
            leftSort.fork();
            rightSort.compute();
            leftSort.join();
            ParCopyTask copyTask = new ParCopyTask(lessarr, 0);
            copyTask.compute_inplace(arr);
            copyTask = new ParCopyTask(eqarr, lessarr.length);
            copyTask.compute_inplace(arr);
            copyTask = new ParCopyTask(morearr, lessarr.length + eqarr.length);
            copyTask.compute_inplace(arr);

        } else {
            int p = SeqQuickSort.seqPartition(leftInd, rightInd, arr);
            leftSort = new ParQuickSort(leftInd, p, arr, useParFilter);
            rightSort = new ParQuickSort(p + 1, rightInd, arr, useParFilter);
            leftSort.fork();
            rightSort.compute();
            leftSort.join();
        }
        return null;
    }

    public static class ParFilterLess extends ParFilter {
        public ParFilterLess(int[] arr, int v) {
            super(arr, v);
        }

        @Override
        public int filter_function(int[] arr, int i, int v) {
            return (arr[i] < v) ? 1 : 0;
        }
    }

    public static class ParFilterMore extends ParFilter {
        public ParFilterMore(int[] arr, int v) {
            super(arr, v);
        }

        @Override
        public int filter_function(int[] arr, int i, int v) {
            return (arr[i] > v) ? 1 : 0;
        }
    }

    public static class ParFilterEq extends ParFilter {
        public ParFilterEq(int[] arr, int v) {
            super(arr, v);
        }

        @Override
        public int filter_function(int[] arr, int i, int v) {
            return (arr[i] == v) ? 1 : 0;
        }
    }

    class ParCopyTask extends ParFor {
        int skipn;

        public ParCopyTask(int[] arr, int skipn) {
            super(arr);
            this.skipn = skipn;
        }


        @Override
        public Integer f(int[] arr, int i, int[] res) {
            res[i + skipn] = arr[i];
            return null;
        }
    }
}
