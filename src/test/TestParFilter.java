package test;

import qsort.ParQuickSort;
import seqmethods.ParFilter;

import java.util.Arrays;

public class TestParFilter {



    public static void main(String[] args) {
        ParFilter parFilter;
        System.out.println("======");
        System.out.println("test1");
        System.out.println("======");
        int[] const_arr = TestArrays.getConstArray(1);
        System.out.println(Arrays.toString(const_arr));
        parFilter = new ParQuickSort.ParFilterEq(const_arr, 1);
        int[] res3 =  parFilter.compute();
        System.out.println(Arrays.toString(res3));

        System.out.println("======");
        System.out.println("test2");
        System.out.println("======");
        int[] const_arr2 = TestArrays.getConstArray(1);
        System.out.println(Arrays.toString(const_arr2));
        parFilter = new ParQuickSort.ParFilterEq(const_arr2, 0);
        int[] res4 =  parFilter.compute();
        System.out.println(Arrays.toString(res4));

        System.out.println("======");
        System.out.println("test3");
        System.out.println("======");
        int[] seq_arr = TestArrays.getSeqArray();
        System.out.println(Arrays.toString(seq_arr));
        parFilter = new ParQuickSort.ParFilterLess(seq_arr, 5);
        int[] res2 = parFilter.compute();
        System.out.println(Arrays.toString(res2));

        System.out.println("======");
        System.out.println("test4");
        System.out.println("======");
        int[] seq_arr2 = TestArrays.getSeqArray();
        System.out.println(Arrays.toString(seq_arr2));
        parFilter = new ParQuickSort.ParFilterEq(seq_arr2, 4);
        int[] res5 = parFilter.compute();
        System.out.println(Arrays.toString(res5));

        System.out.println("======");
        System.out.println("test5");
        System.out.println("======");
        int[] arr_rand = TestArrays.getRandomArray();
        System.out.println(Arrays.toString(arr_rand));
        parFilter = new ParQuickSort.ParFilterMore(arr_rand, 3);
        int[] res1 =  parFilter.compute();
        System.out.println(Arrays.toString(res1));
    }

}
