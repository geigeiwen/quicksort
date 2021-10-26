package seqmethods;

import seqmethods.ParMap;

public class ParMapFlags extends ParMap {

    int v;

    public ParMapFlags(int[] arr) {
        super(arr);
    }

    @Override
    Integer f(int[] arr, int i) {
        return  (arr[i]==v) ? 1 : 0;
    }



    public ParMapFlags(int[] arr, int v) {
        super(arr);
        this.v = v;
    }


}
