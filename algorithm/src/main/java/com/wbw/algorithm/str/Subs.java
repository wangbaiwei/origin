package com.wbw.algorithm.str;

import org.junit.Test;

import java.util.*;

public class Subs {


    @Test
    public void test() {
        System.out.println(subs("abc"));
    }


    public List<String> subs(String s) {
        char[] chars = s.toCharArray();
        List<String> ans = new ArrayList<>();
        String path = "";
        process(chars, 0, ans, path);
        return ans;

    }

    private void process(char[] chars, int i, List<String> ans, String path) {
        if (i == chars.length) {
            ans.add(path);
            return;
        }
        process(chars, i + 1, ans, path);
        process(chars, i + 1, ans, path + chars[i]);
    }


    public List<String> permutation(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        char[] chars = s.toCharArray();
        List<Character> reset = new ArrayList<>();
        for (char c : chars) {
            reset.add(c);
        }
        String path = "";
        f(reset, ans, path);
        return ans;
    }

    private void f(List<Character> reset, List<String> ans, String path) {
        if (reset.isEmpty()) {
            ans.add(path);
        } else {
            for (int i = 0; i < reset.size(); i++) {
                char cur = reset.get(i);
                reset.remove(i);
                f(reset, ans, path + cur);
                reset.add(i, cur);
            }
        }
    }

    @Test
    public void testPermutation() {
        System.out.println(permutation("aba"));
        System.out.println(permutation2("aba"));
    }

    public List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }

        char[] chars = s.toCharArray();
        g(chars, 0, ans);
        return ans;
    }

    private void g(char[] chars, int index, List<String> ans) {
        if (index == chars.length) {
            ans.add(String.valueOf(chars));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < chars.length; i++) {
                if (!visited[chars[i]]) {
                    visited[chars[i]] = true;
                    swap(chars, i, index);
                    g(chars, index + 1, ans);
                    swap(chars, i, index);
                }
            }
        }
    }

    public void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }


    @Test
    public void testReverse() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
