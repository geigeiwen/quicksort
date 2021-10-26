package seqmethods;

import seqmethods.ParFor;
import seqmethods.ParMap;
import seqmethods.ParScan;

public abstract class ParFilter {

    int[] arr;
    int[] res;
    int[] flags;
    int[] sums;
    int v;
    public abstract int filter_function(int[] arr, int i, int v);

    public ParFilter(int[] arr, int v) {
        this.arr = arr;
        this.v  = v;
    }

    public class ForTask extends ParFor {

        public ForTask(int[] arr, int[] sums) {
            super(arr, sums);
        }

        @Override
        public Integer f(int[] arr, int i, int[] res) {
            if (i == 0) {
                if (sums[i] != 0) {
                    res[sums[i] - 1] = arr[i];
                }
            } else {
                if (sums[i]> sums[i-1]) {
                    res[sums[i] - 1] = arr[i];
                }
            }
            return null;

        }
    }

    public class MapTask extends ParMap {
        public MapTask(int[] arr) {
            super(arr);
        }
        @Override
        Integer f(int[] arr, int i) {
            return filter_function(arr, i, v);
        }
    }

    public int[] compute() {
        //map
        MapTask mapTask = new MapTask(arr);
        this.flags = mapTask.compute();
        //scan
        ParScan parScan = new ParScan(flags);
        this.sums = parScan.compute();

        //pfor
        ForTask forTask = new ForTask(arr, sums);
        this.res = forTask.compute();

        return res;
    }

}
