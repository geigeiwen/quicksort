import qsort.ParQuickSort;
import qsort.SeqQuickSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final int ARRAY_SIZE = 100000000; //100000000;
    private static final int CNT_ATTEMPTS = 5; // 5
    private static Integer MAX_ELEM = null;

    private static final boolean USE_PAR_FILTER = false; //false


    private static Random random = new Random();

    private static int[] genArray() {
        int[] res = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            if (MAX_ELEM == null) {
                res[i] = random.nextInt();
            } else {
                res[i] = random.nextInt(MAX_ELEM);

            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<Long> par_res = new ArrayList<>();
        ArrayList<Long> seq_res = new ArrayList<>();

        for (int i = 0; i < CNT_ATTEMPTS; i++) {
            int[] array = genArray();
            int[] seqarray = Arrays.copyOf(array, ARRAY_SIZE);
            int[] pararray = Arrays.copyOf(array, ARRAY_SIZE);


            long startTime = System.nanoTime();

            ForkJoinPool pool = ForkJoinPool.commonPool();
            ParQuickSort parQuickSort = new ParQuickSort(0, ARRAY_SIZE - 1, pararray, USE_PAR_FILTER);
            pool.invoke(parQuickSort);
            long parTime = System.nanoTime() - startTime;
//            System.out.println(parTime);

            startTime = System.nanoTime();
            SeqQuickSort seqQuickSort = new SeqQuickSort(0, ARRAY_SIZE - 1, seqarray);
            seqQuickSort.sort();
            long seqTime = System.nanoTime() - startTime;
//            System.out.println(seqTime);

            seq_res.add(seqTime);
            par_res.add(parTime);

            System.out.println("par time #" + (i + 1) + ": " + parTime);
            System.out.println("seq time #" + (i + 1) + ": " + seqTime);
            System.out.println("======");

            if (ARRAY_SIZE < 100) {

                System.out.println(Arrays.toString(array));
                System.out.println(Arrays.toString(pararray));
                System.out.println(Arrays.toString(seqarray));
                Arrays.sort(array);
                System.out.println(Arrays.toString(array));
            }


        }
//        System.out.println(Arrays.toString(par_res.toArray()));
//        System.out.println(Arrays.toString(seq_res.toArray()));
        Double seqmean = seq_res.stream().mapToLong(val -> val).average().orElse(0.0);
        Double parmean = par_res.stream().mapToLong(val -> val).average().orElse(0.0);
        System.out.println("Seq res mean: " + seqmean);
        System.out.println("Par res mean: " + parmean);
        System.out.println("Is better in " + seqmean / parmean);


    }
}
