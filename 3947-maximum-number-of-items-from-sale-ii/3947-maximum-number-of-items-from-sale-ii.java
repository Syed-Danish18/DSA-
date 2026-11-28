class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];

        int minPrice = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            freq[items[i][0]]++;
            minPrice = Math.min(minPrice, items[i][1]);
        }

        // Count how many item types can be obtained for free
        int[] count = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                count[i] += freq[j];
            }
        }

        // Sort according to price
        java.util.Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price = items[i][1];

            // Buying this item must be worthwhile
            if ((long) price > 2L * minPrice) {
                break;
            }

            // Exclude the item itself
            int freeItems = count[factor] - 1;

            // Maximum useful purchases
            int take = Math.min(freeItems, budget / price);

            budget -= take * price;
            ans += 2L * take;
        }

        // Spend remaining budget on cheapest item
        ans += budget / minPrice;

        return (int) ans;
    }
}