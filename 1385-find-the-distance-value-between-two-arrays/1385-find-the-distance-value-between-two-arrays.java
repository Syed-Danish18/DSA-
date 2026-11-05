class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;

        outer:
        for (int k = 0; k < arr1.length; k++) {
            for (int i = 0; i < arr2.length; i++) {
                int diff = Math.abs(arr1[k] - arr2[i]);

                if (diff <= d) {
                    continue outer;
                }
            }
            count++;
        }

        return count;
    }
}