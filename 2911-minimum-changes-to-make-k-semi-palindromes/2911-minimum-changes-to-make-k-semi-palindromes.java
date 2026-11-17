class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();

        int[][] cost = new int[n][n];

        // Calculate cost of every substring
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int len = j - i + 1;
                int minCost = n;

                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    for (int offset = 0; offset < d; offset++) {
                        int left = i + offset;
                        int right = j - d + 1 + offset;

                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minCost = Math.min(minCost, changes);
                }

                cost[i][j] = minCost;
            }
        }

        // DP
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], n);
        }

        dp[n][0] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int parts = 1; parts <= k; parts++) {

                for (int j = i + 1; j < n; j++) {

                    dp[i][parts] = Math.min(
                        dp[i][parts],
                        cost[i][j] + dp[j + 1][parts - 1]
                    );
                }
            }
        }

        return dp[0][k];
    }
}
