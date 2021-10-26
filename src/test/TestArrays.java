package test;

import java.util.Random;

public class TestArrays {
    public static int[] getRandomArray() {
        Random random = new Random();
        int[] arr_rand = new int[20];
        for (int i = 0; i<arr_rand.length; i++) {
            arr_rand[i] =  random.nextInt(10);
        }
        return arr_rand;
    }

    public static int[] getSeqArray() {
        int[] seq_arr = new int[10];
        for (int i = 0; i<seq_arr.length; i++) {
            seq_arr[i] = i;
        }
        return seq_arr;
    }

    public static int[] getConstArray(int v){
        int[] const_arr = new int[10];
        for (int i = 0; i<const_arr.length; i++) {
            const_arr[i] = v;
        }
        return const_arr;
    }
}
