class Solution {
    int[] ans;
    int[] newParent;
    ArrayList<Integer>[] adj;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        newParent = parent.clone();
        ans = new int[n];

        // Build original tree
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        // Store nearest ancestor for each character
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // First DFS: modify parents
        dfs1(0, -1, s, lastSeen);

        // Build modified tree
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            adj[newParent[i]].add(i);
        }

        // Second DFS: calculate subtree sizes
        dfs2(0);

        return ans;
    }

    void dfs1(int node, int par, String s, int[] lastSeen) {

        int ch = s.charAt(node) - 'a';

        int old = lastSeen[ch];

        if (old != -1) {
            newParent[node] = old;
        }

        lastSeen[ch] = node;

        for (int child : adj[node]) {
            dfs1(child, node, s, lastSeen);
        }

        lastSeen[ch] = old;
    }

    int dfs2(int node) {

        int size = 1;

        for (int child : adj[node]) {
            size += dfs2(child);
        }

        ans[node] = size;

        return size;
    }
}
