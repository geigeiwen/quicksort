package seqmethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParScan {
    int[] arr;
    int[] sums;
    int[] res;


    private class UpTask extends RecursiveTask<Integer> {
        int[] arr;
        int[] sums;
        int left;
        int right;
        int i;


        @Override
        protected Integer compute() {
            if (left == right) {
                return arr[left];
            }
            int m = (left + right) / 2;
            UpTask leftTask = new UpTask(arr, sums, left, m, 2*i + 1);
            UpTask rightTask = new UpTask(arr, sums, m + 1, right, 2*i + 2);

            leftTask.fork();
            int rres = rightTask.compute();
            int lres = leftTask.join();
            sums[i] = rres + lres;
            return  (sums[i]);
        }

        public UpTask(int[] arr, int[] sums, int left, int right, int i) {
            this.arr = arr;
            this.sums = sums;
            this.left = left;
            this.right = right;
            this.i = i;
        }
    }

    private class DownTask extends RecursiveTask<Integer> {
        int[] arr;
        int[] sums;
        int[] res;
        int left;
        int right;
        int i;
        int left_sum;

        public DownTask(int[] arr, int[] sums, int[] res, int left, int right, int i, int left_sum) {
            this.arr = arr;
            this.sums = sums;
            this.res = res;
            this.left = left;
            this.right = right;
            this.i = i;
            this.left_sum = left_sum;
        }

        @Override
        protected Integer compute() {
            if (left == right) {
                res[left] = arr[left] + left_sum;
                return null;
            }
//            System.out.println(left + " " + right + " " + i);
            int m = (left + right) / 2;
            DownTask leftTask = new DownTask(arr, sums, res, left, m, 2*i + 1, left_sum);
            int newsum = left_sum;
            if (left + 1 == right) {
                newsum += arr[left];
            } else  {
                newsum += sums[2*i + 1];
            }
            DownTask rightTask = new DownTask(arr, sums, res, m + 1, right, 2*i + 2, newsum);
            leftTask.fork();
            rightTask.compute();
            leftTask.join();
            return null;
        }
    }


    public ParScan(int[] arr) {
        this.arr = arr;
    }

    public int[] compute() {
        sums = new int[(arr.length)* 2];
        ForkJoinPool pool = ForkJoinPool.commonPool();
        UpTask upTask = new UpTask(arr,  sums, 0, arr.length - 1, 0);
        pool.invoke(upTask);
        res = new int[arr.length];
        DownTask downTask = new DownTask(arr, sums, res, 0, arr.length - 1, 0, 0);
        pool.invoke(downTask);
        return res;
    }
}
