import java.util.*;

class Solution {

    class MyMap {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int size = 0;
        long sum = 0;

        void add(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            size++;
            sum += val;
        }

        void remove(int val) {
            int count = map.get(val);

            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }

            size--;
            sum -= val;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        List<Long> costs = getMinOps(nums, x);

        int n = nums.length;
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE / 2);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {

            for (int j = 0; j <= k; j++) {

                dp[i][j] = dp[i - 1][j];

                if (j > 0 && i >= x) {

                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + costs.get(i - x)
                    );
                }
            }
        }

        return dp[n][k];
    }

    private List<Long> getMinOps(int[] nums, int x) {

        List<Long> costs = new ArrayList<>();

        MyMap lower = new MyMap();
        MyMap upper = new MyMap();

        for (int i = 0; i < nums.length; i++) {

            int val = nums[i];

            if (lower.size == 0 || val <= lower.map.lastKey()) {
                lower.add(val);
            } else {
                upper.add(val);
            }

            // Balance the two halves
            if (lower.size > upper.size + 1) {

                int median = lower.map.lastKey();

                lower.remove(median);
                upper.add(median);

            } else if (lower.size < upper.size) {

                int median = upper.map.firstKey();

                upper.remove(median);
                lower.add(median);
            }

            // Remove the element outside the sliding window
            if (i >= x) {

                val = nums[i - x];

                if (lower.map.containsKey(val)) {
                    lower.remove(val);
                } else {
                    upper.remove(val);
                }

                // Rebalance
                if (lower.size > upper.size + 1) {

                    int median = lower.map.lastKey();

                    lower.remove(median);
                    upper.add(median);

                } else if (lower.size < upper.size) {

                    int median = upper.map.firstKey();

                    upper.remove(median);
                    lower.add(median);
                }
            }

            // Calculate window cost
            if (i >= x - 1) {

                long median = lower.map.lastKey();

                long leftCost =
                    median * lower.size - lower.sum;

                long rightCost =
                    upper.sum - median * upper.size;

                costs.add(leftCost + rightCost);
            }
        }

        return costs;
    }
}