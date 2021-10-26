package seqmethods;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public abstract class ParMap {
    int[] arr;
    int[] res;

    abstract Integer f(int[] arr, int i);

    class MapTask  extends RecursiveTask<Integer> {
        int[] arr;
        int[] res;
        int left;
        int right;
        public MapTask(int[] arr,  int[] res, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
            this.res = res;
        }

        @Override
        protected Integer compute() {
            if (left == right) {
                res[left] = f(arr, left);
                return null;
            }
            int m = (left + right) / 2;
            MapTask leftTask = new MapTask(arr, res, left, m);
            MapTask rightTask = new MapTask(arr, res, m + 1, right);

            leftTask.fork();
            rightTask.compute();
            leftTask.join();
            return null;
        }
    }


    public ParMap(int[] arr) {
        this.arr = arr;
    }

    public int[] compute() {
        res = new int[(arr.length)];
        ForkJoinPool pool = ForkJoinPool.commonPool();
        MapTask mapTask = new MapTask(arr,  res, 0, arr.length - 1);
        pool.invoke(mapTask);
        return res;
    }
}
