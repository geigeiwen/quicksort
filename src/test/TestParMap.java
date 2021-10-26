package test;

import seqmethods.ParMapFlags;

import java.util.Arrays;

public class TestParMap {
    public static void main(String[] args) {

        ParMapFlags parScan;

        int[] const_arr = TestArrays.getConstArray(1);
        System.out.println(Arrays.toString(const_arr));
        parScan = new ParMapFlags(const_arr, 1);
        System.out.println(Arrays.toString(parScan.compute()));
        int[] res3 =  parScan.compute();
        System.out.println(Arrays.toString(res3));


        int[] const_arr2 = TestArrays.getConstArray(1);
        System.out.println(Arrays.toString(const_arr2));
        parScan = new ParMapFlags(const_arr2, 0);
        int[] res4 =  parScan.compute();
        System.out.println(Arrays.toString(res4));


        int[] seq_arr = TestArrays.getSeqArray();
        System.out.println(Arrays.toString(seq_arr));
        parScan = new ParMapFlags(seq_arr, 1);
        int[] res2 = parScan.compute();
        System.out.println(Arrays.toString(res2));


        int[] arr_rand = TestArrays.getRandomArray();
        System.out.println(Arrays.toString(arr_rand));
        parScan = new ParMapFlags(arr_rand, 1);
        int[] res1 =  parScan.compute();
        System.out.println(Arrays.toString(res1));





    }
}
