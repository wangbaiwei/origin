import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Demo3 {


    public int goodNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root, Integer.MIN_VALUE, 0);
    }

    public int process(TreeNode node, int max, int ans) {
        if (node == null) {
            return ans;
        }
        if (node.val >= max) {
            max = node.val;
            ans++;
        }
        int v1 = process(node.left, max, ans);
        int v2 = process(node.right, max, v1);
        return v2;
    }


    @Test
    public void test() {
        TreeNode head = new TreeNode(3);
        head.left = new TreeNode(1);
        head.right = new TreeNode(4);
        head.left.left = new TreeNode(3);
        head.right.left = new TreeNode(1);
        head.right.right = new TreeNode(5);
        goodNodes(head);

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    public int longestCommonSubsequence(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process(str1, str2, str1.length - 1, str2.length - 1);
    }

    public int process(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process(str1, str2, i - 1, j);
            }
        } else {
            int p1 = process(str1, str2, i, j - 1);
            int p2 = process(str1, str2, i - 1, j);
            int p3 = str1[i] == str2[j] ? 1 + process(str1, str2, i - 1, j - 1) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    public int longestCommonSubsequence2(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < m; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int p1 = dp[i][j - 1];
                int p2 = dp[i - 1][j];
                int p3 = str1[i] == str2[j] ? 1 + dp[i - 1][j - 1] : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[m - 1][n - 1];

    }


    @Test
    public void testA() {
        int a = 1;
        int b = 5;
        int c = 9;
        int res = a > b ? a > c ? a : c : b > c ? b : c;
    }


    @Test
    public void findPeakElement() {

        int[] nums = {1, 2, 3, 4};
        System.out.println(process(nums, 0, nums.length - 1));


    }

    public int process(int[] nums, int l, int r) {

        if (l > r) {
            return -1;
        }
        if (l == r || l == r - 1) {
            return nums[l] > nums[r] ? l : r;
        } else if (l == r - 2) {
            if (nums[l] > nums[l + 1] && nums[l] > nums[r]) {
                return l;
            } else if (nums[r] > nums[r - 1] && nums[r] > nums[l]) {
                return r;
            } else if (nums[r] == nums[l] && nums[l] > nums[l + 1]) {
                return l;
            } else {
                return l + 1;
            }
        } else {

            int p1 = process(nums, l, r - 1);
            int p2 = process(nums, l + 1, r);
            return nums[p1] > nums[p2] ? p1 : p2;
        }
    }

    public int findPeakElement(int[] nums) {
        int N = nums.length;
        int[][] dp = new int[N][N];
        int ans = 0;
        for (int i = 0; i < N; i++) {
            dp[i][i] = i;
            dp[i][i + 1] = nums[i] > nums[i + 1] ? i : i + 1;

            if (nums[i] > nums[i + 1] && nums[i] > nums[i + 2]) {
                ans = i;
            } else if (nums[i + 2] > nums[i + 2 - 1] && nums[i + 2] > nums[i]) {
                ans = i + 2;
            } else if (nums[i + 2] == nums[i] && nums[i] > nums[i + 1]) {
                ans = i;
            } else {
                ans = i + 1;
            }
            dp[i][i + 2] = ans;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 3; j < N; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
            }
        }
        return dp[0][N - 1];
    }

    public int process(int[] nums, int l, int r, int[][] dp) {
        if (dp[l][r] != -1) {
            return dp[l][r];
        }

        int ans = 0;
        if (l == r || l == r - 1) {
            ans = nums[l] > nums[r] ? l : r;
        } else if (l == r - 2) {
            if (nums[l] > nums[l + 1] && nums[l] > nums[r]) {
                ans = l;
            } else if (nums[r] > nums[r - 1] && nums[r] > nums[l]) {
                ans = r;
            } else if (nums[r] == nums[l] && nums[l] > nums[l + 1]) {
                ans = l;
            } else {
                ans = l + 1;
            }
        } else {

            int p1 = process(nums, l, r - 1, dp);
            int p2 = process(nums, l + 1, r, dp);
            ans = nums[p1] > nums[p2] ? p1 : p2;
        }
        dp[l][r] = ans;
        return ans;
    }


    @Test
    public void testSecondHighest() {
        System.out.println(secondHighest("vwkxfq9791769"));

    }

    public int secondHighest(String s) {

        String replace = s.replaceAll("[a-z]+", "");
        char[] c = replace.toCharArray();
        Arrays.sort(c);
        String $1 = String.valueOf(c).replaceAll("(.)\\1+", "$1");
        c = $1.toCharArray();
        if ("".equals($1) || $1.length() == 1) {
            return -1;
        }
        return c.length == 1 ? Character.digit(c[0], 10) : Character.digit(c[c.length - 2], 10);
    }


    public int process(int x, int y, int rest, int a, int b) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        if (rest == 0) {
            return x == a && y == b ? 1 : 0;
        }
        int ways = process(x + 2, y + 1, rest - 1, a, b);
        ways += process(x + 1, y + 2, rest - 1, a, b);
        ways += process(x - 1, y + 2, rest - 1, a, b);
        ways += process(x - 2, y + 1, rest - 1, a, b);
        ways += process(x - 2, y - 1, rest - 1, a, b);
        ways += process(x - 1, y - 2, rest - 1, a, b);
        ways += process(x + 1, y - 2, rest - 1, a, b);
        ways += process(x + 2, y - 1, rest - 1, a, b);
        return ways;
    }

    public int dp(int a, int b, int k) {
        int[][][] dp = new int[10][8][k + 1];
        dp[a][b][0] = 1;
        for (int rest = 1; rest < k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    dp[x][y][rest] = ways;
                }
            }
        }
        return dp[0][0][k];
    }

    public int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) {
            return 0;
        }
        return dp[x][y][rest];
    }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return null;
        }
        if (intervals.length == 1) {
            return intervals;
        }

        PriorityQueue<int[]> pQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int[] interval : intervals) {
            pQ.offer(interval);
        }

        List<int[]> ans = new ArrayList<>();
        int[] one = pQ.poll();
        while (!pQ.isEmpty()) {

            int[] another = pQ.poll();
            if (another[0] <= one[1]) {
                int end = Math.max(one[1], another[1]);
                one = new int[]{one[0], end};
            } else {
                ans.add(one);
                one = another;
            }
        }
        if (one != null) {
            ans.add(one);
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
