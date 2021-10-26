package seqmethods;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public abstract class ParFor {
    public int[] arr;
    public int[] res;
    public int[] sums;
    public int reslen;

    public abstract Integer f(int[] arr, int i, int[] res);


    private class ForTask  extends RecursiveTask {
        int[] arr;
        int[] res;
        int[] sums;
        int left;
        int right;


        public ForTask(int[] arr, int[] res, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
            this.res = res;
            this.sums = sums;
        }
        @Override
        protected Integer compute() {
            if (left == right) {
                f(arr, left, res);
                return null;
            }
            int m = (left + right) / 2;
            ForTask leftTask = new ForTask(arr, res,  left, m);
            ForTask rightTask = new ForTask(arr, res,m + 1, right);

            leftTask.fork();
            rightTask.compute();
            leftTask.join();
            return null;
        }
    }


    public ParFor(int[] arr) {
        this.arr = arr;
        this.reslen = arr.length;
    }

    public ParFor(int[] arr, int[] sums) {
        this.arr = arr;
        this.sums = sums;
        this.reslen = sums[sums.length - 1];
    }

    public int[] compute() {
        res = new int[this.reslen];
        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForTask forTask = new ForTask(arr,  res,  0, arr.length - 1);
        pool.invoke(forTask);
        return res;
    }

    public  int[] compute_inplace (int[] res) {
        this.res = res;
        ForkJoinPool pool = ForkJoinPool.commonPool();
        ForTask forTask = new ForTask(arr,  res,  0, arr.length - 1);
        pool.invoke(forTask);
        return res;
    }




}
