import org.junit.Test;

import java.util.*;

public class Demo4 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        String line = in.nextLine();
        if (line == null && line.length() == 0) {
            System.out.println(0);
            return;
        }
        String[] split = line.trim().split(",");
        int row = Integer.parseInt(split[0].trim());
        int col = Integer.parseInt(split[1].trim());
        int startX = Integer.parseInt(split[2].trim());
        int startY = Integer.parseInt(split[3].trim());
        int endX = Integer.parseInt(split[4].trim());
        int endY = Integer.parseInt(split[5].trim());

        int[][] grid = new int[row][col];
        grid[startX][startY] = 1;
        grid[endX][endY] = 1;
        // 填充次数
        int times = 0;
        while (!isFinsh(grid)) {
            boolean fill = fill(grid);
            times++;
        }
        System.out.println(times);
    }

    public static boolean fill(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean falg = false;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] != 1) {
                    if (i + 1 < row && grid[i + 1][j] == 1) {
                        grid[i][j] = 1;
                        j++;
                        falg = true;
                    } else if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        grid[i][j] = 1;
                        j++;
                        falg = true;
                    } else if (j + 1 < col && grid[i][j + 1] == 1) {
                        grid[i][j] = 1;
                        j++;
                        falg = true;
                    } else if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        grid[i][j] = 1;
                        j++;
                        falg = true;
                    }
                }
            }
        }
        return falg;
    }

    public static boolean isFinsh(int[][] arr) {
        int res = 1;
        for (int[] ints : arr) {
            for (int anInt : ints) {
                res &= anInt;
            }
        }
        return res == 1;
    }

    /**
     * [-1,2,4,9,6]
     * 8
     *
     * @param args
     */
    public static void main1(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String arr = in.nextLine();
            int count = in.nextInt();
            arr = arr.substring(1, arr.length() - 1);

            String[] split = arr.split(",");

            List<Integer> input = new ArrayList<>();
            for (String s : split) {
                input.add(Integer.valueOf(s));
            }
            int len = input.size();
            int[] ans = new int[2];
            c:
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    if (input.get(i) + input.get(j) == count) {
                        ans[0] = input.get(i);
                        ans[1] = input.get(j);
                        break c;
                    }
                }
            }
            System.out.println(Arrays.toString(ans));
        }
    }

    @Test
    public void testMinTimes() {
    }

    public void minTimes(int[] arr, int n, int a, int b) {
        PriorityQueue<int[]> pQ = new PriorityQueue<>((aa, bb) -> (aa[0] + aa[1]) - (bb[0] + bb[1]));
        for (int wt : arr) {
            pQ.add(new int[]{0, wt});
        }

        int[] costTime = new int[n];
        for (int i = 0; i < n; i++) {
            int[] curTime_WKTime = pQ.poll();
            curTime_WKTime[0] += curTime_WKTime[1];
            costTime[i] = curTime_WKTime[1];
            pQ.offer(curTime_WKTime);
        }
        bestTime(costTime, a, b, 0, 0);
    }

    /**
     * @param drinks 所有杯子开始洗的时间
     * @param wash   洗干净有的时间
     * @param air    挥发干净用的时间
     * @param index  第index个杯子
     * @param free   洗杯机空闲的时间
     * @return 所有杯子干净的时间点
     */
    public int bestTime(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }
        // 洗干净
        int selfClear1 = Math.max(drinks[index], free) + wash;
        int restClear1 = bestTime(drinks, wash, air, index + 1, selfClear1);
        int p1 = Math.max(selfClear1, restClear1);
        // 挥发干净
        int selfClear2 = drinks[index] + air;
        int restClear2 = bestTime(drinks, wash, air, index + 1, free);
        int p2 = Math.max(selfClear2, restClear2);
        return Math.min(p1, p2);
    }

    public int bestTimeDP(int[] dirnks, int wash, int air) {
        int maxFree = Arrays.stream(dirnks).max().getAsInt() + wash;
        int N = dirnks.length;
        int[][] dp = new int[N + 1][maxFree + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int free = 0; free <= maxFree; free++) {
                // 洗干净
                int selfClear1 = Math.max(dirnks[index], free) + wash;
                if (selfClear1 > maxFree) { // 越界
                    continue;
                }
                int restClear1 = dp[index + 1][selfClear1];
                int p1 = Math.max(selfClear1, restClear1);
                // 挥发干净
                int selfClear2 = dirnks[index] + air;
                int restClear2 = dp[index + 1][free];
                int p2 = Math.max(selfClear2, restClear2);
                dp[index][free] = Math.min(p1, p2);
            }
        }
        return dp[0][0];
    }


    @Test
    public void test() {
        System.out.println(coinWays3(new int[]{1, 1, 1}, 2));
        System.out.println(coinWays3Dp(new int[]{1, 1, 1}, 2));
    }

    public int coinWays(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }

    public int coinWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int row = N - 1; row >= 0; row--) {
            for (int col = 0; col <= aim; col++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[row] <= col; zhang++) {
                    ways += process(arr, row + 1, col - (zhang * arr[zhang]));
                }
                dp[row][col] = ways;
            }
        }
        return dp[0][aim];
    }

    private int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - (zhang * arr[zhang]));
        }
        return ways;
    }


    public int coinWays3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        Map<Integer, Integer> record = new HashMap<>();
        for (int n : arr) {
            record.put(n, record.getOrDefault(n, 0) + 1);
        }
        int N = record.size();
        int[] coins = new int[N];
        int[] zhang = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : record.entrySet()) {
            coins[index] = entry.getKey();
            zhang[index++] = entry.getValue();
        }
        return process(coins, zhang, 0, aim);
    }

    /**
     * @param coins 面值
     * @param zhang 张数
     * @param index 当前面值
     * @param rest  剩余钱
     * @return
     */
    private int process(int[] coins, int[] zhang, int index, int rest) {
        if (index == coins.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        for (int z = 0; z < zhang[index] && z * coins[index] <= rest; z++) {
            ways += process(coins, zhang, index + 1, rest - z * coins[index]);
        }
        return ways;
    }

    public int coinWays3Dp(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        Map<Integer, Integer> record = new HashMap<>();
        for (int n : arr) {
            record.put(n, record.getOrDefault(n, 0) + 1);
        }
        int N = record.size();
        int[] coins = new int[N];
        int[] zhang = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : record.entrySet()) {
            coins[index] = entry.getKey();
            zhang[index++] = entry.getValue();
        }
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col <= aim; col++) {
                int ways = 0;
                for (int z = 0; z < zhang[row] && z * coins[row] <= col; z++) {
                    ways += dp[row + 1][col - z * coins[row]];
                }
                dp[row][col] = ways;
            }
        }
        return dp[0][aim];
    }

    @Test
    public void testLivePosibility() {
        System.out.println(livePosibility(0, 0, 1, 2, 2));
        System.out.println(livePosibilityDp(0, 0, 1, 2, 2));
    }

    public double livePosibility(int row, int col, int k, int N, int M) {
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    /**
     * @param row  当前所在行
     * @param col  当前所在列
     * @param rest 剩余步数
     * @param n    棋盘长度
     * @param m    棋盘宽度
     * @return
     */
    private int process(int row, int col, int rest, int n, int m) {
        if (row < 0 || row == n || rest < 0 || col < 0 || col == m) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        int up = process(row - 1, col, rest - 1, n, m);
        int down = process(row + 1, col, rest - 1, n, m);
        int left = process(row, col - 1, rest - 1, n, m);
        int right = process(row, col + 1, rest - 1, n, m);
        return up + down + left + right;
    }

    public double livePosibilityDp(int row, int col, int k, int N, int M) {
        int[][][] dp = new int[N][M][k + 1];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                dp[r][c][0] = 1;
            }
        }
        for (int h = 1; h <= k; h++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    dp[r][c][h] += pick(dp, r - 1, c, h - 1, N, M);
                    dp[r][c][h] += pick(dp, r + 1, c, h - 1, N, M);
                    dp[r][c][h] += pick(dp, r, c + 1, h - 1, N, M);
                    dp[r][c][h] += pick(dp, r, c - 1, h - 1, N, M);
                }
            }
        }
        return dp[row][col][k] / Math.pow(4, k);
    }

    public int pick(int[][][] dp, int row, int col, int h, int N, int M) {
        if (row < 0 || col < 0 || row == N || col == M) {
            return 0;
        }
        return dp[row][col][h];
    }


    @Test
    public void testLive() {
        System.out.println(live(1, 1, 1));
        System.out.println(liveDp(1, 1, 1));
    }

    /**
     * @param N 总血量
     * @param M 每次掉血量
     * @param K 剩余次数
     * @return
     */
    public double live(int N, int M, int K) {
        double all = Math.pow(M + 1, K);
        long kill = process1(N, M, K);
        return kill * 1.0 / all;
    }

    private long process1(int N, int M, int K) {
        if (K == 0) {
            return N <= 0 ? 1 : 0;
        }
        int ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process1(N - i, M, K - 1);
        }
        return ways;
    }

    /**
     * @param kill 可以砍的刀术
     * @param rest 剩余血量
     * @param lost 掉血量
     * @return
     */
    public double liveDp(int kill, int rest, int lost) {
        long all = (long) Math.pow(rest + 1, kill);
        long[][] dp = new long[kill + 1][rest + 1];
        dp[0][0] = 1;
        for (int row = 1; row <= kill; row++) {
//            dp[row][0] = (long) Math.pow(rest + 1, row);
            for (int col = 1; col <= rest; col++) {
                int ways = 0;
                for (int i = 0; i <= lost; i++) {
                    if (col - i >= 0) { // 剩余血量大于0
                        ways += dp[row - 1][col - i];
                    } /*else { // 剩余血量小于0
                        ways += (long) Math.pow(rest + 1, row);
                    }*/
                }
                dp[row][col] = ways;
            }
        }
        return (double) dp[kill][rest] / (double) all;
    }

    public int minCoins(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) {
            return 0;
        }
        return process2(arr, 0, aim);
    }

    private int process2(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            int next = process2(arr, index + 1, rest - zhang * arr[index]);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, zhang + next);
            }
        }
        return ans;
    }

    public int minCoinsDp(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int i = 1; i <= aim; i++) {
            dp[N][i] = Integer.MAX_VALUE;
        }

        for (int row = N - 1; row >= 0; row--) {
            for (int col = 0; col <= aim; col++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[row] <= col; zhang++) {
                    int next = dp[row + 1][col - zhang * arr[row]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, zhang + next);
                    }
                }
                dp[row][col] = ans;
            }
        }
        return dp[0][aim];
    }


    public int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return split(1, n);
    }

    public int split(int pre, int n) {
        if (n == 0) {
            return 1;
        }
        if (pre == n) {
            return 1;
        }
        if (pre > n) {
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= n; first++) {
            ways += split(first, n - first);
        }
        return ways;
    }

    public int waysDp(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int row = 0; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                int ways = 0;
                for (int first = row; first <= n; first++) {
                    ways += dp[first][n - first];
                }
                dp[row][col] = ways;
            }
        }
        return split(1, n);
    }

    public int right(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = Arrays.stream(arr).sum();
        return process4(arr, 0, sum / 2);
    }

    private int process4(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return 0;
        } else {
            int p1 = process4(arr, index + 1, rest);
            int p2 = 0;
            if (arr[index] <= rest) {
                p2 = arr[index] + process4(arr, index + 1, rest - arr[index]);
            }
            return Math.max(p1, p2);
        }
    }

    public int rightDp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = Arrays.stream(arr).sum();
        int N = arr.length;
        int M = sum / 2;
        int[][] dp = new int[N + 1][M + 1];
        for (int row = N - 1; row >= 0; row--) {
            for (int col = 0; col <= M; col++) {
                int p1 = dp[row + 1][col];
                int p2 = 0;
                if (arr[row] <= col) {
                    p2 = arr[row] + dp[row + 1][col - arr[row]];
                }
                dp[row][col] = Math.max(p1, p2);
            }
        }
        return dp[0][M];
    }

    @Test
    public void testRight() {
        System.out.println(right(new int[]{1, 2, 3}));
        System.out.println(rightDp(new int[]{1, 2, 3}));
    }
}