//        reverse(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int cur = f(stack);
        reverse(stack);
        stack.push(cur);
    }

    public int f(Stack<Integer> stack) {
        int before = stack.pop();
        if (stack.isEmpty()) {
            return before;
        } else {
            int after = f(stack);
            stack.push(before);
            return after;
        }
    }

    public int process(int start, int aim, int rest, int N) {
        if (rest == 0) {
            return start == aim ? 1 : 0;
        }
        if (start == 1) {
            return process(2, aim, rest - 1, N);
        }
        if (start == N) {
            return process(N - 1, aim, rest - 1, N);
        }
        return process(start - 1, aim, rest - 1, N) + process(start + 1, aim, rest - 1, N);
    }

    public int process2(int start, int aim, int rest, int N, int[][] dp) {
        if (dp[start][rest] != -1) {
            return dp[start][rest];
        }
        int ans;
        // 剩余一个元素，若是到目标位置，步数为1，否则为0
        if (rest == 0) {
            ans = start == aim ? 1 : 0;
        } else if (start == 1) { // 开始位置为1，只能向右走
            ans = process2(2, aim, rest - 1, N, dp);
        } else if (start == N) { // 开始位置为N，只能向左走
            ans = process2(N - 1, aim, rest - 1, N, dp);
        } else { // 可以向左走也可以向右走
            ans = process2(start - 1, aim, rest - 1, N, dp) + process2(start + 1, aim, rest - 1, N, dp);
        }
        dp[start][rest] = ans;
        return ans;
    }

    @Test
    public void testStep() {
        int N = 4, rest = 4;
        System.out.println(process(2, 4, 4, N));
        int[][] dp = new int[N + 1][rest + 1];
        Arrays.stream(dp).forEach(e -> Arrays.fill(e, -1));
        System.out.println(process2(2, 4, rest, N, dp));
        int i = process2(2, 4, rest, N);
        System.out.println(i);
    }

    /**
     * @param start 开始位置
     * @param aim   目标位置
     * @param rest  剩余移动次数
     * @param N     总共位置
     * @return
     */
    public int process2(int start, int aim, int rest, int N) {
        int[][] dp = new int[N + 1][rest + 1];
        // 第1列第aim行值为1
        dp[aim][0] = 1;
        for (int col = 1; col <= rest; col++) {
            dp[1][col] = dp[2][col - 1];
            for (int row = 2; row < N; row++) {
                dp[row][col] = dp[row - 1][col - 1] + dp[row + 1][col - 1];
            }
            dp[N][col] = dp[N - 1][col - 1];
        }
        return dp[start][rest];
    }

    @Test
    public void testWin() {
        System.out.println(win(new int[]{1, 100, 1}));
        System.out.println(win2(new int[]{1, 100, 1}));
    }

    public int win(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 先手
        int m1 = f(arr, 0, arr.length - 1);
        // 后手
        int m2 = g(arr, 0, arr.length - 1);
        return Math.max(m1, m2);
    }

    public int f(int[] arr, int l, int r) {
        // 先手，若有一张牌，是先手的
        if (l == r) {
            return arr[l];
        }
        // 先手拿左侧的拍然后作为后手所能获得的最好分数
        int m1 = arr[l] + g(arr, l + 1, r);
        int m2 = arr[r] + g(arr, l, r - 1);
        return Math.max(m1, m2);
    }

    public int g(int[] arr, int l, int r) {
        // 作为后手，只有一张牌，则是先手的
        if (l == r) {
            return 0;
        }
        int m1 = f(arr, l + 1, r);
        int m2 = f(arr, l, r - 1);
        return Math.min(m1, m2);
    }


    public int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] fmap = new int[N][N];
        int[][] gmap = new int[N][N];
        for (int i = 0; i < N; i++) {
            fmap[i][i] = arr[i];
        }

        for (int startCol = 1; startCol < N; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < N) {

                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                L++;
                R++;
            }
        }
        return Math.max(fmap[0][N - 1], gmap[0][N - 1]);
    }

    public int f2(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        if (fmap[l][r] != -1) {
            return fmap[l][r];
        }

        int ans = 0;
        if (l == r) { // 先手，若有一张牌，是先手的
            ans = arr[l];
        } else {
            // 先手拿左侧的拍然后作为后手所能获得的最好分数
            int m1 = arr[l] + g2(arr, l + 1, r, fmap, gmap);
            int m2 = arr[r] + g2(arr, l, r - 1, fmap, gmap);
            ans = Math.max(m1, m2);
        }
        fmap[l][r] = ans;
        return ans;
    }

    public int g2(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        if (gmap[l][r] != -1) {
            return gmap[l][r];
        }
        int ans = 0;
        // 作为后手，只有一张牌，则是先手的
        if (l != r) {
            int m1 = f2(arr, l + 1, r, fmap, gmap);
            int m2 = f2(arr, l, r - 1, fmap, gmap);
            ans = Math.min(m1, m2);
        }
        gmap[l][r] = ans;
        return ans;
    }

    /**
     * @param w     货物的重量
     * @param p     货物的价值
     * @param index 当前货物
     * @param bag   背包容量
     * @return
     */
    public int process(int[] w, int[] p, int index, int bag) {
        if (bag < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }

        // 不要当前的货物
        int p1 = process(w, p, index + 1, bag);
        // 要当前的货物
        int p2 = 0;
        int next = process(w, p, index + 1, bag - w[index]);
        // 背包容量不为负数
        if (next != -1) {
            p2 = p[index] + next;
        }
        return Math.max(p1, p2);
    }

    public int process2(int[] w, int[] p, int index, int rest) {
        int N = w.length;
        int[][] dp = new int[N + 1][rest + 1];
        for (int row = N - 1; row >= 0; row--) {
            for (int col = 0; col <= rest; col++) {
                int p1 = dp[row + 1][col];
                int next = col - w[row] < 0 ? -1 : dp[row + 1][col - w[row]];
                int p2 = 0;
                if (next != -1) {
                    p2 = p[row] + next;
                }
                dp[row][col] = Math.max(p1, p2);
            }
        }
        return dp[0][rest];
    }


    public int convert(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        return process3(chars, 0);
    }

    private int process3(char[] chars, int i) {
        if (i == chars.length) { // 若到右侧边界，返回一种可能
            return 1;
        }
        if (chars[i] == '0') { // 之前决策有问题：比如"10"
            return 0;
        }
        int ways = process3(chars, i + 1); // 单转
        if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') <= 26) { // 两个字符一起转
            ways += process3(chars, i + 2);
        }
        return ways;
    }


    private int dp(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int N = chars.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            if (chars[i] != '0') {
                int ways = dp[i + 1]; // 单转
                if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') <= 26) { // 两个字符一起转
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }

    public int minerSticker(String[] stickers, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String sticker : stickers) {
            String rest = minus(target, sticker);
            if (rest.length() != target.length()) {
                min = Math.min(min, minerSticker(stickers, rest));
            }
        }
        return min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min + 1;
    }

    public String minus(String s1, String s2) {
        char[] chrs1 = s1.toCharArray();
        char[] chrs2 = s2.toCharArray();
        int[] count = new int[26];
        for (char c : chrs1) {
            count[c - 'a']++;
        }
        for (char c : chrs2) {
            count[c - 'a']--;
        }

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < count[i]; j++) {
                str.append((char) (i + 'a'));
            }
        }
        return str.toString();
    }

    public int minStickers(String[] stickers, String target) {
        if (target == null || target.length() == 0) {
            return 0;
        }

        int N = stickers.length;
        // 统计每个贴纸中，每个字符出现的次数
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            for (char c : stickers[i].toCharArray()) {
                counts[i][c - 'a']++;
            }
        }
        Map<String, Integer> dp = new HashMap<>();
        int ans = process(counts, target, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }


    public int process(int[][] stickers, String target, Map<String, Integer> dp) {
        // 长度校验
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        int[] count = new int[26];
        // 统计target中每个字符出现的次数
        char[] targets = target.toCharArray();
        for (char c : targets) {
            count[c - 'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) { // 遍历每个贴纸
            int[] sticker = stickers[i];
            if (sticker[targets[0] - 'a'] > 0) { // 当前贴纸包含target的首个字符
                StringBuilder reset = new StringBuilder(); // 统计target中剩余的字符
                for (int j = 0; j < 26; j++) { // tartget中每个字符出现的个数减去当前贴纸中每个字符出现的次数
                    if (count[j] > 0) { // target当前字符词频大于0
                        int num = count[j] - sticker[j]; // target当前词频减一贴纸当前字符词频
                        for (int k = 0; k < num; k++) { // 统计target剩余词频
                            reset.append((char) (j + 'a'));
                        }
                    }
                }
                min = Math.min(min, process(stickers, reset.toString(), dp));
            }
        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, ans);
        return ans;
    }


    @Test
    public void testP() {
//
//        System.out.println(minerSticker(new String[]{"notice", "possible"}, "basicbasic"));
//        System.out.println(minStickers(new String[]{"notice", "possible"}, "basicbasic"));


        String str = "aaaabbccd";

        Map<Character, Integer> map = new HashMap<>();
        int start = 0, cur = 0;
        while (cur < str.length()) {
            while (cur < str.length() && str.charAt(cur) == str.charAt(start)) {
                cur++;
            }
            map.put(str.charAt(start), cur - start);
            start = cur;
        }
        System.out.println(map);
    }


    @Test
    public void testCheckStraightLine() {

//        int[][] coordinates = {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}};
//        int[][] coordinates = {{1,1},{2,2},{3,4},{4,5},{5,6},{7,7}};
//        int[][] coordinates = {{2, 1}, {4, 2}, {6, 3}};
        int[][] coordinates = {{0, 1}, {1, 3}, {-4, -7}, {5, 11}};
        System.out.println(checkStraightLine(coordinates));

    }


    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates == null) {
            return false;
        }
        if (coordinates.length == 1) {
            return true;
        }
        Arrays.sort(coordinates, (a, b) -> a[0] - b[0]);

        int N = coordinates.length;
        int x = coordinates[0][0];
        int y = coordinates[0][1];
        if (x == 0) {
            for (int start = 2; start < N; start++) {
                if (coordinates[start][0] != 0) {
                    return false;
                }
            }
        } else if (y == 0) {
            for (int start = 2; start < N; start++) {
                if (coordinates[start][1] != 0) {
                    return false;
                }
            }
        } else {

            double p = ((coordinates[1][1] - y) * 1.0 / (coordinates[1][0] - x));
            int start = 2;
            while (start < N) {
                if (((coordinates[start][1] - y) * 1.0 / (coordinates[start][0] - x)) != p) {
                    return false;
                }
                start++;
            }
        }
        return true;
    }









}