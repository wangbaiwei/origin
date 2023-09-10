import org.junit.Test;

import java.util.*;

public class Demo {

    @Test
    public void test() {
        int[] arr = {1, 2, 3, 3, 2, 1};
        Node head = generateNodeList(arr);
        System.out.println(judge(head));

    }

    public boolean judge(Node head) {
        Stack<Node> stack = new Stack<>();
        if (head == null) {
            return true;
        }
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        cur = head;
        while (!stack.isEmpty() && cur != null) {
            if (stack.pop().val != cur.val) {
                return false;
            }
            cur = cur.next;
        }
        return true;
    }


    public Node generateNodeList(int[] arr) {
        Node head = null;
        Node cur = head;
        for (int n : arr) {
            if (cur == null) {
                cur = new Node(n);
                head = cur;
            } else {
                cur.next = new Node(n);
                cur = cur.next;
            }
        }
        return head;
    }

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        boolean nStraightHand = isNStraightHand(arr, 3);
        System.out.println(nStraightHand);
    }

    public boolean isNStraightHand(int[] hand, int groupSize) {
        int len = hand.length;
        if (len % groupSize != 0) {
            return false;
        }


        Arrays.sort(hand);
        if (len == groupSize) {
            int start = hand[0];
            for (int i = 1; i < len; i++) {
                if (hand[i] != ++start) {
                    return false;
                }
            }
        }

        int l = 0;
        int r = l + groupSize - 1;
        int diff = hand[r] - hand[l];
        for (int i = 0; i < len; i++) {
            l += groupSize;
            r += groupSize;
            if (hand[r] - hand[l] != diff) {
                return false;
            }
        }

        return true;
    }


    public boolean lemonadeChange(int[] bills) {

        int len = bills.length;
        int[] residue = new int[len];

        for (int i = 0; i < len; i++) {
            if (i > 0) {
                residue[i] = residue[i - 1];
            }
            if (bills[i] - 5 > residue[i]) {
                return false;
            }
            residue[i] += bills[i] - 5;
        }
        return true;

    }

    @Test
    public void test3() {


    }


    public int[] maxSlidingWindow(int[] nums, int k) {

        int len = nums.length;
        List<Integer> list = new ArrayList<>();
        int index = 0;
        int[] ans = new int[len - k + 1];
        for (int i = 0; i < len; i++) {
            if (i > k) {
                list.remove(nums[i - k]);
            }
            if (i >= k - 1) {
                ans[index++] = list.stream().sorted().max((a, b) -> b - a).get();
            }
            list.add(nums[i]);

        }
        return ans;
    }
    public List<String> process(String[] arr) {
        List<String> ans = new ArrayList<>();
        if (arr.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < arr.length; i++) {
            String first = arr[i];
            String[] nexts = removeEleOfArr(arr, i);
            System.out.println("first=" + first + " nexts="
                    + Arrays.toString(nexts) + " ans="
                    + ans.stream().map(e -> String.valueOf(e)).reduce("", (a, b) -> a + "|" + b)
                    + " arr=" + Arrays.toString(arr));
            System.out.println("压栈=============================================");
            List<String> next = process(nexts);
            System.out.println("弹栈=============================================");
            for (String str : next) {
                ans.add(first + str);
            }

            System.out.println("first=" + first + " next="
                    + next.stream().map(e -> String.valueOf(e)).reduce("", (a, b) -> a + "|" + b) + " ans="
                    + ans.stream().map(e -> String.valueOf(e)).reduce("", (a, b) -> a + "|" + b)
                    + " arr=" + Arrays.toString(arr));
        }
        return ans;

    }

    @Test
    public void testa() {

        System.out.println(lowString2(new String[]{"ad", "ef", "adfate"}));
    }

    public String[] removeEleOfArr(String[] arr, int pos) {
        String[] ans = new String[arr.length - 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != pos) {
                ans[index++] = arr[i];
            }
        }
        return ans;
    }


    public String lowString2(String[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        Arrays.sort(arr, (a, b) -> (a + b).compareTo(b + a));
        return Arrays.stream(arr).reduce("", (a, b) -> a + b);
    }


    @Test
    public void testBestArrange2() {
        System.out.println(bestArrange2(new int[][]{{1, 2}, {2, 3}, {4, 5}}));
        System.out.println(bestArrange(new int[][]{{1, 2}, {2, 3}, {4, 5}}));
    }

    public int bestArrange2(int[][] times) {
        if (times == null || times.length == 0) {
            return 0;
        }
        // 按照结束时间排序
        Arrays.sort(times, (a, b) -> a[1] - b[1]);
        // 时间线，保存时间的结束点，后续会议开始时间大于上次会议结束时间，会议场次加一
        int timeLine = 0;
        int ans = 0;
        for (int i = 0; i < times.length; i++) {
            if (times[i][0] > timeLine) {
                ans++;
                timeLine = times[i][1];
            }
        }
        return ans;
    }


    public int bestArrange(int[][] times) {
        if (times == null || times.length == 0) {
            return 0;
        }
        return process(times, 0, 0);
    }

    public int process(int[][] times, int done, int timeLine) {
        if (times.length == 0) {
            // 若当前已经没有会议，发挥已经完成的会议场次
            return done;
        }
        // 还有剩余会议
        int max = done;
        for (int i = 0; i < times.length; i++) {
            // 假设当前会议为首场
            int[] first = times[i];
            // 若后续会议开始时间大于上场会议结束时间，场次加一
            if (first[0] > timeLine) {
                // 移除当前场
                int[][] nexts = removeEleOfArr(times, i);
                // 更新时间线为上场次的结束时间
                timeLine = first[1];
                // 取以当前会议为首场能够的场次和其他时间点为首场次会议场次的最大值
                max = Math.max(max, process(nexts, done + 1, timeLine));
            }
        }
        return max;
    }

    /**
     * 移除指定位置的元素
     *
     * @param arr
     * @param pos
     * @return
     */
    public int[][] removeEleOfArr(int[][] arr, int pos) {
        int[][] ans = new int[arr.length - 1][2];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != pos) {
                ans[index++] = arr[i];
            }
        }
        return ans;
    }


    @Test
    public void testLessMoney() {
        System.out.println(lessMoney(new int[]{10, 20, 30}));
        System.out.println(lessMoney2(new int[]{10, 20, 30}));
    }

    public int lessMoney(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> pQ = new PriorityQueue();
        Arrays.stream(arr).forEach(e -> pQ.offer(e));
        int ans = 0;

        while (pQ.size() > 1) {
            int sum = pQ.poll() + pQ.poll();
            pQ.offer(sum);
            ans += sum;
        }
        return ans;
    }

    public int lessMoney2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    public int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMerge2(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    public int[] copyAndMerge2(int[] arr, int i, int j) {
        int[] res = new int[arr.length - 1];
        int index = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                res[index++] = arr[k];
            }
        }
        res[index] = arr[i] + arr[j];
        return res;
    }

    @Test
    public void testTmp() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        Integer[] integers = list.toArray(new Integer[0]);
        System.out.println(Arrays.stream(integers).map(e -> e + "").reduce("", (a, b) -> a + " " + b));
    }


    @Test
    public void hello() {
        int money = findMaximizedCapital(new int[][]{{5, 1}, {1, 3}, {2, 5}, {6, 4}}, 2, 2);
        System.out.println(money);

    }

    public int findMaximizedCapital(int[][] arr, int W, int K) {
        PriorityQueue<int[]> minCostQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> maxProfitQ = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        Arrays.stream(arr).forEach(e -> minCostQ.offer(e));

        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && W >= minCostQ.peek()[0]) {
                maxProfitQ.offer(minCostQ.poll());
            }

            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll()[1];
        }
        return W;
    }


    public int minLight(String road) {
        char[] str = road.toCharArray();
        int i = 0;
        int light = 0;
        while (i < str.length) {
            if (str[i] == 'X') {
                i++;
            } else {
                light++;
                if (i + 1 == str.length) {
                    break;
                } else {
                    if (str[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }

    @Test
    public void testMergeTwoLists() {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        mergeTwoLists(list1, list2);
    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }


    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
