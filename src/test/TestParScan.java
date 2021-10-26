package test;

import seqmethods.ParScan;

import java.util.Arrays;

public class TestParScan {

    public static void main(String[] args) {
        ParScan parScan;

        int[] const_arr = TestArrays.getConstArray(1);
        System.out.println(Arrays.toString(const_arr));
        parScan = new ParScan(const_arr);
        int[] res3 = parScan.compute();
        System.out.println(Arrays.toString(res3));


        int[] seq_arr = TestArrays.getSeqArray();
        System.out.println(Arrays.toString(seq_arr));
        parScan = new ParScan(seq_arr);
        int[] res2 = parScan.compute();
        System.out.println(Arrays.toString(res2));


        int[] arr_rand = TestArrays.getRandomArray();
        System.out.println(Arrays.toString(arr_rand));
        parScan = new ParScan(arr_rand);
        int[] res1 = parScan.compute();
        System.out.println(Arrays.toString(res1));





    }
}
